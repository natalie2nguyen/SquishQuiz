package com.example.squishquiz;

public class FoodQuestionAnswer {

    public static String question[] = {
        "What is the main ingredient in traditional Greek moussaka?",
            "Which ingredient is used to make the Italian dessert tiramisu?",
            "What is the primary ingredient in the cocktail \"Margarita\"?",
            "In which country did the dish sushi originate?",
            "What type of pastry is used to make a croissant?",
            "What is the main ingredient in the Indian dish \"saag paneer\"?",
            "What is the primary flavoring ingredient in traditional pesto sauce?",
            "Which nut is used to make pesto in the Ligurian variation of the sauce?",
            "What type of wine is traditionally used to make a French coq au vin?",
            "What is the primary ingredient in the popular Vietnamese soup pho?"
    };

    public static String choices[][] = {
            {"Tomato", "Eggplant", "Potato", "Zucchini"},
            {"Raspberry", "Coffee-soaked ladyfingers", "Chocolate", "Lemon"},
            {"Rum", "Tequila", "Vodka", "Gin"},
            {"Thailand", "Japan", "South Korea", "China"},
            {"Filo pastry", "Puff pastry", "Shortcrust pastry", "Choux pastry"},
            {"Lentils", "Spinach", "Cauliflower", "Chickpeas"},
            {"Mint", "Basil", "Parsley", "Cilantro"},
            {"Almonds", "Pine nuts", "Walnuts", "Hazelnuts"},
            {"Champagne", "White wine", "Red wine", "Rosé wine"},
            {"Rice noodles", "Egg noodles", "Udon noodles", "Soba noodles"}
    };
    public static String correctAnswers[] = {
            // Answer: Eggplant
            // Other options: Potato, Zucchini, Tomato
            "Eggplant",
            //Answer: Coffee-soaked ladyfingers
            //Other options: Chocolate, Lemon, Raspberry
            "Coffee-soaked ladyfingers",
            //Answer: Tequila
            //Other options: Vodka, Rum, Gin
            "Tequila",
            //Answer: Japan
            //Other options: China, Thailand, South Korea
            "Japan",
            //Answer: Puff pastry
            //Other options: Shortcrust pastry, Filo pastry, Choux pastry
            "Puff pastry",
            //Answer: Spinach
            //Other options: Lentils, Chickpeas, Cauliflower
            "Spinach",
            //Answer: Basil
            //Other options: Parsley, Cilantro, Mint
            "Basil",
            //Answer: Pine nuts
            //Other options: Almonds, Walnuts, Hazelnuts
            "Pine nuts",
            //Answer: Red wine
            //Other options: White wine, Rosé wine, Champagne
            "Red wine",
            //Answer: Rice noodles
            //Other options: Egg noodles, Udon noodles, Soba noodles
            "Rice noodles"
    };

    public static int foodImages[] = {
        R.mipmap.moussaka,
            R.mipmap.tiramisu,
            R.mipmap.margarita,
            R.mipmap.sushi,
            R.mipmap.croissant,
            R.mipmap.saag_paneer,
            R.mipmap.pesto,
            R.mipmap.ligurian_pesto,
            R.mipmap.coq_au_vin,
            R.mipmap.pho
    };
}

