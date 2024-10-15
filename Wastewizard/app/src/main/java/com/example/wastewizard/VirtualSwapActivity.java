package com.example.wastewizard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class VirtualSwapActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private EditText editTextName, editTextAddress;
    private Spinner spinnerWasteType;
    private Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual_swap);

        editTextName = findViewById(R.id.editTextName);
        editTextAddress = findViewById(R.id.editTextAddress);
        spinnerWasteType = findViewById(R.id.spinnerWasteType);

        // Populate spinner with waste types
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.waste_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWasteType.setAdapter(adapter);

        Button buttonUploadPhoto = findViewById(R.id.buttonUploadPhoto);
        buttonUploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
        }
    }

    private void submitForm() {
        String name = editTextName.getText().toString();
        String address = editTextAddress.getText().toString();
        String wasteType = spinnerWasteType.getSelectedItem().toString();

        // Check if all fields are filled
        if (name.isEmpty() || address.isEmpty() || wasteType.isEmpty() || imageBitmap == null) {
            Toast.makeText(this, "Please fill all fields and upload a photo.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Now you can do something with the collected data, like sending it to a server or saving it locally
        // For simplicity, let's just display a toast message with the collected data
        String message = "Name: " + name + "\nAddress: " + address + "\nWaste Type: " + wasteType;
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        // You can also work with the captured image (imageBitmap) here.
    }
}