package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class settings extends AppCompatActivity {
    ImageView personal_settings, back;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //        hide the actionbar
        getSupportActionBar().hide();
        back = findViewById(R.id.back);
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

//        go back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), dashboard.class));
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
