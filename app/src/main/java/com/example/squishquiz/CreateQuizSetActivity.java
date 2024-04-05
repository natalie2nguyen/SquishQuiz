package com.example.squishquiz;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

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
    }

    private void saveQuizSet() {
        // Retrieve the title from the EditText
        String title = titleEditText.getText().toString().trim();

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
            List<String> options = new ArrayList<>();
            options.add(option1EditText.getText().toString().trim());
            options.add(option2EditText.getText().toString().trim());
            options.add(option3EditText.getText().toString().trim());
            options.add(option4EditText.getText().toString().trim());

            // Retrieve the correct answer index from the EditText
            int correctAnswerIndex = Integer.parseInt(correctAnswerEditText.getText().toString().trim()) -1;

            // Create a Question object and add it to the questions list
            Question question = new Question(questionText, options, correctAnswerIndex);
            questions.add(question);
        }

        // Create a QuizSet object with the title and questions
        QuizSet quizSet = new QuizSet(title, questions);

        // Save the quiz set using SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("QuizSets", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = GsonHelper.toJson(quizSet);
        editor.putString(title, json);

        // Save the quiz set title separately
        Set<String> quizSetTitles = sharedPreferences.getStringSet("QuizSetTitles", new HashSet<String>());
        quizSetTitles.add(title);
        editor.putStringSet("QuizSetTitles", quizSetTitles);

        editor.apply();

        // Finish the activity
        finish();
    }
}