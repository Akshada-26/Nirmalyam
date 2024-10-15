package com.example.wastewizard;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WardsSupervisorActivity extends AppCompatActivity {

    private Button wardsRegisterButton, wardsLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wards_supervisor);

        // Initialize buttons
        wardsRegisterButton = findViewById(R.id.wardsRegisterButton);
        wardsLoginButton = findViewById(R.id.wardsLoginButton);

        // Set click listener for Register button
        wardsRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start RegisterActivity
                Intent intent = new Intent(WardsSupervisorActivity.this, WardsSupervisorRegistrationActivity.class);
                startActivity(intent);
            }
        });

        // Set click listener for Login button
        wardsLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start LoginActivity
                Intent intent = new Intent(WardsSupervisorActivity.this, WardsSupervisorLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
