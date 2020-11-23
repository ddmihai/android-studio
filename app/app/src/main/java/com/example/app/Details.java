package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    ImageView iv;
    TextView name,desc;
    Button read,add, reservation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        //        hide the actionbar
        getSupportActionBar().hide();
        iv=findViewById(R.id.iv_details);
        name=findViewById(R.id.tv_name);
        desc=findViewById(R.id.tv_details);
        read=findViewById(R.id.btn_r);
        add=findViewById(R.id.btn_ar);
        reservation=findViewById(R.id.btn_res);
        User u=getIntent().getParcelableExtra("User");
        //Picasso.get().load(list.get(position).getUrl()).fit().into(hold.iv);
        name.setText(u.getfName());
        desc.setText(u.getlName());
    }
}