package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;

public class add_reviews extends AppCompatActivity {
    ImageView header,userPic;
    TextView eName,eLocation,uName,uType;
    Button addR;
    EditText addReview;
    RatingBar rating;
    String path;
    float ratingVal;
    DatabaseReference dbref= FirebaseDatabase.getInstance().getReference("Eatery");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reviews);
        //        hide the actionbar
        getSupportActionBar().hide();
        final User u=((logged) getApplication()).getLogged();
        final Eatery e=getIntent().getParcelableExtra("Eatery");
        header=findViewById(R.id.iv_review_eatery);
        userPic=findViewById(R.id.iv_review_user);
        eName=findViewById(R.id.tv_eatery_name);
        eLocation=findViewById(R.id.tv_eatery_location);
        uName=findViewById(R.id.tv_review_username);
        uType=findViewById(R.id.tv_user_rank);
        addReview=findViewById(R.id.et_review);
        rating=findViewById(R.id.ratingBar);
        addR=findViewById(R.id.btn_add_review);

        Picasso.get().load(e.getUrl()).fit().into(header);
        eName.setText(e.getName());
        eLocation.setText(e.getLocation());
        uName.setText(u.getLogin());
        switch (u.getType()){
            case 1: {uType.setText("Standard User");break;}
            case 2: {uType.setText("Food Critic");break;}
            case 3: {uType.setText("Administrator");break;}
        }
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Eatery eatery = ds.getValue(Eatery.class);
                    if (eatery.getName().equals(e.getName())&&eatery.getLocation().equals(e.getLocation()))
                    {
                        path = ds.getKey();
                        break;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        final DatabaseReference ref=FirebaseDatabase.getInstance().getReference("_reviews_");
        Picasso.get().load(u.getUrl()).fit().into(userPic);

//        Date reviewTime= Calendar.getInstance().getTime();
//        reviewTime.getTime();
//        Toast.makeText(add_reviews.this,reviewTime.toString(),Toast.LENGTH_LONG).show();
        //rating bar
        addR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reviewText=addReview.getText().toString().trim();
                if(TextUtils.isEmpty(reviewText)) {addReview.setError("Please add your review !");return;}
                ratingVal=rating.getRating();
                if(ratingVal==0)
                    Toast.makeText(add_reviews.this,"Please select a rating !",Toast.LENGTH_SHORT).show();
                else
                {
//                    Date reviewTime= Calendar.getInstance().getTime();
//                    reviewTime.getTime();
                    String npath=ref.push().getKey();
                    Review review=new Review(reviewText, FirebaseAuth.getInstance().getCurrentUser().getUid(),e.getName(),ratingVal,0,0,npath);
                    dbref.child(path).child("rating").setValue(e.getRating()+ratingVal);
                    dbref.child(path).child("ratingNr").setValue(e.getRatingNr()+1);
                    ref.child(npath).setValue(review);
                    Toast.makeText(add_reviews.this,"Review Added!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getBaseContext(), dashboard.class));
                    finish();

                }
            }
        });
    }
}
