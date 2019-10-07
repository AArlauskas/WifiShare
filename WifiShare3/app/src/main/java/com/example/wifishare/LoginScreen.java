package com.example.wifishare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button buttonSignIn = findViewById(R.id.buttonSignIn);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textUsername = findViewById(R.id.textUsername);
                EditText textPassword = findViewById(R.id.PasswordDetails);

                String username = textUsername.getText().toString();
                String password = textPassword.getText().toString();

                if (username.equals("admin") && password.equals("admin"))
                {
                    openUserScreen(username);
                }

                else
                {
                    Alert();
                }


            }
        });

        Button buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegistrationScreen();
            }
        });


    }

    private void Alert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Wrong username or password").create();
        builder.show();
    }

    private void openUserScreen(String username) {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(this,UserScreen.class);
        bundle.putString("username",username);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    private void openRegistrationScreen() {
        Intent intent = new Intent(this,RegistrationScreen.class);
        startActivity(intent);
    }
}
