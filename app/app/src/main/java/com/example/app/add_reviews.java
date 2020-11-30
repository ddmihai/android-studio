package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class add_reviews extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reviews);
        //        hide the actionbar
        getSupportActionBar().hide();
    }
}
