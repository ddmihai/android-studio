package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class info extends AppCompatActivity {
    ImageView back;
    Button logout;
    @Override
                            //    Delete reviews
                            //    Eateries unicate
                            //    Update firebase on email update
                            //    ora fututa la booking
                            //    sortari in recycle views
                            //    users rate reviews
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        //        hide the actionbar
        getSupportActionBar().hide();

        back = findViewById(R.id.back);
        logout= findViewById(R.id.logout);
//        go back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Details.class));
            }
        });

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
