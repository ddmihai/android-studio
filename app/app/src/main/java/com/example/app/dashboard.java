package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class dashboard extends AppCompatActivity {
    Button settings, e_restaurant, e_streetfood, logout, addRestaurant, addStreetfood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        //        hide the actionbar
        getSupportActionBar().hide();

        settings = findViewById(R.id.settings);
        e_restaurant = findViewById(R.id.e_restaurant);
        e_streetfood = findViewById(R.id.e_streetfood);
        logout = findViewById(R.id.logout);
        addRestaurant = findViewById(R.id.addRestaurant);
        addStreetfood = findViewById(R.id.addStreetfood);

        //        go to settings page
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), settings.class);
                startActivity(i);
            }
        });

//        explore restaurants
        e_restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), RecycleView.class));
            }
        });

//        explore street food
        e_streetfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), RecycleView.class));
            }
        });

//      logout btn
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getBaseContext(), MainActivity.class));
                Toast.makeText(getBaseContext(), "Logout Successful!", Toast.LENGTH_SHORT).show();
            }
        });

//        add restaurant
        addRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), add_restaurant.class));
            }
        });


//        add street food
        addStreetfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), add_restaurant.class));
            }
        });

    }
}
