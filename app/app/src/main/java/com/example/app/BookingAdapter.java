package com.example.app;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.Bookingholder>
    {
        public BookingAdapter(ArrayList<Booking> list) {
            this.list = list;
        }

        ArrayList<Booking> list;
        RecycleView_Adapter.holder.OnCardClickedListener listener;
        @NonNull
        @Override
        public Bookingholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
            Bookingholder h=new Bookingholder(v);
            return h;
        }

        public void onBindViewHolder(@NonNull Bookingholder hold, int position) {
            hold.tv.setText(list.get(position).getEatery());
            String Hour=(int)list.get(position).getHour()+":";
            if((int)list.get(position).getHour()==list.get(position).getHour())
                Hour+="00";
            else Hour+=(list.get(position).getHour()-(int)list.get(position).getHour());

            hold.tv2.setText("You have a booking for "+list.get(position).getEatery()+
                            " on "+list.get(position).getDay()+"/"+list.get(position).getMonth()+
                            "/"+list.get(position).getYear()+" set at "+Hour+" hours");
            hold.iv.setImageResource(R.drawable.menu);
            //Picasso.get().load((list.get(position)).getUrl()).fit().into(hold.iv);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public static class Bookingholder extends RecyclerView.ViewHolder
        {
            ImageView iv;
            TextView tv,tv2;

            RecycleView_Adapter.holder.OnCardClickedListener listener;

            public Bookingholder(@NonNull View itemView) {
                super(itemView);
                iv=itemView.findViewById(R.id.iv_card);
                tv=itemView.findViewById(R.id.tv_card);
                tv2=itemView.findViewById(R.id.tv_card2);

            }

        }


    }
