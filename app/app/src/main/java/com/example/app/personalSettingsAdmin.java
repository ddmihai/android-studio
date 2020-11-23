package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class personalSettingsAdmin extends AppCompatActivity {
    ImageView personal_settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_settings_admin);
        personal_settings = findViewById(R.id.personal_settings);


    }
}
