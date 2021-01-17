package com.sokah.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class HomeActivity extends AppCompatActivity implements FolderDialog.DialogListener {

    private FirebaseDatabase database;
    private FirebaseAuth auth;
    private TextView name;
    private User currentUser;
    private Button createFolder;
    private GridView gridView;
    private FolderAdapter folderAdapter;
    private ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        name= findViewById(R.id.nameHome);
        String id = auth.getCurrentUser().getUid();
        createFolder = findViewById(R.id.btnCreateFolder);
        folderAdapter = new FolderAdapter();
        gridView=findViewById(R.id.grid);
        gridView.setAdapter(folderAdapter);

        database.getReference().child("Users").child(id).addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                            currentUser = snapshot.getValue(User.class);
                            Log.e("TAG", currentUser.getName());
                            name.setText(currentUser.getName());
                        LoadDatabase();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                }

        );
        createFolder.setOnClickListener(v -> {

            CreateDialog();
        });
    }

    public void CreateDialog() {
        FolderDialog folderDialog = new FolderDialog();
        folderDialog.show(getSupportFragmentManager(),"dialog");
    }

    @Override
    public void CreateFolder(String folderName) {
        String id =UUID.randomUUID().toString();
    Folder tempFolder = new Folder(id,currentUser.getId(),folderName);
    database.getReference().child("Folders").child(id).setValue(tempFolder);
    }

    public void LoadDatabase(){

        valueEventListener= database.getReference().child("Folders").orderByChild("userId").equalTo(currentUser.getId()).addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        folderAdapter.clear();
                        for (DataSnapshot children: snapshot.getChildren()) {

                            Folder folder = children.getValue(Folder.class);
                            folderAdapter.AddFolder(folder);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
        );
    }
}