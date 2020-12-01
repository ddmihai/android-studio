package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {

    ImageView iv, info, back;
    TextView name,specifc,location,desc;
    Button read,add, reservation;
    RatingBar rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        //        hide the actionbar
        getSupportActionBar().hide();
        back = findViewById(R.id.back);
        info = findViewById(R.id.info);
        iv=findViewById(R.id.iv_details);
        name=findViewById(R.id.tv_details_name);
        specifc=findViewById(R.id.tv_details_specific);
        location=findViewById(R.id.tv_details_location);
        desc=findViewById(R.id.tv_details);
        rating=findViewById(R.id.ratingBar2);
        read=findViewById(R.id.btn_r);
        add=findViewById(R.id.btn_ar);
        reservation=findViewById(R.id.btn_res);
        final Eatery e=getIntent().getParcelableExtra("Eatery");
        Picasso.get().load(e.getUrl()).fit().into(iv);
        name.setText(e.getName());
        specifc.setText(e.getServing());
        location.setText(e.getLocation());
        desc.setText(e.getDescription());
        rating.setRating(e.getRating()/e.getRatingNr());

//        get info page
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), info.class));
            }
        });
//        back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), RecycleView.class));
            }
        });
        if(((logged) getApplication()).getLogged().getType()==1)
            add.setVisibility(View.GONE);

//        add reservation intent
        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Details.this,reservation.class);
                intent.putExtra("Eatery",e);
                startActivity(intent);
            }
        });

//        add review
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), add_reviews.class);
                i.putExtra("Eatery",e);
                startActivity(i);

            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getBaseContext(),RecycleView.class);
                i.putExtra("Path","_reviews_");
                i.putExtra("Code",3);
                i.putExtra("Eatery",e);
                startActivity(i);
            }
        });
    }
}