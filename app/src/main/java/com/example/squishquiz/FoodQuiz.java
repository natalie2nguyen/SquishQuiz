package com.example.squishquiz;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class FoodQuiz extends AppCompatActivity implements View.OnClickListener{

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button optionA, optionB, optionC, optionD;
    int score = 0;
    int amountOfQuestions = FoodQuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String [] selectedAnswer = new String[10];
    ImageView foodImage;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.logout ){
            Intent intent = new Intent(FoodQuiz.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.homeIcon){
            Intent intent = new Intent(FoodQuiz.this, HomeScreen.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_quiz);
        totalQuestionsTextView = findViewById(R.id.totalQuestions);
        questionTextView = findViewById(R.id.foodQuestion);
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        foodImage = findViewById(R.id.foodImage);


        optionA.setOnClickListener(this);
        optionB.setOnClickListener(this);
        optionC.setOnClickListener(this);
        optionD.setOnClickListener(this);

        totalQuestionsTextView.setText("Total Questions: "+amountOfQuestions);

        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {
        // Check if an option button was clicked
        if (view instanceof Button) {
            Button clickedButton = (Button) view;
            // Record the selected answer
            selectedAnswer[currentQuestionIndex] = clickedButton.getText().toString();

            // Check if the selected answer is correct
            if (selectedAnswer[currentQuestionIndex].equals(FoodQuestionAnswer.correctAnswers[currentQuestionIndex])) {
                score++;
            }

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

        Log.d("questionIndex", "currentQuestionIdex" + currentQuestionIndex);

        //selectedAnswer[currentQuestionIndex] = "";
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
        if(currentQuestionIndex > amountOfQuestions-1){
            finishQuiz();
            return;
        }
        // load flag
        foodImage.setImageResource(FoodQuestionAnswer.foodImages[currentQuestionIndex]);
        questionTextView.setText(FoodQuestionAnswer.question[currentQuestionIndex]);
        optionA.setText(FoodQuestionAnswer.choices[currentQuestionIndex][0]);
        optionB.setText(FoodQuestionAnswer.choices[currentQuestionIndex][1]);
        optionC.setText(FoodQuestionAnswer.choices[currentQuestionIndex][2]);
        optionD.setText(FoodQuestionAnswer.choices[currentQuestionIndex][3]);


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
                .setMessage("Your Score is: "+score+" out of "+amountOfQuestions)
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
