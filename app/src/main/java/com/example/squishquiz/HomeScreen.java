package com.example.squishquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

    }
    // go to Countries Quiz
    public void goCountriesQuiz(View view) {
        //create the intent then launch it
        Intent goCountries = new Intent(HomeScreen.this, CountriesQuiz.class);
        startActivity(goCountries);
    }

}