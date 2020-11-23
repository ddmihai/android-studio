package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class personalsettings extends AppCompatActivity {
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalsettings);
        //        hide the actionbar
        getSupportActionBar().hide();

        back = findViewById(R.id.back);


//        go back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),settings.class ));
            }
        });

    }

}
//agapie@gmail.com
//iulian