package com.example.wifishare.Login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.wifishare.ClassesLogic.User;
import com.example.wifishare.UserScreen.UserScreen;
import com.example.wifishare.ClassesLogic.WiFi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class LoginScreen extends AppCompatActivity {
    public static List<WiFi> WifiList = new ArrayList<>();
    public static List<User> UserList = new ArrayList<>();

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

                for(User item : UserList)
                {
                    if (username.equals(item.getUsername()) && password.equals(item.getPassword()))
                    {
                        openUserScreen(username);
                    }

                    else
                    {
                        Alert();
                    }
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
        Intent intent = new Intent(this, UserScreen.class);
        bundle.putString("username",username);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    private void openRegistrationScreen() {
        Intent intent = new Intent(this, RegistrationScreen.class);
        startActivity(intent);
        UserList.add(new User("hey","hey"));
    }

    public static List<WiFi> getList()
    {
        return WifiList;
    }

    public static int indexOfName(String name)
    {
        for (WiFi item : WifiList)
        {
            if(item.getName().equals(name))
            {
                return WifiList.indexOf(item);
            }
        }

        return -1;
    }

    public static User getUserByUsername(String username)
    {
        for(User item : UserList)
        {
            if(item.getUsername().equals(username))
            {
                return item;
            }
        }
        return null;

    }


}
