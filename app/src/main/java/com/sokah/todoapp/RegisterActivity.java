package com.sokah.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText email,password,repeatPasswordRegister;
    private Button registerButton;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        database =FirebaseDatabase.getInstance();
        email=findViewById(R.id.emailRegister);
        password=findViewById(R.id.passwordRegister);
        repeatPasswordRegister=findViewById(R.id.repeatPasswordRegister);
        registerButton=findViewById(R.id.btnRegister);
        name=findViewById(R.id.name);

        registerButton.setOnClickListener((v) -> {

            // existe algún campo vacio
            if(name.getText().toString().isEmpty()||email.getText().toString().isEmpty()||password.getText().toString().isEmpty()||repeatPasswordRegister.getText().toString().isEmpty()){

                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            }
            else{



            //las contraseñas coinciden

            if(password.getText().toString().equals(repeatPasswordRegister.getText().toString())){

                String Semail,Spassword;
                Semail=email.getText().toString();
                Spassword=password.getText().toString();

            auth.createUserWithEmailAndPassword(Semail,Spassword).addOnCompleteListener(
                    (authResultTask)->{

                        Toast.makeText(this, "Succesfully registered", Toast.LENGTH_SHORT).show();
                        User tempUser = new User(auth.getUid(),name.getText().toString(),Semail);
                        database.getReference().child("Users").child(auth.getUid()).setValue(tempUser);
                        Intent intent = new Intent(this,HomeActivity.class);
                        startActivity(intent);


                    }
            );

            }
            else{


            }
            }
        });


    }
}