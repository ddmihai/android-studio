package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class settings extends AppCompatActivity {
    ImageView personal_settings;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //        hide the actionbar
        getSupportActionBar().hide();

        logout = findViewById(R.id.logout);
        personal_settings = findViewById(R.id.personal_settings);

        //    go to personal settings
        personal_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), personalsettings.class);
                startActivity(i);
            }
        });

//              logout
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getBaseContext(), MainActivity.class);
                }
            });

    }
}
