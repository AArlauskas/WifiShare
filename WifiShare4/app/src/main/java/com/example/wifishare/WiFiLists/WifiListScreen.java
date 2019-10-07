package com.example.wifishare.WiFiLists;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.wifishare.ClassesLogic.WiFi;
import com.example.wifishare.Login.LoginScreen;

import java.util.ArrayList;
import java.util.List;

public class WifiListScreen extends AppCompatActivity {

    ArrayList<String> names = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_list_screen);

        List<WiFi> wiFiList = new ArrayList<>();

        Boolean bool = getIntent().getBooleanExtra("ifPublic",false);
        String username = getIntent().getStringExtra("username");

        if(bool)
        {
            for(WiFi item : LoginScreen.WifiList)
            {
                if(item.isSharable())
                {
                    wiFiList.add(item);
                }
            }
        }
        else
        {
            for(WiFi item : LoginScreen.WifiList)
            {
                if(item.getCreator().equals(username) && !item.isSharable() || LoginScreen.getUserByUsername(username).getFriendList().contains(item.getCreator()))
                {
                    wiFiList.add(item);
                }
            }
        }

        for (WiFi item : wiFiList)
        {
            names.add(item.getName());
        }

        System.out.println(names);
        final ListView WiFiList = findViewById(R.id.WiFiList);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,names);
        WiFiList.setAdapter(adapter);

        WiFiList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = names.get(position);
                int index = LoginScreen.indexOfName(name);
                OpenWifiDetails(index);

            }
        });
    }

    private void OpenWifiDetails(int index) {
        Intent intent = new Intent(this,WiFiDetails.class);
        WiFi wifi = LoginScreen.getList().get(index);

        intent.putExtra("name",wifi.getName());
        intent.putExtra("password",wifi.getPassword());
        intent.putExtra("description",wifi.getDescription());
        intent.putExtra("ifPublic",wifi.isSharable());

        System.out.println(intent.getStringExtra("description"));

        startActivity(intent);



    }
}
