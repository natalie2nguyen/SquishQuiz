package com.example.squishquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText inputUsername = findViewById(R.id.inputUsername);
        final EditText inputPassword = findViewById(R.id.inputPassword);
        Button registerButton = findViewById(R.id.registerButton);
        Button loginButton = findViewById(R.id.loginButton);

        // when user clicks login button -> display username if the user is in the system
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = inputUsername.getText().toString();
                String password  = inputPassword.getText().toString();

                SharedPreferences preferences = getSharedPreferences("myPreferences", MODE_PRIVATE);
                String storedPassword = preferences.getString(user + password + "data", "");

//                Map<String, ?> allEntries = preferences.getAll();
//
//                int numberOfAccounts = 0;
//                for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
//                    // Check if the entry is a username and password combination
//                    if (entry.getKey().endsWith("data")) {
//                        numberOfAccounts++;
//                        // If the count exceeds 5, remove the entry
//                        if (numberOfAccounts > 5) {
//                            SharedPreferences.Editor editor = preferences.edit();
//                            editor.remove(entry.getKey());
//                            editor.apply();
//                        }
//                    }
//                }
//
//                Log.i("Number of Accounts", "Total number of saved usernames and passwords: " + numberOfAccounts);


                // if user doesnt have an account
                if(storedPassword.equals("")){
                    Toast.makeText(MainActivity.this, "No user found",Toast.LENGTH_LONG).show();
                }
                else{
                    // User exists and credentials match
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("loggedInUser", user);
                    editor.apply();

                    Intent displayScreen = new Intent(MainActivity.this, HomeScreen.class);
                    startActivity(displayScreen);
                }

            }
        });
        // When user click register button -> go to register screen
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registerScreen = new Intent(MainActivity.this, RegisterScreen.class);
                startActivity(registerScreen);
            }
        });
    }

    public void goHome(View view) {
        //create the intent then launch it
        Intent goToHomePage = new Intent(this, HomeScreen.class);
        startActivity(goToHomePage);
    }

}