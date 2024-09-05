package com.mangu.tfmjuanma.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hangman {
    private final Random random = new Random();
    private final List<String> wordsList = new ArrayList<>();

    public Hangman(List<String> words) {
        words.forEach(word -> wordsList.add(word.trim()));
    }

    public String pickGoodStarterWord() {
        return wordsList.get(random.nextInt(wordsList.size())).toLowerCase();
    }
}
