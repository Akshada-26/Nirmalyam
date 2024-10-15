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

public class WardsSupervisorRegistrationActivity extends AppCompatActivity {

    EditText wardsName, wardsAddress, wardsUsername, wardsPassword;
    Button wardsRegister;
    TextView wardsLoginRedirect;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wards_supervisor_registration);

        wardsName=findViewById(R.id.wardsName);
        wardsAddress=findViewById(R.id.wardsAddress);
        wardsUsername = findViewById(R.id.wardsUsername);
        wardsPassword = findViewById(R.id.wardsPassword);
        wardsRegister = findViewById(R.id.wardsRegister);
        wardsLoginRedirect= findViewById(R.id.wardsLoginRedirect);

        wardsRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                database= FirebaseDatabase.getInstance();
                reference=database.getReference("supervisor");

                String name = wardsName.getText().toString();
                String address = wardsAddress.getText().toString();
                String username = wardsUsername.getText().toString();
                String password = wardsPassword.getText().toString();

                supervisor ss = new supervisor(name, address, username, password);
                reference.child(username).setValue(ss);

                Toast.makeText(WardsSupervisorRegistrationActivity.this, "You have registered successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(WardsSupervisorRegistrationActivity.this, WardsSupervisorHomeActivity.class);
                startActivity(intent);
            }
        });

        wardsLoginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WardsSupervisorRegistrationActivity.this, WardsSupervisorLoginActivity.class);
                startActivity(intent);

            }
        });
    }
}
