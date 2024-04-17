package com.example.squishquiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

        // Get the logged-in user's username
        SharedPreferences preferences = getSharedPreferences("myPreferences", MODE_PRIVATE);
        String loggedInUser = preferences.getString("loggedInUser", "");

        // Retrieve the quiz set titles for the logged-in user from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("QuizSets", MODE_PRIVATE);
        Set<String> quizSetTitles = sharedPreferences.getStringSet(loggedInUser + "_QuizSetTitles", new HashSet<String>());

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
            button.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    new AlertDialog.Builder(MySetsActivity.this)
                            .setTitle("Delete Set")
                            .setMessage("Do you really want to delete the set '" + title + "'?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Remove the set from SharedPreferences
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    Set<String> updatedSetTitles = new HashSet<>(sharedPreferences.getStringSet(loggedInUser + "_QuizSetTitles", new HashSet<String>()));
                                    updatedSetTitles.remove(title);
                                    editor.putStringSet(loggedInUser + "_QuizSetTitles", updatedSetTitles);
                                    editor.apply();

                                    // Remove the button from the layout
                                    setsContainer.removeView(button);
                                }
                            })
                            .setNegativeButton(android.R.string.no, null)
                            .show();
                    return true;
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