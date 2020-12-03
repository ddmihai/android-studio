package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class user_profile extends AppCompatActivity {
    TextView name, login, email, level;
    ImageView profilePic;
    DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("_user_");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        hide the actionbar
        getSupportActionBar().hide();
        final User u = getIntent().getParcelableExtra("User");
        setContentView(R.layout.activity_user_profile);
        int nrLikes=getIntent().getIntExtra("nrLikes",0);
        int nrDislikes=getIntent().getIntExtra("nrDislikes",0);
        name = findViewById(R.id.tv_userprofile_name);
        login = findViewById(R.id.tv_userprofile_login);
        email = findViewById(R.id.tv_userprofile_email);
        level = findViewById(R.id.tv_userprofile_level);
        profilePic = findViewById(R.id.iv_userprofile_pic);
        name.setText(u.getfName() + " " + u.getlName());
        login.setText(u.getLogin());
        email.setText(u.getEmail());
        switch (u.getType()) {
            case 1: {
                level.setText("Standard User");
                break;
            }
            case 2: {
                level.setText("Food Critic");
                break;
            }
            case 3: {
                level.setText("Administrator");
                break;
            }
        }
        Picasso.get().load(u.getUrl()).into(profilePic);

    }
}



