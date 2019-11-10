package com.charan.paginglibrary;

import java.util.ArrayList;

public class Word {
    private String word;

    public Word(String name) {
        word = name;
    }

    public String getName() {
        return word;
    }

    private static int lastWordId = 0;

    public static ArrayList<Word> createWordsList(int numWords) {
        ArrayList<Word> words = new ArrayList<Word>();

        for (int i = 1; i <= numWords; i++) {
            words.add(new Word("This is word " + i));
        }

        return words;
    }
}