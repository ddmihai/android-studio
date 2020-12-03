package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class info extends AppCompatActivity {

    Button logout;

    @Override
    //    Delete reviews
    //rate reviews of critics

    //Create Users
    //-Normal User
    //-Promote at least two critics
    //
    //Create some bookings
    //
    //Create some review Street Food and Restaurant
    //
    //Create Restaurants and Street Food
    //
    //
    //User-
    //*	Normal sarrina@gmail.com	sarrina
    //*	Normal raffa2020@gmail.com	rafael
    //*	Normal chesar@gmail.com		chesar
    //*	Normal steel@gmail.com		jamessteel	Promoted to Critic
    //*	Normal serghey@gmail.com 	serghey		Promoted to Critic
    //*	Normal	grey@gmail.com		greysimon
    //*	Normal  singh@yahoo.com		singhk
    //
    //Bugs :
    //when allready logged in agapie@gmail.com, from the dashboard i pressed back and i've got the dashboard from
    //the new user (sarrina)
    //    users rate reviews
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        //        hide the actionbar
        getSupportActionBar().hide();
        logout = findViewById(R.id.logout);


//        logout button
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getBaseContext(), MainActivity.class));
            }
        });
    }
}
