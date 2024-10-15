package com.example.wastewizard;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;



public class GarbageSchedulerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garbage_scheduler);

        // Initialize map fragment


        // Initialize spinner
        Spinner wasteTypeSpinner = findViewById(R.id.waste_type_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.waste_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wasteTypeSpinner.setAdapter(adapter);

        // Initialize list view
        ListView previousSchedulesList = findViewById(R.id.previous_schedules_list);
        // Set adapter for previous schedules list

        // Functionality for the schedule button
        findViewById(R.id.schedule_button).setOnClickListener(view -> {
            // Implement your scheduling logic here
        });
    }

}