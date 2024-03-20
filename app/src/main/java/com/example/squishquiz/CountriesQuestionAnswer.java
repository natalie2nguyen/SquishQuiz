package com.example.squishquiz;

public class CountriesQuestionAnswer {
    public static String question[]={
        "Which country does this flag belong to?",
            "Which country does this flag belong to?",
            "Which country does this flag belong to?",
            "Which country does this flag belong to?",
            "Which country does this flag belong to?",
            "Which country does this flag belong to?",
            "Which country does this flag belong to?",
            "Which country does this flag belong to?",
            "Which country does this flag belong to?",
            "Which country does this flag belong to?"

    };

    public static String choices[][]={
            //vietnam
            {"Vietnam", "South Korea", "Argentina", "Senegal"},
            // Kuwait
            {"Ireland", "Kuwait", "Italy", "Mexico"},
            // South Korea
            {"Somalia", "Vietnam", "South Korea", "Singapore"},
            // Ireland
            {"Ireland", "Somalia", "Argentina", "Mexico"},
            // Senegal
            {"Ireland", "Senegal", "Italy", "Singapore"},
            // Somalia
            {"Senegal", "South Korea", "Vietnam", "Somalia"},
            // Mexico
            {"Vietnam", "South Korea", "Kuwait", "Mexico"},
            // Singapore
            {"Argentina", "Italy", "Somalia", "Singapore"},
            // Argentina
            {"Vietnam", "Argentina", "Kuwait", "Mexico"},
            // Italy
            {"Ireland", "Mexico", "Italy", "Singapore"}
    };

    public static String correctAnswers[] = {
            "Vietnam",
            "Kuwait",
            "South Korea",
            "Ireland",
            "Senegal",
            "Somalia",
            "Mexico",
            "Singapore",
            "Argentina",
            "Italy"

    };

    public static int flagImages[] = {
        R.mipmap.vietnam_flag,
            R.mipmap.kuwait_flag,
            R.mipmap.korea_flag,
            R.mipmap.ireland_flag,
            R.mipmap.senegal_flag,
            R.mipmap.somalia_flag,
            R.mipmap.mexico_flag,
            R.mipmap.singapore_flag,
            R.mipmap.argentina_flag,
            R.mipmap.italy_flag
    };


}
