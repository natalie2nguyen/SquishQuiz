package com.example.squishquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CreateQuizSetActivity extends AppCompatActivity {
    private EditText titleEditText;
    private List<Question> questions;
    private LinearLayout questionContainer;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.logout ){
            Intent intent = new Intent(CreateQuizSetActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.homeIcon){
            Intent intent = new Intent(CreateQuizSetActivity.this, HomeScreen.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz_set);

        // Initialize views
        titleEditText = findViewById(R.id.titleEditText);
        questionContainer = findViewById(R.id.questionContainer);
        questions = new ArrayList<>();

        // Set up the UI for creating questions and answers
        for (int i = 0; i < 10; i++) {
            // Inflate the question view layout
            View questionView = getLayoutInflater().inflate(R.layout.item_question, questionContainer, false);

            // Find the question and answer EditTexts within the inflated view
            EditText questionEditText = questionView.findViewById(R.id.questionEditText);
            EditText option1EditText = questionView.findViewById(R.id.option1EditText);
            EditText option2EditText = questionView.findViewById(R.id.option2EditText);
            EditText option3EditText = questionView.findViewById(R.id.option3EditText);
            EditText option4EditText = questionView.findViewById(R.id.option4EditText);
            EditText correctAnswerEditText = questionView.findViewById(R.id.correctAnswerEditText);

            // Add the question view to the container
            questionContainer.addView(questionView);
        }

        // Set up the save button click listener
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveQuizSet();
            }
        });

        View homeIcon = findViewById(R.id.homeIcon);
        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHome = new Intent(CreateQuizSetActivity.this, HomeScreen.class);
                startActivity(goHome);
            }
        });
    }
    
    

    private void saveQuizSet() {
        // Retrieve the title from the EditText
        String title = titleEditText.getText().toString().trim();

        // Check if the title is empty
        if (title.isEmpty()) {
            titleEditText.setError("Please enter a title");
            return;
        }

        // Clear the questions list
        questions.clear();

        // Iterate over the question views in the container
        for (int i = 0; i < 10; i++) {
            // Get the question view at the current index
            View questionView = questionContainer.getChildAt(i);

            // Find the question and answer EditTexts within the question view
            EditText questionEditText = questionView.findViewById(R.id.questionEditText);
            EditText option1EditText = questionView.findViewById(R.id.option1EditText);
            EditText option2EditText = questionView.findViewById(R.id.option2EditText);
            EditText option3EditText = questionView.findViewById(R.id.option3EditText);
            EditText option4EditText = questionView.findViewById(R.id.option4EditText);
            EditText correctAnswerEditText = questionView.findViewById(R.id.correctAnswerEditText);

            // Retrieve the question text and options from the EditTexts
            String questionText = questionEditText.getText().toString().trim();
            String option1 = option1EditText.getText().toString().trim();
            String option2 = option2EditText.getText().toString().trim();
            String option3 = option3EditText.getText().toString().trim();
            String option4 = option4EditText.getText().toString().trim();
            String correctAnswer = correctAnswerEditText.getText().toString().trim();

            // Check if any of the fields are empty
            if (questionText.isEmpty() || option1.isEmpty() || option2.isEmpty() || option3.isEmpty() || option4.isEmpty() || correctAnswer.isEmpty()) {
                questionEditText.setError("Please fill in all the fields");
                return;
            }

            // Validate the correct answer index
            int correctAnswerIndex;
            try {
                correctAnswerIndex = Integer.parseInt(correctAnswer) - 1;
                if (correctAnswerIndex < 0 || correctAnswerIndex > 3) {
                    correctAnswerEditText.setError("Please enter a valid correct answer index (1-4)");
                    return;
                }
            } catch (NumberFormatException e) {
                correctAnswerEditText.setError("Please enter a valid correct answer index (1-4)");
                return;
            }

            // Create a list of options
            List<String> options = new ArrayList<>();
            options.add(option1);
            options.add(option2);
            options.add(option3);
            options.add(option4);

            // Create a Question object and add it to the questions list
            Question question = new Question(questionText, options, correctAnswerIndex);
            questions.add(question);
        }

        // Create a QuizSet object with the title and questions
        QuizSet quizSet = new QuizSet(title, questions);

        // Get the logged-in user's username
        SharedPreferences preferences = getSharedPreferences("myPreferences", MODE_PRIVATE);
        String loggedInUser = preferences.getString("loggedInUser", "");

        // Save the quiz set using SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("QuizSets", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = GsonHelper.toJson(quizSet);
        editor.putString(loggedInUser + "_" + title, json);

        // Save the quiz set title separately for the logged-in user
        Set<String> quizSetTitles = sharedPreferences.getStringSet(loggedInUser + "_QuizSetTitles", new HashSet<String>());
        quizSetTitles.add(title);
        editor.putStringSet(loggedInUser + "_QuizSetTitles", quizSetTitles);
        editor.apply();

        // Finish the activity
        finish();
    }
}