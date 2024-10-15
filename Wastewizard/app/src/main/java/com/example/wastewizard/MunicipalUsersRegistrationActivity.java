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

public class MunicipalUsersRegistrationActivity extends AppCompatActivity {

    EditText nameEditText, usernameEditText, addressEditText, passwordEditText;
    Button registerButton;
    TextView loginRedirectTextView;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipal_users_registration);

        nameEditText=findViewById(R.id.nameEditText);
        addressEditText=findViewById(R.id.addressEditText);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerButton = findViewById(R.id.registerButton);
        loginRedirectTextView= findViewById(R.id.loginRedirectTextView);

        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                database= FirebaseDatabase.getInstance();
                reference=database.getReference("users");

                String name = nameEditText.getText().toString();
                String address = addressEditText.getText().toString();
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                users user = new users(name, address, username, password);
                reference.child(username).setValue(user);

                Toast.makeText(MunicipalUsersRegistrationActivity.this, "You have registered successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MunicipalUsersRegistrationActivity.this, MunicipalUsersHomeActivity.class);
                startActivity(intent);
            }
        });

        loginRedirectTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MunicipalUsersRegistrationActivity.this, MunicipalUsersLoginActivity.class);
                startActivity(intent);

            }
        });
    }
}
