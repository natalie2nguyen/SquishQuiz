package com.example.squishquiz;

import android.content.Intent;
import android.content.SharedPreferences;
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

import org.w3c.dom.Text;

public class HomeScreen extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.logout ){
            Intent intent = new Intent(HomeScreen.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.homeIcon){
            Intent intent = new Intent(HomeScreen.this, HomeScreen.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

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

    public void goFoodQuiz(View view) {
        //create the intent then launch it
        Intent goFood = new Intent(HomeScreen.this, FoodQuiz.class);
        startActivity(goFood);
    }

    public void goGeneralQuiz(View view) {
        //create the intent then launch it
        Intent goGeneral = new Intent(HomeScreen.this, GeneralQuiz.class);
        startActivity(goGeneral);
    }

}