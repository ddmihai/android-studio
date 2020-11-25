package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecycleView extends AppCompatActivity implements RecycleView_Adapter.holder.OnCardClickedListener {
    ImageView back;
    RecyclerView rv;
    ArrayList<User> list=new ArrayList<>();
    DatabaseReference dbref;
    RecycleView_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        //        hide the actionbar
        getSupportActionBar().hide();
        back = findViewById(R.id.back);
        rv=findViewById(R.id.rv_l);
        rv.setLayoutManager(new LinearLayoutManager(RecycleView.this));//LinearLayoutManager.HORIZONTAL,false));
        dbref= FirebaseDatabase.getInstance().getReference("_user_");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dss : snapshot.getChildren()) {
                    User user = dss.getValue(User.class);
                    list.add(user);

                }
                adapter=new RecycleView_Adapter(list,RecycleView.this);
                rv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        back btn
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), dashboard.class));
            }
        });
    }

    @Override
    public void OnCardClickedListener(int i) {
        Intent intent=new Intent(RecycleView.this,Details.class);
        intent.putExtra("User",list.get(i));
        startActivity(intent);
    }

}