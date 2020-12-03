package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class settings extends AppCompatActivity {
    ImageView personal_settings, bookings;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //        hide the actionbar
        getSupportActionBar().hide();
        logout = findViewById(R.id.logout_settings);
        personal_settings = findViewById(R.id.personal_settings);
        bookings = findViewById(R.id.your_bookings);

        //    go to personal settings
        personal_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), personalsettings.class);
                startActivity(i);
            }
        });
        bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), RecycleView.class);
                i.putExtra("Path", "_bookings_");
                i.putExtra("Code", 2);
                startActivity(i);
            }
        });

//

//              logout
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                Toast.makeText(getBaseContext(), "Logout Successful!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
