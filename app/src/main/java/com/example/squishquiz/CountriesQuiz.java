package com.example.squishquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class CountriesQuiz extends AppCompatActivity implements View.OnClickListener{

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button optionA, optionB, optionC, optionD;
    ImageView goNext;
    int score = 0;
    int amountOfQuestions = CountriesQuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";
    ImageView flagImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries_quiz);
        totalQuestionsTextView = findViewById(R.id.totalQuestions);
        questionTextView = findViewById(R.id.countryQuestion);
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        goNext = findViewById(R.id.goNext);
        flagImage = findViewById(R.id.flagImage);


        optionA.setOnClickListener(this);
        optionB.setOnClickListener(this);
        optionC.setOnClickListener(this);
        optionD.setOnClickListener(this);
        goNext.setOnClickListener(this);


        totalQuestionsTextView.setText("Total Questions: "+amountOfQuestions);

        loadNewQuestion();
    }
    public void onClick(View view) {
        try {
            // Check if the clicked view is the "Next" button
            if (view.getId() == R.id.goNext) {
                // Check if an option is selected before proceeding
                if (selectedAnswer.isEmpty() || selectedAnswer == null) {
                    throw new IllegalStateException("Please select an option before proceeding");
                }

                // Handle click on "Next" (ImageView)
                if (selectedAnswer.equals(CountriesQuestionAnswer.correctAnswers[currentQuestionIndex])) {
                    score++;
                }
                currentQuestionIndex++;
                loadNewQuestion();
            } else {
                Button clickedButton = (Button) view;
                // If the clicked view is not the "Next" button, it's one of the option buttons
                selectedAnswer = clickedButton.getText().toString();
                clickedButton.setBackgroundColor(Color.BLACK);
                //clickedButton.setTextColor(Color.WHITE);
            }
        } catch (IllegalStateException e) {
            // Handle the exception
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }



    }



    public void loadNewQuestion(){

        selectedAnswer = "";
        // set option buttons to their default color
        int optionAColor = ContextCompat.getColor(this, R.color.pink);
        int optionBColor = ContextCompat.getColor(this, R.color.teal);
        int optionCColor = ContextCompat.getColor(this, R.color.blue);
        int optionDColor = ContextCompat.getColor(this, R.color.yellow);

        optionA.setBackgroundColor(optionAColor);
        optionB.setBackgroundColor(optionBColor);
        optionC.setBackgroundColor(optionCColor);
        optionD.setBackgroundColor(optionDColor);

        //load the new question
        if(currentQuestionIndex == amountOfQuestions){
            finishQuiz();
            return;
        }
        // load flag
        flagImage.setImageResource(CountriesQuestionAnswer.flagImages[currentQuestionIndex]);
        questionTextView.setText(CountriesQuestionAnswer.question[currentQuestionIndex]);
        optionA.setText(CountriesQuestionAnswer.choices[currentQuestionIndex][0]);
        optionB.setText(CountriesQuestionAnswer.choices[currentQuestionIndex][1]);
        optionC.setText(CountriesQuestionAnswer.choices[currentQuestionIndex][2]);
        optionD.setText(CountriesQuestionAnswer.choices[currentQuestionIndex][3]);
    }

    private void finishQuiz() {
        String passStatus = "";
        if(score > amountOfQuestions *.60){
            passStatus = "Passed";
        }
        else{
            passStatus = "Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is"+score+"out of"+amountOfQuestions)
                .setPositiveButton("Restart", (dialogInterface, i) -> restartQuiz())
                .setCancelable(false)
                .show();
    }

    private void restartQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }

    public void goHome(View view){
        Intent goToHomePage = new Intent(this, HomeScreen.class);
        startActivity(goToHomePage);
    }

}