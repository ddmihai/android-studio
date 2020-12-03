package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

public class detailed_review extends AppCompatActivity {
    TextView login, rDesc, likes, dislikes;
    ImageView profilePic, delete, like, dislike;
    RatingBar rating;
    DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("_user_");
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("_reviews_");
    DatabaseReference eref = FirebaseDatabase.getInstance().getReference("Eatery");
    String uPath, ePath;
    Button profile;

    Eatery reviewedE;
    ArrayList<String> Like = new ArrayList<>();
    ArrayList<String> Dislike = new ArrayList<>();
    ArrayList<User> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_review);
        //        hide the actionbar
        getSupportActionBar().hide();
        final Review r = getIntent().getParcelableExtra("Review");
        final User log=((logged)getApplication()).getLogged();
        profile = findViewById(R.id.btn_profile);
        login = findViewById(R.id.tv_detailedreview_login);
        rDesc = findViewById(R.id.review_desc);
        likes = findViewById(R.id.tv_review_likes2);
        dislikes = findViewById(R.id.tv_dislikes2);
        profilePic = findViewById(R.id.iv_detailedreview_pic);
        delete = findViewById(R.id.iv_delete);
        like = findViewById(R.id.iv_like);
        dislike = findViewById(R.id.iv_dislike);
        rating = findViewById(R.id.detailed_reviewRB);
        rating.setRating(r.getRating());
        rDesc.setText(r.getReview());
        likes.setText(String.valueOf(r.getLikes()));
        dislikes.setText(String.valueOf(r.getDislikes()));
        eref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dss : snapshot.getChildren()) {
                    Eatery e = dss.getValue(Eatery.class);
                    if (e.getName().equals(r.getEateryName())) {
                        reviewedE = e;
                        ePath = dss.getKey();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dss : snapshot.getChildren()) {
                    {
                        Log.d("detailed_review","Back here");
                        User u = dss.getValue(User.class);
                        if (u.getUid().equals(r.getReviewerID())) {
                            login.setText(u.getLogin());
                            Picasso.get().load(u.getUrl()).into(profilePic);
                        }
                        if(u.getUid().equals(log.getUid()))
                            uPath=u.getUid();
                        if (!(u.getUid().equals(log.getUid())) && log.getType() != 3)
                            delete.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dss : snapshot.getChildren()) {
//                    for (DataSnapshot ds : dss.child("Like").getChildren()) {
//                        Like.add(ds.getValue(String.class));
//                        Like.add(ds.getKey());
//
//
//                    }
//                    for (DataSnapshot ds : dss.child("Dislike").getChildren()) {
//                        Dislike.add(ds.getValue(String.class));
//                        Dislike.add(ds.getKey());
//
//
//                    }
//                }
//        }
//
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), user_profile.class);
                i.putExtra("User", log);
                startActivity(i);

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eref.child(ePath).child("rating").setValue(reviewedE.getRating() - r.getRating());
                eref.child(ePath).child("ratingNr").setValue(reviewedE.getRatingNr() - 1);
                ref.child(r.getPath()).removeValue();
                Toast.makeText(detailed_review.this, "Review removed !", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getBaseContext(), dashboard.class));
            }
        });
        like.setOnClickListener(new View.OnClickListener() {

            boolean exists = false;
            String npath;
            @Override
            public void onClick(View v) {
                if (Like.size() == 0) {
                    ref.child(r.getPath()).child("likes").setValue(r.getLikes() + 1);
                    r.setLikes(r.getLikes() + 1);
                    npath=ref.push().getKey();
                    ref.child(r.getPath()).child("Like").child(npath).setValue(uPath);
                    likes.setText(String.valueOf(r.getLikes()));
                    Like.add(uPath);
                    Like.add(npath);
                    exists = true;
                } else for (int i = 0; i < Like.size(); i += 2) {
                    if (Like.get(i).equals(log.getUid())) {
                        ref.child(r.getPath()).child("likes").setValue(r.getLikes() - 1);
                        r.setLikes(r.getLikes() - 1);
                        ref.child(r.getPath()).child("Like").child(Like.get(i+1)).removeValue();
                        Like.remove(i);
                        Like.remove(i);
                        likes.setText(String.valueOf(r.getLikes()));
                        exists = true;
                    }
                }
                if (exists == false) {
                    ref.child(r.getPath()).child("likes").setValue(r.getLikes() + 1);
                    r.setLikes(r.getLikes() + 1);
                    npath=ref.push().getKey();
                    ref.child(r.getPath()).child("Like").child(npath).setValue(uPath);
                    Like.add(uPath);
                    Like.add(npath);
                    likes.setText(String.valueOf(r.getLikes()));

                }
            }
            // Toast.makeText(detailed_review.this, "Thank you !", Toast.LENGTH_SHORT).show();

        });

        dislike.setOnClickListener(new View.OnClickListener() {
            boolean exists = false;
            String npath;
            @Override
            public void onClick(View v) {
                if (Dislike.size() == 0) {
                    ref.child(r.getPath()).child("dislikes").setValue(r.getDislikes() + 1);
                    r.setDislikes(r.getDislikes() + 1);
                    npath=ref.push().getKey();
                    ref.child(r.getPath()).child("Dislike").child(npath).setValue(uPath);
                    dislikes.setText(String.valueOf(r.getDislikes()));
                    Dislike.add(uPath);
                    Dislike.add(npath);
                    exists = true;
                } else for (int i = 0; i < Dislike.size(); i += 2) {
                    if (Dislike.get(i).equals(log.getUid())) {
                        ref.child(r.getPath()).child("dislikes").setValue(r.getDislikes() - 1);
                        r.setDislikes(r.getDislikes() - 1);
                        ref.child(r.getPath()).child("Dislike").child(Dislike.get(i+1)).removeValue();
                        Dislike.remove(i);
                        Dislike.remove(i);
                        dislikes.setText(String.valueOf(r.getDislikes()));
                        exists = true;
                    }
                }
                if (exists == false) {
                    ref.child(r.getPath()).child("dislikes").setValue(r.getDislikes() + 1);
                    r.setDislikes(r.getDislikes() + 1);
                    npath=ref.push().getKey();
                    ref.child(r.getPath()).child("Dislike").child(npath).setValue(uPath);
                    Dislike.add(uPath);
                    Dislike.add(npath);
                    dislikes.setText(String.valueOf(r.getDislikes()));

                }

            }
//

        });


    }
}


//        like.setOnClickListener(new View.OnClickListener() {
//            String  mail,npath;
//            int ok=1;
//            @Override
//            public void onClick(View v) {
//                ref.child(path).child("Like").addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for(DataSnapshot dss:snapshot.getChildren()) {
//                            mail = dss.getValue(String.class);
//                            if(mail.equals(((logged)getApplication()).getLogged().getEmail()))
//                            {
//                                ok=0;
//                                npath = dss.getKey();
//
//                            }
//
//                        }
//
//
//                    }
//
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//                if(ok==1)
//                {
//                    ref.child(path).child("likes").setValue(r.getLikes()+1);
//                    r.setLikes(r.getLikes()+1);
//                    likes.setText(String.valueOf(r.getLikes()));
//                    ref.child(path).child("Like").child(ref.push().getKey()).setValue(((logged)getApplication()).getLogged().getEmail());
//                    return;
//                }
//                else if(ok==0) {
//
//                    ref.child(path).child("likes").setValue(r.getLikes() - 1);
//                    r.setLikes(r.getLikes() - 1);
//                    likes.setText(String.valueOf(r.getLikes()));
//                    ref.child(path).child("Like").child(npath).removeValue();
//                    ok=1;
//                    return;
//                }
//            }
//        });
