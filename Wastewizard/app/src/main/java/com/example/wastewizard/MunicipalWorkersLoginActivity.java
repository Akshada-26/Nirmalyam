package com.example.wastewizard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MunicipalWorkersLoginActivity extends AppCompatActivity {

    EditText municipalLoginUsername, municipalLoginPassword;
    Button municipalLogin;
    TextView municipalRegisterRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipal_workers_login);

        municipalLoginUsername = findViewById(R.id.municipalLoginUsername);
        municipalLoginPassword = findViewById(R.id.municipalLoginPassword);
        municipalRegisterRedirect = findViewById(R.id.municipalRegisterRedirect);
        municipalLogin = findViewById(R.id.municipalLogin);

        municipalLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateUsername() | !validatePassword()){
                    Toast.makeText(MunicipalWorkersLoginActivity.this, "You havent login", Toast.LENGTH_SHORT).show();


                }else {
                    checkUser();
                }
            }
        });

        municipalRegisterRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MunicipalWorkersLoginActivity.this, MunicipalWorkersRegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
    public Boolean validateUsername(){
        String val = municipalLoginUsername.getText().toString();
        if(val.isEmpty()){
            municipalLoginUsername.setError("Username cannot be empty");
            return false;
        }
        else {
            municipalLoginUsername.setError(null);
            return true;
        }
    }

    public Boolean validatePassword(){
        String val = municipalLoginPassword.getText().toString();
        if(val.isEmpty()){
            municipalLoginPassword.setError("Password cannot be empty");
            return false;
        }
        else {
            municipalLoginPassword.setError(null);
            return true;
        }
    }

    public void checkUser(){
        String municipalUsername = municipalLoginUsername.getText().toString().trim();
        String municipalPassword = municipalLoginPassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("municipal");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(municipalUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    municipalLoginUsername.setError(null);
                    String passwordFromDB = snapshot.child(municipalUsername).child("password").getValue(String.class);

                    if (passwordFromDB.equals(municipalPassword)){
                        municipalLoginUsername.setError(null);

                        String nameFromDB = snapshot.child(municipalUsername).child("name").getValue(String.class);
                        String addressFromDB = snapshot.child(municipalUsername).child("address").getValue(String.class);
                        String usernameFromDB = snapshot.child(municipalUsername).child("username").getValue(String.class);

                        Intent intent = new Intent(MunicipalWorkersLoginActivity.this, MunicipalWorkersHomeActivity.class);
                        startActivity(intent);
                    }
                    else {
                        municipalLoginPassword.setError("Invalid Credentials");
                        municipalLoginPassword.requestFocus();
                    }
                }
                else {
                    municipalLoginUsername.setError("User does not exists");
                    municipalLoginUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}