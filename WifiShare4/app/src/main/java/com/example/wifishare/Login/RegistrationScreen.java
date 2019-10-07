package com.example.wifishare.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.wifishare.ClassesLogic.User;

public class RegistrationScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);
        Button buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = findViewById(R.id.textFieldUsername);
                EditText password = findViewById(R.id.textFieldPassword);
                LoginScreen.UserList.add(new User(username.getText().toString(),password.getText().toString()));
                GoBackToSignIn();
            }
        });
    }

    private void GoBackToSignIn() {
        Intent intent = new Intent(this,LoginScreen.class);
        startActivity(intent);
    }
}
