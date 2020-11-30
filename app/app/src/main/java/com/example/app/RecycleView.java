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
import java.util.Collections;
import java.util.Comparator;

public class RecycleView extends AppCompatActivity implements RecycleView_Adapter.holder.OnCardClickedListener {
    ImageView back;
    RecyclerView rv;
    ArrayList<Eatery> list=new ArrayList<>();
    ArrayList<Booking> listB=new ArrayList<>();
    DatabaseReference dbref;
    RecycleView_Adapter adapter;
    BookingAdapter adapterB;
    String type;
    String path;
    int code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        //        hide the actionbar
        getSupportActionBar().hide();
        back = findViewById(R.id.back);
        rv=findViewById(R.id.rv_l);

        rv.setLayoutManager(new LinearLayoutManager(RecycleView.this));//LinearLayoutManager.HORIZONTAL,false));
        path=getIntent().getStringExtra("Path");
        code=getIntent().getIntExtra("Code",0);
        dbref= FirebaseDatabase.getInstance().getReference(path);
        if(code==1) {
            type=getIntent().getStringExtra("Type");
            dbref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dss : snapshot.getChildren()) {
                        Eatery e = dss.getValue(Eatery.class);
                        if (e.getType().equals(type))
                            list.add(e);

                    }
                    Collections.sort(list);
                    adapter = new RecycleView_Adapter(list, RecycleView.this);
                    rv.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else if(code==2)
        {
            dbref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dss : snapshot.getChildren()) {
                        Booking b = dss.getValue(Booking.class);
                        if (b.getAddress().equals(((logged) getApplication()).getLogged().getEmail()))
                        {
                            listB.add(b);


                        }

                    }
                    Collections.sort(list);
                    adapterB = new BookingAdapter(listB);
                    rv.setAdapter(adapterB);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
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
        if(code==1) {
            Intent intent = new Intent(RecycleView.this, Details.class);
            intent.putExtra("Eatery", list.get(i));
            startActivity(intent);

        }
    }

}