package com.example.squishquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    private QuizSet quizSet;
    private TextView questionTextView;
    private List<Button> optionButtons;
    private int currentQuestionIndex;
    private int score;
    private String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionTextView = findViewById(R.id.questionTextView);
        optionButtons = new ArrayList<>();
        optionButtons.add(findViewById(R.id.optionButton1));
        optionButtons.add(findViewById(R.id.optionButton2));
        optionButtons.add(findViewById(R.id.optionButton3));
        optionButtons.add(findViewById(R.id.optionButton4));

        for (Button button : optionButtons) {
            button.setOnClickListener(this);
        }

        // Retrieve the selected quiz set title from the intent extras
        String quizSetTitle = getIntent().getStringExtra("QuizSetTitle");

        // Retrieve the quiz set from SharedPreferences based on the title
        SharedPreferences sharedPreferences = getSharedPreferences("QuizSets", MODE_PRIVATE);
        String json = sharedPreferences.getString(quizSetTitle, "");
        quizSet = GsonHelper.fromJson(json);

        currentQuestionIndex = 0;
        score = 0;

        displayQuestion();
    }

    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;
        selectedAnswer = clickedButton.getText().toString();

        checkAnswer(quizSet.getQuestions().get(currentQuestionIndex), clickedButton);
    }

    private void displayQuestion() {
        if (currentQuestionIndex < quizSet.getQuestions().size()) {
            Question currentQuestion = quizSet.getQuestions().get(currentQuestionIndex);
            questionTextView.setText(currentQuestion.getQuestionText());

            for (int i = 0; i < optionButtons.size(); i++) {
                Button button = optionButtons.get(i);
                button.setText(currentQuestion.getOptions().get(i));
            }
        } else {
            finishQuiz();
        }
    }

    private void checkAnswer(Question question, Button selectedButton) {
        int selectedAnswerIndex = optionButtons.indexOf(selectedButton);

        if (selectedAnswerIndex == question.getCorrectAnswerIndex()) {
            score++;
        }

        currentQuestionIndex++;
        displayQuestion();
    }

    private void finishQuiz() {
        String passStatus = score > quizSet.getQuestions().size() * 0.6 ? "Passed" : "Failed";

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Your Score is: " + score + " out of " + quizSet.getQuestions().size())
                .setPositiveButton("Restart", (dialogInterface, i) -> restartQuiz())
                .setCancelable(false)
                .show();
    }

    private void restartQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        displayQuestion();
    }

    public void goHome(View view) {
        Intent goToHomePage = new Intent(this, HomeScreen.class);
        startActivity(goToHomePage);
    }
}
