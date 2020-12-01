package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
TextView name,login,email,level;
ImageView profilePic;
DatabaseReference dbref= FirebaseDatabase.getInstance().getReference("_user_");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Review r=getIntent().getParcelableExtra("Review");
        setContentView(R.layout.activity_user_profile);
        name=findViewById(R.id.tv_userprofile_name);
        login=findViewById(R.id.tv_userprofile_login);
        email=findViewById(R.id.tv_userprofile_email);
        level=findViewById(R.id.tv_userprofile_level);
        profilePic=findViewById(R.id.iv_userprofile_pic);
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dss : snapshot.getChildren()) {
                    User u = dss.getValue(User.class);
                    if (u.getEmail().equals(r.getReviewerMail()))
                    {
                        name.setText(u.getfName()+" "+u.getlName());
                        login.setText(u.getLogin());
                        email.setText(u.getEmail());
                        switch (u.getType()){
                            case 1: {level.setText("Standard User");break;}
                            case 2: {level.setText("Food Critic");break;}
                            case 3: {level.setText("Administrator");break;}
                        }
                        Picasso.get().load(u.getUrl()).into(profilePic) ;
                        break;

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
