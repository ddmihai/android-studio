package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    Button signup, login;
    EditText log_email, log_pwd;
    User user;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //        hide the actionbar
        getSupportActionBar().hide();
//bellow are the impostors ( edit text and buttons
        mAuth = FirebaseAuth.getInstance();

//        reference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot datas: dataSnapshot.getChildren()){
//                    int type=datas.getValue(User.class).getType();
//
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);

        log_email = findViewById(R.id.log_email);
        log_pwd = findViewById(R.id.log_pwd);

        if (mAuth.getCurrentUser() != null) {
            final FirebaseUser user = mAuth.getCurrentUser();
            final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("_user_");
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dss : snapshot.getChildren()) {
                        User u = dss.getValue(User.class);
                        if (u.getEmail().equals(user.getEmail())) {
                            if (u.getUid() == null)
                                ref.child(dss.getKey()).child("uid").setValue(user.getUid());
                            Intent i = new Intent(getBaseContext(), dashboard.class);
                            ((logged) getApplication()).setLogged(u);//calling the class that extends the app to hold the type of user
                            //consideration for adding put extra everywhere
                            startActivity(i);
                            //Toast.makeText(MainActivity.this,"Opening your dashboard",Toast.LENGTH_SHORT).show();
                            finish();
                        }


                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), Register.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = log_email.getText().toString().trim();
                String pwd = log_pwd.getText().toString().trim();
                if (TextUtils.isEmpty(mail)) {
                    log_email.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    log_pwd.setError("Password is required");
                    return;
                }
                mAuth.signInWithEmailAndPassword(mail, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Logged in successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else
                            Toast.makeText(MainActivity.this, "Error !", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


    }
}