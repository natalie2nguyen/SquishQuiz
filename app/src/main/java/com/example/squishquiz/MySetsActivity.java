package com.example.squishquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class MySetsActivity extends AppCompatActivity {
    private LinearLayout setsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sets);

        setsContainer = findViewById(R.id.setsContainer);

        // Retrieve the quiz set titles from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("QuizSets", MODE_PRIVATE);
        Set<String> quizSetTitles = sharedPreferences.getStringSet("QuizSetTitles", new HashSet<String>());

        // Find the quiz set button template
        Button buttonTemplate = findViewById(R.id.quizSetButtonTemplate);

        // Create buttons for each quiz set title
        for (String title : quizSetTitles) {
            Button button = new Button(this);
            button.setLayoutParams(buttonTemplate.getLayoutParams());
            button.setPadding(buttonTemplate.getPaddingLeft(), buttonTemplate.getPaddingTop(),
                    buttonTemplate.getPaddingRight(), buttonTemplate.getPaddingBottom());
            button.setTextSize(TypedValue.COMPLEX_UNIT_PX, buttonTemplate.getTextSize());
            button.setTextColor(buttonTemplate.getTextColors());
            button.setBackgroundDrawable(buttonTemplate.getBackground());
            button.setText(title);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Start the QuizActivity with the selected quiz set
                    Intent intent = new Intent(MySetsActivity.this, QuizActivity.class);
                    intent.putExtra("QuizSetTitle", title);
                    startActivity(intent);
                }
            });
            setsContainer.addView(button);
        }
    }
    public void goHome(View view) {
        Intent goToHomePage = new Intent(this, HomeScreen.class);
        startActivity(goToHomePage);
    }

}