package com.example.squishquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterScreen extends AppCompatActivity {
    EditText username;
    EditText password;
    Button registerButton;
    ImageView goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);

        username = findViewById(R.id.inputUsername);
        password = findViewById(R.id.inputPassword);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("myPreferences", MODE_PRIVATE);
                String newUser = username.getText().toString();
                String newPassword = password.getText().toString();

                SharedPreferences.Editor editor = preferences.edit();
                // Save the username and password
                editor.putString(newUser + newPassword + "data", newUser);

                // Create a new Profile object for the user
                Profile profile = new Profile(newUser, newPassword, 0);
                String profileJson = GsonHelper.toJson(profile);
                editor.putString(newUser + "profile", profileJson);

                editor.apply();

                Intent goToLogin = new Intent(RegisterScreen.this, MainActivity.class);
                startActivity(goToLogin);
            }
        });

        // If the user clicks on the profile icon, log out
        goBack = findViewById(R.id.goBack);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}