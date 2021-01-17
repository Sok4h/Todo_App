package com.sokah.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private TextView register;
    private EditText email,password;
    private Button login;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register = findViewById(R.id.registerText);
        email = findViewById(R.id.emailLogin);
        password = findViewById(R.id.passwordLogin);
        register.setOnClickListener(v -> Register());
        login = findViewById(R.id.btnLogin);
        auth=FirebaseAuth.getInstance();
        login.setOnClickListener(
                (v)->{

                if(email.getText().toString().isEmpty()||password.getText().toString().isEmpty()){

                    Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }

                else{

                    auth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(

                            (task)->{

                                if(task.isSuccessful()){

                                    Intent intent = new Intent(this,HomeActivity.class);
                                    startActivity(intent);
                                }

                                else{

                                    Toast.makeText(this, task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                    );
                }
                }
        );

    }

    public void Register(){

        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
}