package com.example.wastewizard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MunicipalUsersHomeActivity extends AppCompatActivity {

    private EditText complaintEditText;

    TextView userName, userUsername, userAddress;
    private Button homeButton, garbageCollectorButton, zeroWastePageButton, virtualSwapButton, complainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipal_users_home);

        // Initialize views

        homeButton = findViewById(R.id.homeButton);
        garbageCollectorButton = findViewById(R.id.garbageCollectorButton);
        zeroWastePageButton = findViewById(R.id.zeroWastePageButton);
        virtualSwapButton = findViewById(R.id.virtualSwapButton);
        complainButton = findViewById(R.id.complainButton);

        userName = findViewById(R.id.userName);
        userUsername = findViewById(R.id.userUsername);
        userAddress = findViewById(R.id.userAddress);

        showUserData();

        // Set onClick listeners for buttons
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Home button click
                Toast.makeText(MunicipalUsersHomeActivity.this, "Home button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        garbageCollectorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Garbage Collector button click
                Toast.makeText(MunicipalUsersHomeActivity.this, "Garbage Collector button clicked", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MunicipalUsersHomeActivity.this, GarbageSchedulerActivity.class);
                startActivity(intent);

            }
        });

        zeroWastePageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Zero Waste Page button click
                Toast.makeText(MunicipalUsersHomeActivity.this, "Zero Waste Page button clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MunicipalUsersHomeActivity.this, ZeroWasteActivity.class);
                startActivity(intent);
            }
        });

        virtualSwapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Virtual Swap button click
                Toast.makeText(MunicipalUsersHomeActivity.this, "Virtual Swap button clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MunicipalUsersHomeActivity.this, VirtualSwapActivity.class);
                startActivity(intent);

            }
        });

        complainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MunicipalUsersHomeActivity.this, complainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void showUserData(){
        Intent intent = getIntent();

        String nameUser = intent.getStringExtra("name");
        String usernameUser = intent.getStringExtra("username");
        String addressUser = intent.getStringExtra("address");
        String passwordUser = intent.getStringExtra("password");

        userName.setText(nameUser);
        userAddress.setText(addressUser);
        userUsername.setText(usernameUser);

    }

}
