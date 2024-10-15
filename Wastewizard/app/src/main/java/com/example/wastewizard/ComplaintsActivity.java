package com.example.wastewizard;

import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import java.util.ArrayList;

public class ComplaintsActivity extends AppCompatActivity {

    private EditText complaintEditText;
    ListView listView;

    TextView userName, userUsername, userAddress;
    private Button homeButton, garbageCollectorButton, zeroWastePageButton, virtualSwapButton, complainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);

        // Initialize views


        listView = findViewById(R.id.listView);

        //showUserData();

        ArrayList<String> list = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.list_item, list);
        listView.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("complaints");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot  : dataSnapshot.getChildren()){
                    list.add(snapshot.getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

   /* public void showUserData(){
        Intent intent = getIntent();

        String nameUser = intent.getStringExtra("name");
        String usernameUser = intent.getStringExtra("username");
        String addressUser = intent.getStringExtra("address");
        String passwordUser = intent.getStringExtra("password");

        userName.setText(nameUser);
        userAddress.setText(addressUser);
        userUsername.setText(usernameUser);

    }*/



}
