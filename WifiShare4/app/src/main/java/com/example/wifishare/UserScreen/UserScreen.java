package com.example.wifishare.UserScreen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.wifishare.Login.LoginScreen;
import com.example.wifishare.ClassesLogic.User;
import com.example.wifishare.ClassesLogic.WiFi;
import com.example.wifishare.WiFiLists.WifiListScreen;

public class UserScreen extends AppCompatActivity {
    User user;
    String username = null;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_screen);

        final Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            username = bundle.getString("username");
        }

        user = LoginScreen.getUserByUsername(username);

        TextView textViewHello = findViewById(R.id.textViewHello);
        textViewHello.setText("Nice to see you " + username);


        Button buttonAddFriend = findViewById(R.id.buttonAddFriend);
        Button buttonAddWifi = findViewById(R.id.buttonAddWifi);
        Button buttonPublicWifi = findViewById(R.id.buttonPublicWifi);
        Button buttonPrivateWifi = findViewById(R.id.buttonPrivateWifi);
        Button buttonLogout = findViewById(R.id.buttonLogOut);

        buttonAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryAddFriend();
            }
        });

        buttonAddWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenAddWifiScreen();
            }
        });

        buttonPublicWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenWifiListScreen(true,username);
            }
        });

        buttonPrivateWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenWifiListScreen(false,username);
            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenLoginScreen();
            }
        });
    }

    private void tryAddFriend() {
        TextView textError = findViewById(R.id.textError);
        String tryToFind;
        EditText editTextAddFriend = findViewById(R.id.editTextAddFriend);
        tryToFind = editTextAddFriend.getText().toString();
        System.out.println("test " + tryToFind);

        for(User item : LoginScreen.UserList)
        {
            if(item.getUsername().equals(tryToFind) && !item.getUsername().equals(username))
            {
                if(user.getFriendList().contains(tryToFind))
                {
                    textError.setTextColor(Color.BLUE);
                    textError.setText("Friend already added");
                    return;
                }
                user.addFriend(tryToFind);
                textError.setTextColor(Color.GREEN);
                textError.setText("Friend added");
                return;
            }
        }
        textError.setTextColor(Color.RED);
        textError.setText("username not found");
    }

    private void OpenLoginScreen() {
        Intent intent = new Intent(this,LoginScreen.class);
        startActivity(intent);
    }

    private void OpenWifiListScreen(boolean bool, String username) {
        Intent intent = new Intent(this, WifiListScreen.class);
        intent.putExtra("ifPublic",bool);
        intent.putExtra("username",username);
        startActivity(intent);


    }

    private void OpenAddWifiScreen() {
        Intent intent = new Intent(this, AddWifiScreen.class);

        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == Activity.RESULT_OK)
        {
            WiFi temp = new WiFi(data.getStringExtra("name"),data.getStringExtra("password"),data.getStringExtra("description"),data.getBooleanExtra("ifPublic",false),
                    username);
            LoginScreen.WifiList.add(temp);
        }
    }


}
