package com.example.wastewizard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MunicipalWorkersHomeActivity extends AppCompatActivity {

    Button pickUpRequest, virtualCollect, reportComplaints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipal_workers_home);

        pickUpRequest = findViewById(R.id.pickUpRequest);
        virtualCollect = findViewById(R.id.virtualCollect);
        reportComplaints = findViewById(R.id.reportComplaints);

        pickUpRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Garbage Collector button click
                Toast.makeText(MunicipalWorkersHomeActivity.this, "Garbage Collector button clicked", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MunicipalWorkersHomeActivity.this, PickUpRequestActivity.class);
                startActivity(intent);
            }
        });

        virtualCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Garbage Collector button click
                Toast.makeText(MunicipalWorkersHomeActivity.this, "Garbage Collector button clicked", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MunicipalWorkersHomeActivity.this, VirtualCollectActivity.class);
                startActivity(intent);
            }
        });

        reportComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MunicipalWorkersHomeActivity.this, ComplaintsActivity.class);
                startActivity(intent);
            }
        });


    }
}