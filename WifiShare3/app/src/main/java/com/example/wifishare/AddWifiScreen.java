package com.example.wifishare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;

public class AddWifiScreen extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wifi_screen);
        //final List<WiFi> wiFiList = (List<WiFi>) getIntent().getSerializableExtra("WiFiList");

        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText entryName = findViewById(R.id.textWifiName);
                EditText entryPassword = findViewById(R.id.textWifiPassword);
                EditText entryDesription = findViewById(R.id.DetailsDescription);
                RadioGroup radioGroup = findViewById(R.id.buttonGroup);


                String name = entryName.getText().toString();
                String password = entryPassword.getText().toString();
                String description = entryDesription.getText().toString();

                if(name.length() < 3 || password.length() < 6)
                {
                    Alert();
                }
                else
                {
                    int id = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(id);

                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK,returnIntent);

                    returnIntent.putExtra("name",name);
                    returnIntent.putExtra("password",password);
                    returnIntent.putExtra("description",description);

                    if(radioButton.getText().equals("Public"))
                    {
                        returnIntent.putExtra("ifPublic",true);
                    }

                    else
                    {
                        returnIntent.putExtra("ifPublic",false);
                    }

                    finish();
                }

            }
        });
    }

    private void Alert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Too short username or password").create();
        builder.show();
    }
}
