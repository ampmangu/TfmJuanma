package com.mangu.tfmjuanma.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mangu.tfmjuanma.R;
import com.mangu.tfmjuanma.databinding.ActivityHangmanBinding;
import com.mangu.tfmjuanma.model.Hangman;
import com.mangu.tfmjuanma.service.FileService;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HangmanActivity extends AppCompatActivity {

    public static final String PREFERENCES_GAME = "preferencesGame";
    private ActivityHangmanBinding binding;
    private Hangman hangman;
    @Inject
    public FileService fileService;
    private String currentWord;
    private int count = 0;
    private String result = " ";

    private int score = 0, maxScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeContent();
        initializeView();
    }

    private void initializeContent() {
        List<String> words = fileService.getWords();
        hangman = new Hangman(words);
        currentWord = hangman.pickGoodStarterWord();
        SharedPreferences prefs = getSharedPreferences(PREFERENCES_GAME, MODE_PRIVATE);
        maxScore = prefs.getInt("maxScore", 0);
    }

    private void initializeView() {
        binding = ActivityHangmanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setUpLetterButton();
        binding.reset.setOnClickListener(this::resetListener);
        binding.solve.setOnClickListener(view1 -> binding.word.setText(currentWord));
        binding.play.setOnClickListener(this::playListener);
        binding.reset.callOnClick();
        binding.high.setText("" + maxScore);
    }

    private void setUpLetterButton() {
        binding.letter.setRawInputType(InputType.TYPE_CLASS_TEXT);
        binding.letter.setImeOptions(EditorInfo.IME_ACTION_GO);
        binding.letter.setOnEditorActionListener(actionListenerForLetter());
    }

    private TextView.OnEditorActionListener actionListenerForLetter() {
        return (v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_GO) {
                checkLetter(binding.letter);
            }
            return true;
        };
    }

    private void checkLetter(EditText editText) {
        int flag = 0;
        TextView word = binding.word;
        String letter = editText.getText().toString().trim().toLowerCase();
        flag = getCoincidences(letter, word, flag);
        checkScore(flag, word);
        editText.setText("");
    }

    private int getCoincidences(String letter, TextView word, int flag) {
        int coincidences;
        int index;
        for (index = 0; index < currentWord.length(); ++index) {
            if (currentWord.indexOf(letter, index) != -1) {
                coincidences = currentWord.indexOf(letter, index);
                result = result.substring(0, 2 * coincidences) + letter + " " + result.substring(2 * coincidences + 2);
                word.setText(result);
                index = coincidences;
                flag = 1;
                if (!result.contains("_")) {
                    String youWon = getString(R.string.you_won);
                    word.setText(youWon + "\n" + currentWord);
                    score += 1;
                    binding.score.setText("" + score);
                    if (maxScore < score) {
                        maxScore = score;
                        binding.high.setText("" + maxScore);
                        addMaxScore(maxScore);
                    }
                }
            }
        }
        return flag;
    }

    private void addMaxScore(int maxScore) {
        SharedPreferences.Editor editor = getSharedPreferences(PREFERENCES_GAME, MODE_PRIVATE).edit();
        editor.putInt("maxScore", maxScore);
        editor.apply();
    }

    private void checkScore(int flag, TextView word) {
        if (flag == 0) {
            count = count + 1;
            if (count >= 6) {
                String youLost = getString(R.string.you_lost);
                word.setText(youLost + "\n" + currentWord);
                score = 0;
                binding.score.setText("" + score);
            }
            int idHang = getResources().getIdentifier("hang" + count, "drawable", getApplication().getPackageName());
            binding.hang.setImageDrawable(getDrawable(idHang));
        }
    }

    private void resetListener(View view1) {
        count = 0;
        int hangId = getResources().getIdentifier("hang0", "drawable", getApplication().getPackageName());
        binding.hang.setImageDrawable(getDrawable(hangId));
        currentWord = null;
        currentWord = hangman.pickGoodStarterWord();
        result = "";
        binding.word.setText("");
        for (int i = 0; i < currentWord.length(); i += 1) {
            result += "_ ";
        }
        binding.word.setText(result);
        binding.letter.setEnabled(true);
        binding.letter.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(binding.letter, InputMethodManager.SHOW_IMPLICIT);
    }

    private void playListener(View view12) {
        if (currentWord == null) {
            currentWord = hangman.pickGoodStarterWord();
            for (int i = 0; i < currentWord.length(); i += 1) {
                result += "_ ";
            }
            binding.word.setText(result);
        }
        binding.letter.setEnabled(true);
        binding.letter.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(binding.letter, InputMethodManager.SHOW_IMPLICIT);
    }
}