package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {

    ImageView iv, info, back;
    TextView name,desc;
    Button read,add, reservation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        //        hide the actionbar
        getSupportActionBar().hide();
        back = findViewById(R.id.back);
        info = findViewById(R.id.info);
        iv=findViewById(R.id.iv_details);
        name=findViewById(R.id.tv_name);
        desc=findViewById(R.id.tv_details);
        read=findViewById(R.id.btn_r);
        add=findViewById(R.id.btn_ar);
        reservation=findViewById(R.id.btn_res);
        final Eatery e=getIntent().getParcelableExtra("Eatery");
        Picasso.get().load(e.getUrl()).fit().into(iv);
        name.setText(e.getName());
        desc.setText(e.getDescription());

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

//        add reservation intent
        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Details.this,reservation.class);
                intent.putExtra("Eatery",e);
                startActivity(intent);
            }
        });
    }
}