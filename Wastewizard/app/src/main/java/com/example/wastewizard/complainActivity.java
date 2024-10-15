package com.example.wastewizard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class complainActivity extends AppCompatActivity {

    EditText editTextAddress, editTextComplaint;
    Button buttonSubmit;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);

        editTextAddress=findViewById(R.id.editTextAddress);
        editTextComplaint = findViewById(R.id.editTextComplaint);
        buttonSubmit = findViewById(R.id.buttonSubmit);


        buttonSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                database= FirebaseDatabase.getInstance();
                reference=database.getReference("complaints");

                String address = editTextAddress.getText().toString();
                String complaint = editTextComplaint.getText().toString();

                complaints c = new complaints(address, complaint);
                reference.child(address).setValue(c);

                Toast.makeText(complainActivity.this, "You have registered successfully", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
