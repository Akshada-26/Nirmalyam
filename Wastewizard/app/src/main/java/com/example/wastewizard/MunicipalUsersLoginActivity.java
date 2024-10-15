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

public class MunicipalUsersLoginActivity extends AppCompatActivity {

    EditText loginUsernameEditText, loginPasswordEditText;
    Button loginButton;
    TextView registerRedirectTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipal_users_login);

        loginUsernameEditText = findViewById(R.id.loginUsernameEditText);
        loginPasswordEditText = findViewById(R.id.loginPasswordEditText);
        registerRedirectTextView = findViewById(R.id.registerRedirectTextView);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateUsername() | !validatePassword()){
                    Toast.makeText(MunicipalUsersLoginActivity.this, "You havent login", Toast.LENGTH_SHORT).show();


                }else {
                    checkUser();
                }
            }
        });

        registerRedirectTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MunicipalUsersLoginActivity.this, MunicipalUsersRegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
    public Boolean validateUsername(){
        String val = loginUsernameEditText.getText().toString();
        if(val.isEmpty()){
            loginUsernameEditText.setError("Username cannot be empty");
            return false;
        }
        else {
            loginUsernameEditText.setError(null);
            return true;
        }
    }

    public Boolean validatePassword(){
        String val = loginPasswordEditText.getText().toString();
        if(val.isEmpty()){
            loginPasswordEditText.setError("Password cannot be empty");
            return false;
        }
        else {
            loginPasswordEditText.setError(null);
            return true;
        }
    }

    public void checkUser(){
        String userUsername = loginUsernameEditText.getText().toString().trim();
        String userPassword = loginPasswordEditText.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    loginUsernameEditText.setError(null);
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);

                    if (passwordFromDB.equals(userPassword)){
                        loginUsernameEditText.setError(null);

                        //pass data using intent

                        String nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);
                        String addressFromDB = snapshot.child(userUsername).child("address").getValue(String.class);
                        String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);

                        Intent intent = new Intent(MunicipalUsersLoginActivity.this, MunicipalUsersHomeActivity.class);

                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("address", addressFromDB);
                        intent.putExtra("username", usernameFromDB);
                      //  intent.putExtra("password", nameFromDB);

                        startActivity(intent);
                    }
                    else {
                        loginPasswordEditText.setError("Invalid Credentials");
                        loginPasswordEditText.requestFocus();
                    }
                }
                else {
                    loginUsernameEditText.setError("User does not exists");
                    loginUsernameEditText.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}