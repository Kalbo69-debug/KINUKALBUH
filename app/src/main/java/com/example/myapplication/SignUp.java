package com.example.myapplication;



import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


public class SignUp extends AppCompatActivity {


    EditText Username, Password, rePassword; // Added rePassword EditText
    Button SignupButton, SigninButton;
    DataBaseHelper MyDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Username = findViewById(R.id.Username);
        Password = findViewById(R.id.Password);
        rePassword = findViewById(R.id.rePassword); // Initialize rePassword EditText
        MyDatabase = new DataBaseHelper(this);
        SignupButton = findViewById(R.id.SignupButton);
        SigninButton = findViewById(R.id.Signin);


        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = Username.getText().toString();
                String pass = Password.getText().toString();
                String confirmPass = rePassword.getText().toString();


                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(confirmPass)) {
                    Toast.makeText(SignUp.this, "All fields are Required ", Toast.LENGTH_SHORT).show();
                } else if (!pass.equals(confirmPass)) {
                    Toast.makeText(SignUp.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkuser = MyDatabase.checkUsername(user);
                    if (!checkuser) {
                        Boolean insert = MyDatabase.insertData(user, pass);
                        if (insert) {
                            Toast.makeText(SignUp.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignUp.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignUp.this, "User already Existed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        SigninButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}

