package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class detailed_review extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_review);
        //        hide the actionbar
        getSupportActionBar().hide();
    }
}
