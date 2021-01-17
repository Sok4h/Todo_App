package com.sokah.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private FirebaseAuth auth;
    private TextView name;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        name= findViewById(R.id.nameHome);
        String id = auth.getCurrentUser().getUid();

        database.getReference().child("Users").child(id).addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                            currentUser = snapshot.getValue(User.class);
                            Log.e("TAG", currentUser.getName());
                            name.setText(currentUser.getName());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                }

        );

    }
}