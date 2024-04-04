package com.example.squishquiz;

import com.google.gson.Gson;

public class GsonHelper {
    private static Gson gson = new Gson();

    public static String toJson(QuizSet quizSet) {
        return gson.toJson(quizSet);
    }

    public static QuizSet fromJson(String json) {
        return gson.fromJson(json, QuizSet.class);
    }
}