package com.example.wastewizard;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MunicipalUsersActivity extends AppCompatActivity {

    private Button registerButton, loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipal_users);

        // Initialize buttons
        registerButton = findViewById(R.id.registerButton);
        loginButton = findViewById(R.id.loginButton);

        // Set click listener for Register button
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start RegisterActivity
                Intent intent = new Intent(MunicipalUsersActivity.this, MunicipalUsersRegistrationActivity.class);
                startActivity(intent);
            }
        });

        // Set click listener for Login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start LoginActivity
                Intent intent = new Intent(MunicipalUsersActivity.this, MunicipalUsersLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
