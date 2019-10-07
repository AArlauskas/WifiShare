package com.example.wifishare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WiFiDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wi_fi_details);

        TextView textName = findViewById(R.id.NameDetails);
        TextView textPassword = findViewById(R.id.PasswordDetails);
        TextView textViewStatus = findViewById(R.id.AvailabilityDetails);
        TextView textDescription = findViewById(R.id.DetailsDescription);



        Intent intent = new Intent();
        System.out.println(intent.getStringExtra("description"));


        textName.setText(getIntent().getStringExtra("name"));
        textPassword.setText(getIntent().getStringExtra("password"));
        textDescription.setText(getIntent().getStringExtra("description"));

        if (getIntent().getBooleanExtra("ifPublic",false))
        {
            textViewStatus.setText("Public");
        }

        else textViewStatus.setText("Private");
    }
}
