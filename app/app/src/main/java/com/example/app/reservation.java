package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class reservation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        //        hide the actionbar
        getSupportActionBar().hide();
    }
}
