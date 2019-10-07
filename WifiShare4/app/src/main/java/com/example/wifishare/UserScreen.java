package com.example.wifishare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserScreen extends AppCompatActivity {
    public static List<WiFi> WifiList = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_screen);

        WifiList = new ArrayList<>();

        final Bundle bundle = getIntent().getExtras();
        String username = null;
        if (bundle != null) {
            username = bundle.getString("username");
        }

        TextView textViewHello = findViewById(R.id.textViewHello);
        textViewHello.setText("Nice to see you " + username);

        Button buttonAddWifi = findViewById(R.id.buttonAddWifi);
        Button buttonPublicWifi = findViewById(R.id.buttonPublicWifi);
        Button buttonPrivateWifi = findViewById(R.id.buttonPrivateWifi);
        Button buttonLogout = findViewById(R.id.buttonLogOut);

        buttonAddWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenAddWifiScreen();
            }
        });

        buttonPublicWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenWifiListScreen(true);
            }
        });

        buttonPrivateWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenWifiListScreen(false);
            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenLoginScreen();
            }
        });
    }

    private void OpenLoginScreen() {
        Intent intent = new Intent(this,LoginScreen.class);
        startActivity(intent);
    }

    private void OpenWifiListScreen(boolean bool) {
        Intent intent = new Intent(this,WifiListScreen.class);
        intent.putExtra("ifPublic",bool);
        startActivity(intent);


    }

    private void OpenAddWifiScreen() {
        Intent intent = new Intent(this,AddWifiScreen.class);

        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == Activity.RESULT_OK)
        {
            WiFi temp = new WiFi(data.getStringExtra("name"),data.getStringExtra("password"),data.getStringExtra("description"),data.getBooleanExtra("ifPublic",false));
            WifiList.add(temp);
        }
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
}
