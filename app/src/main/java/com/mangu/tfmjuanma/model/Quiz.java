package com.mangu.tfmjuanma.model;

import android.util.Pair;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Parcel
public class Quiz {

    public static final int MAX_QUESTIONS = 5;
    public List<Question> questionList;


    public List<Question> listForSession;

    public int currentIndex = 0;

    public boolean isFinished = false;

    public Map<Integer, Boolean> answerWasCorrect = new HashMap<>();

    public Map<Integer, Integer> positionGiven = new HashMap<>();

    @ParcelConstructor
    public Quiz(List<Question> questionList) {
        this.questionList = questionList;
        this.listForSession = new HashSet<>(questionList)
                .stream().limit(MAX_QUESTIONS).collect(Collectors.toList());
        Collections.shuffle(listForSession);
    }

    public void solve(String text) {
        Question question = listForSession.get(currentIndex);
        answerWasCorrect.put(currentIndex,
                question.getAnswers().indexOf(text) == question.getPositionOfAnswer());
        positionGiven.put(currentIndex, question.getAnswers().indexOf(text));
        currentIndex++;
        isFinished = currentIndex == listForSession.size();
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public Question getCurrentQuestion() {
        return listForSession.get(currentIndex);
    }

    public boolean isFinished() {
        return isFinished;
    }

    public long getResult() {
        return answerWasCorrect.values().stream().filter(value -> value == Boolean.TRUE).count();
    }

    public int getMaxQuestions() {
        return listForSession.size();
    }


    public Map<Integer, Pair<String, Boolean>> getResults() {
        Map<Integer, Pair<String, Boolean>> result = new HashMap<>();
        for (Integer index : answerWasCorrect.keySet()) {
            Question question = this.listForSession.get(index);
            String answerGiven = "Question was: " + question.getTitle() +
                    " Your answer was: " +
                    question.getAnswers().get(positionGiven.getOrDefault(index, question.getPositionOfAnswer()))
                    + "\n";
            if (Boolean.FALSE.equals(answerWasCorrect.get(index))) {
                String wrongAnswer = answerGiven + Arrays.toString(Character.toChars(0x274C)) + " The correct answer was: " +
                        question.getAnswers().get(question.getPositionOfAnswer())+"\n";
                result.put(index, Pair.create(wrongAnswer, false));
            } else {
                result.put(index, Pair.create(answerGiven + Arrays.toString(Character.toChars(0x2705)), true));
            }
        }
        return result;
    }
}
