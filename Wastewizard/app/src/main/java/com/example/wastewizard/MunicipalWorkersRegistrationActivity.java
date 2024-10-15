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

public class MunicipalWorkersRegistrationActivity extends AppCompatActivity {

    EditText municipalName, municipalUsername, municipalCity, municipalPassword;
    Button municipalRegister;
    TextView municipalLoginRedirect;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipal_workers_registration);

        municipalName=findViewById(R.id.municipalName);
        municipalCity=findViewById(R.id.municipalCity);
        municipalUsername = findViewById(R.id.municipalUsername);
        municipalPassword = findViewById(R.id.municipalPassword);
        municipalRegister = findViewById(R.id.municipalRegister);
        municipalLoginRedirect= findViewById(R.id.municipalLoginRedirect);

        municipalRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                database= FirebaseDatabase.getInstance();
                reference=database.getReference("municipal");

                String name = municipalName.getText().toString();
                String city = municipalCity.getText().toString();
                String username = municipalUsername.getText().toString();
                String password = municipalPassword.getText().toString();

                municipal mp = new municipal(name, city, username, password);
                reference.child(username).setValue(mp);

                Toast.makeText(MunicipalWorkersRegistrationActivity.this, "You have registered successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MunicipalWorkersRegistrationActivity.this, MunicipalWorkersHomeActivity.class);
                startActivity(intent);
            }
        });

        municipalLoginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MunicipalWorkersRegistrationActivity.this, MunicipalWorkersLoginActivity.class);
                startActivity(intent);

            }
        });
    }
}
