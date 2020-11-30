package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class dashboard extends AppCompatActivity {
    Button settings, e_restaurant, e_streetfood, logout, addRestaurant, addStreetfood;
    TextView fullname,type;
    ImageView avatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        //        hide the actionbar
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("_user_");
        getSupportActionBar().hide();
        User User=((logged) getApplication()).getLogged();//getting the user type value through the class that extends app
        settings = findViewById(R.id.settings);
        e_restaurant = findViewById(R.id.e_restaurant);
        e_streetfood = findViewById(R.id.e_streetfood);
        logout = findViewById(R.id.logout);
        addRestaurant = findViewById(R.id.addRestaurant);
        addStreetfood = findViewById(R.id.addStreetfood);
        fullname=findViewById(R.id.tv_fullname);
        type=findViewById(R.id.tv_type);
        avatar=findViewById(R.id.avatar);
        fullname.setText(User.getfName()+" "+User.getlName());
        switch (User.getType()){
            case 1: {type.setText("Standard User");break;}
            case 2: {type.setText("Food Critic");break;}
            case 3: {type.setText("Administrator");break;}
        }
        Picasso.get().load(User.getUrl()).fit().into(avatar);
        if(User.getType()==1)
            addRestaurant.setVisibility(View.INVISIBLE);

        //        go to settings page
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((logged) getApplication()).getLogged().getType()==1) {
                    Intent i = new Intent(getBaseContext(), personalsettings.class);
                    startActivity(i);
                }
                else if(((logged) getApplication()).getLogged().getType()==3)
                {
                    Intent i = new Intent(getBaseContext(), personalSettingsAdmin.class);
                    startActivity(i);
                }
            }


        });

//        explore restaurants
        e_restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getBaseContext(),RecycleView.class);
                i.putExtra("Path","Eatery");
                i.putExtra("Code",1);
                i.putExtra("Type","Restaurant");
                i.putExtra("Header",1);
                startActivity(i);

            }
        });

//        explore street food
        e_streetfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getBaseContext(),RecycleView.class);
                i.putExtra("Path","Eatery");
                i.putExtra("Code",1);
                i.putExtra("Type","Street Food");
                i.putExtra("Header",2);
                startActivity(i);
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
                Intent i =new Intent(getBaseContext(),add_eatery.class);
                i.putExtra("Type","Restaurant");
                startActivity(i);
            }
        });


//        add street food
        addStreetfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getBaseContext(),add_eatery.class);
                i.putExtra("Type","Street Food");
                startActivity(i);
            }
        });

    }
}
