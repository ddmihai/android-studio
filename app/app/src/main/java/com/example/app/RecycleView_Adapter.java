package com.example.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleView_Adapter extends RecyclerView.Adapter<RecycleView_Adapter.holder>
{
    public RecycleView_Adapter(ArrayList<User> list,holder.OnCardClickedListener _listener) {
        this.list = list;
        listener=_listener;
    }

    ArrayList<User> list;
    holder.OnCardClickedListener listener;
    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        holder h=new holder(v,listener);
        return h;
    }

    @Override
    public void onBindViewHolder(@NonNull holder hold, int position) {
        hold.tv.setText(list.get(position).getfName());
        //Picasso.get().load(list.get(position).getUrl()).fit().into(hold.iv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class holder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView iv;
        TextView tv;
        OnCardClickedListener listener;

        public holder(@NonNull View itemView, OnCardClickedListener _listener) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv_card);
            tv=itemView.findViewById(R.id.tv_card);
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
