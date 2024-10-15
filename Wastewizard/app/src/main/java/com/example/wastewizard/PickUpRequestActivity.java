package com.example.wastewizard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PickUpRequestActivity extends AppCompatActivity {

    Button virtualCollectButton, takeAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_up_request);

        // Button for navigating to the Virtual Collect activity
        virtualCollectButton = findViewById(R.id.virtualCollectButton);
        takeAction = findViewById(R.id.takeAction);

        virtualCollectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PickUpRequestActivity.this, VirtualCollectActivity.class);
                startActivity(intent);
            }
   });

    }
}