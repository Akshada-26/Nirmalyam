package com.example.wastewizard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button municipalUsersBtn, municipalWorkersBtn, wardsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons
        municipalUsersBtn = findViewById(R.id.municipalUsersBtn);
        municipalWorkersBtn = findViewById(R.id.municipalWorkersBtn);
        wardsButton = findViewById(R.id.wardsButton);


        // Set onClick listeners for buttons
        municipalUsersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MunicipalUsersActivity.class);
                startActivity(intent);
            }
        });

        municipalWorkersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to Municipal Workers login/registration page
                Intent intent = new Intent(MainActivity.this, MunicipalWorkersActivity.class);
                startActivity(intent);
            }
        });
        wardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to Municipal Workers login/registration page
                Intent intent = new Intent(MainActivity.this, WardsSupervisorActivity.class);
                startActivity(intent);
            }
        });
    }
}