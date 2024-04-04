package com.example.squishquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        // make sets button
        Button addSetsButton = findViewById(R.id.addSetsButton);
        addSetsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(HomeScreen.this, CreateQuizSetActivity.class);
                startActivity(intent1);
            }
        });

        // my sets
        Button mySetsButton = findViewById(R.id.mySetsButton);
        mySetsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, MySetsActivity.class);
                startActivity(intent);
            }
        });


    }
    // go to Countries Quiz
    public void goCountriesQuiz(View view) {
        //create the intent then launch it
        Intent goCountries = new Intent(HomeScreen.this, CountriesQuiz.class);
        startActivity(goCountries);
    }

    public void goCitiesQuiz(View view) {
        //create the intent then launch it
        Intent goCities = new Intent(HomeScreen.this, CitiesQuiz.class);
        startActivity(goCities);
    }

}