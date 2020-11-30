package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class personalSettingsAdmin extends AppCompatActivity {
    ImageView personal_settings, back, admin_settings,admin_bookings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_settings_admin);
        //        hide the actionbar
        getSupportActionBar().hide();
        personal_settings = findViewById(R.id.admin_personal_settings);
        back = findViewById(R.id.back);
        admin_settings = findViewById(R.id.admin_settings);
        admin_bookings=findViewById(R.id.admin_bookings);

//        go to admin settings
        admin_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), admin.class));
            }
        });
//        go back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), settings.class));
            }
        });

        personal_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), personalsettings.class));
            }
        });
        admin_bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), RecycleView.class);
                i.putExtra("Path","_bookings_");
                i.putExtra("Code",2);
                startActivity(i);
            }
        });

    }
}
