package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class detailed_review extends AppCompatActivity {
    TextView login,rDesc,likes,dislikes;
    ImageView profilePic,delete,like,dislike;
    RatingBar rating;
    DatabaseReference dbref= FirebaseDatabase.getInstance().getReference("_user_");
    DatabaseReference ref=FirebaseDatabase.getInstance().getReference("_reviews_");
    String uPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_review);
        //        hide the actionbar
        getSupportActionBar().hide();
        final Review r=getIntent().getParcelableExtra("Review");
        login=findViewById(R.id.tv_detailedreview_login);
        rDesc=findViewById(R.id.review_desc);
        likes=findViewById(R.id.tv_review_likes2);
        dislikes=findViewById(R.id.tv_dislikes2);
        profilePic=findViewById(R.id.iv_detailedreview_pic);
        delete=findViewById(R.id.iv_delete);
        like=findViewById(R.id.iv_like);
        dislike=findViewById(R.id.iv_dislike);
        rating=findViewById(R.id.detailed_reviewRB);
        rating.setRating(r.getRating());
        rDesc.setText(r.getReview());
        likes.setText(String.valueOf(r.getLikes()));
        dislikes.setText(String.valueOf(r.getDislikes()));
//        dbref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dss : snapshot.getChildren()) {
//                    User u = dss.getValue(User.class);
//                    if (u.getEmail().equals(r.getReviewerMail()))
//                    {
//                        login.setText(u.getLogin());
//                        Picasso.get().load(u.getUrl()).into(profilePic) ;
//                        uPath=dss.getKey();
//
//                    }
//                    if(!(u.getEmail().equals(((logged)getApplication()).getLogged().getEmail()))&& u.getType()!=3)
//                        delete.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ok= true;
                if(ok)
                {
                    ref.child(r.path).child("Like").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

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
    }
}
