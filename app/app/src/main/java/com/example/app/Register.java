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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class Register extends AppCompatActivity {
    EditText fname, lname, login, email, password;
    Button signup;
    private FirebaseAuth mAuth;
    DatabaseReference dbref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //        hide the actionbar
        getSupportActionBar().hide();
        signup = findViewById(R.id.signup);

        fname =findViewById(R.id.fname);
        lname =findViewById(R.id.lname);
        login = findViewById(R.id.login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        mAuth= FirebaseAuth.getInstance();
        //db reference
        dbref = FirebaseDatabase.getInstance().getReference("_user_");

//        if(mAuth.getCurrentUser()!=null)
//        {
//            startActivity(new Intent(getApplicationContext(),MainActivity.class));
//            Toast.makeText(Register.this,"Muie !",Toast.LENGTH_SHORT).show();
//            finish();
//        }
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String fn=fname.getText().toString().trim();
                String ln=lname.getText().toString().trim();
                String log=login.getText().toString().trim();
                String mail=email.getText().toString().trim();
                String pwd=password.getText().toString().trim();
                if(TextUtils.isEmpty(fn))  {fname.setError("First Name is required"); return;}
                if(TextUtils.isEmpty(ln))  {lname.setError("Last Name is required");return;}
                if(TextUtils.isEmpty(log)) {login.setError("Username is required");return;}
                if(TextUtils.isEmpty(mail)){email.setError("Email is required");return;}
                if(TextUtils.isEmpty(pwd)) {password.setError("Password is required");return;}
                if(pwd.length()<6){password.setError("Password needs to be 6 characters or more");return;}
                mAuth.createUserWithEmailAndPassword(mail,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Register.this,"User Created",Toast.LENGTH_SHORT).show();
                            User user = new User(fname.getText().toString(), lname.getText().toString(),email.getText().toString(),password.getText().toString(), login.getText().toString());
                            dbref.child(dbref.push().getKey()).setValue(user);
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else Toast.makeText(Register.this,"Error !",Toast.LENGTH_SHORT).show();

                    }
                });
            }

        });


        /*signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fname.getText().length() > 0 && lname.getText().length() > 0 && login.getText().length() > 0 && email.getText().length() > 0 && password.getText().length() > 0) {
                    User user = new User(fname.getText().toString(), lname.getText().toString(),email.getText().toString(),password.getText().toString(), login.getText().toString());
                    dbref.child(dbref.push().getKey()).setValue(user);

                    Intent i = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(i);
                    Toast.makeText(getBaseContext(), "Please login with your details.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getBaseContext(), "Please complete all fields.", Toast.LENGTH_SHORT).show();
                }

            }
        });*/

    }
}
