package com.example.squishquiz;

import android.app.AlertDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class CitiesQuiz extends AppCompatActivity implements View.OnClickListener{

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button optionA, optionB, optionC, optionD;
    int score = 0;
    int amountOfQuestions = CitiesQuestionAnswer.questions.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";
    ImageView cityImage;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.logout ){
            Intent intent = new Intent(CitiesQuiz.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.homeIcon){
            Intent intent = new Intent(CitiesQuiz.this, HomeScreen.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities_quiz);
        totalQuestionsTextView = findViewById(R.id.totalQuestions);
        questionTextView = findViewById(R.id.cityQuestion);
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        cityImage = findViewById(R.id.cityImage);

        optionA.setOnClickListener(this);
        optionB.setOnClickListener(this);
        optionC.setOnClickListener(this);
        optionD.setOnClickListener(this);


        totalQuestionsTextView.setText("Total Questions: "+amountOfQuestions);

        loadNewQuestion();
    }
    @Override
    public void onClick(View view) {
        if (view instanceof Button) {
            Button clickedButton = (Button) view;

            // Record the selected answer and immediately proceed to evaluate it
            selectedAnswer = clickedButton.getText().toString();

            // Check if the selected answer is correct
            if (selectedAnswer.equals(CitiesQuestionAnswer.answers[currentQuestionIndex])) {
                score++;
            }

            // Reset the selected answer for the next question
            selectedAnswer = "";

            // Move to the next question or finish the quiz if at the end
            currentQuestionIndex++;
            if (currentQuestionIndex < amountOfQuestions) {
                loadNewQuestion();
            } else {
                finishQuiz();
            }
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
        cityImage.setImageResource(CitiesQuestionAnswer.citiesImages[currentQuestionIndex]);
        questionTextView.setText(CitiesQuestionAnswer.questions[currentQuestionIndex]);
        optionA.setText(CitiesQuestionAnswer.choices[currentQuestionIndex][0]);
        optionB.setText(CitiesQuestionAnswer.choices[currentQuestionIndex][1]);
        optionC.setText(CitiesQuestionAnswer.choices[currentQuestionIndex][2]);
        optionD.setText(CitiesQuestionAnswer.choices[currentQuestionIndex][3]);
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
                .setMessage("Score is "+score+" out of "+amountOfQuestions)
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