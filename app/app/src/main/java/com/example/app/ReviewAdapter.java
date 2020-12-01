package com.example.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.Reviewholder> {
    public ReviewAdapter(ArrayList<Review> list,User logged, ReviewAdapter.Reviewholder.OnCardClickedListener _listener) {
        this.list = list;
        log=logged;
        listener=_listener;

    }
    User log;
    ArrayList<Review> list;
    ReviewAdapter.Reviewholder.OnCardClickedListener listener;
    DatabaseReference dbref= FirebaseDatabase.getInstance().getReference("_user_");

    @NonNull
    @Override
    public ReviewAdapter.Reviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.read_review_card,parent,false);
        ReviewAdapter.Reviewholder h=new ReviewAdapter.Reviewholder(v,listener);
        return h;
    }

    @Override
    public void onBindViewHolder(@NonNull final ReviewAdapter.Reviewholder hold, final int position) {

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dss : snapshot.getChildren()) {
                    User u = dss.getValue(User.class);
                    if (u.getEmail().equals(list.get(position).getReviewerMail()))
                    {
                        hold.tv.setText(u.getLogin());
                        hold.tv2.setText(list.get(position).getReview());
                        Picasso.get().load(u.getUrl()).fit().into(hold.iv);
                        hold.rating.setRating(list.get(position).getRating());
                        //((logged) Context.getApplicationContext()).getMy_id();
//                        if((!u.getEmail().equals(log.getEmail()))&& log.getType()!=3)
//                            hold.delete.setVisibility(View.GONE);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class Reviewholder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView iv;
        TextView tv,tv2;
        RatingBar rating;

        ReviewAdapter.Reviewholder.OnCardClickedListener listener;

        public Reviewholder(@NonNull View itemView, ReviewAdapter.Reviewholder.OnCardClickedListener _listener) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv_card);
            tv=itemView.findViewById(R.id.tv_card);
            tv2=itemView.findViewById(R.id.tv_card2);
            rating=itemView.findViewById(R.id.ratingBar3);
            listener=_listener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            listener.OnCardClickedListener(getAdapterPosition());
        }
        public interface OnCardClickedListener
        {
            public void OnCardClickedListener(int i);
        }

    }

}

