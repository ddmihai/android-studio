package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.Date;

public class reservation extends AppCompatActivity {
    DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("_bookings_");
    CalendarView calendar;
    TextView name;
    EditText hour;
    Button reserve;
    Booking booking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reservation);
        //        hide the actionbar
        getSupportActionBar().hide();
        calendar = findViewById(R.id.calendarView);
        name = findViewById(R.id.tv_res_name);
        reserve = findViewById(R.id.btn_reserve);
        hour=findViewById(R.id.et_time);
        final Eatery e = getIntent().getParcelableExtra("Eatery");
        name.setText(e.getName());
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
               booking= new Booking(year, month, dayOfMonth, 0, e.getName(),((logged) getApplication()).getLogged().getEmail());
                Toast.makeText(reservation.this, "Date Selected !", Toast.LENGTH_SHORT).show();// TODO Auto-generated method stub

            }
        });
        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double time=0;
                try {
                    time = Double.parseDouble(hour.getText().toString());
                    if (time<11 || time >18)
                        hour.setError("We are not open at that time");
                    else
                    {
                        booking.setHour(time);
                        dbref.child(dbref.push().getKey()).setValue(booking);
                        startActivity( new Intent(getBaseContext(),dashboard.class));
                    }
                } catch (Exception exception) {
                    hour.setError("Please enter a valid time");
                }

            }
        });

    }
}
