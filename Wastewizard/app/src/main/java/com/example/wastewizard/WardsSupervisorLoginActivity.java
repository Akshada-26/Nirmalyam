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

public class WardsSupervisorLoginActivity extends AppCompatActivity {

    EditText WardsLoginUsername, wardsLoginPassword;
    Button wardsLoginButton;
    TextView wardsRegisterRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wards_supervisor_login);

        WardsLoginUsername = findViewById(R.id.WardsLoginUsername);
        wardsLoginPassword = findViewById(R.id.wardsLoginPassword);
        wardsRegisterRedirect = findViewById(R.id.wardsRegisterRedirect);
        wardsLoginButton = findViewById(R.id.wardsLoginButton);

        wardsLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateUsername() | !validatePassword()){
                    Toast.makeText(WardsSupervisorLoginActivity.this, "You havent login", Toast.LENGTH_SHORT).show();


                }else {
                    checkUser();
                }
            }
        });

        wardsRegisterRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WardsSupervisorLoginActivity.this, MunicipalUsersRegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
    public Boolean validateUsername(){
        String val = WardsLoginUsername.getText().toString();
        if(val.isEmpty()){
            WardsLoginUsername.setError("Username cannot be empty");
            return false;
        }
        else {
            WardsLoginUsername.setError(null);
            return true;
        }
    }

    public Boolean validatePassword(){
        String val = wardsLoginPassword.getText().toString();
        if(val.isEmpty()){
            wardsLoginPassword.setError("Password cannot be empty");
            return false;
        }
        else {
            wardsLoginPassword.setError(null);
            return true;
        }
    }

    public void checkUser(){
        String userUsername = WardsLoginUsername.getText().toString().trim();
        String userPassword = wardsLoginPassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("supervisor");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    WardsLoginUsername.setError(null);
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);

                    if (passwordFromDB.equals(userPassword)){
                        WardsLoginUsername.setError(null);

                        //pass data using intent

                        String nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);
                        String addressFromDB = snapshot.child(userUsername).child("address").getValue(String.class);
                        String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);

                        Intent intent = new Intent(WardsSupervisorLoginActivity.this, WardsSupervisorHomeActivity.class);

                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("address", addressFromDB);
                        intent.putExtra("username", usernameFromDB);
                        //  intent.putExtra("password", nameFromDB);

                        startActivity(intent);
                    }
                    else {
                        wardsLoginPassword.setError("Invalid Credentials");
                        wardsLoginPassword.requestFocus();
                    }
                }
                else {
                    WardsLoginUsername.setError("User does not exists");
                    WardsLoginUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}