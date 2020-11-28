package com.example.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class personalsettings extends AppCompatActivity {
    public static final int SELECT_PICTURE = 1;
    public static final String URL = "URL";
    Uri url;
    ImageView back, avatar;
    EditText fname, lname, login, email, password;
    Button confirm,edit1,edit2,edit3,edit4,edit5;
    DatabaseReference dbref;
    StorageReference sref = FirebaseStorage.getInstance().getReference("usersProfile");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalsettings);
        //        hide the actionbar
        getSupportActionBar().hide();


        back = findViewById(R.id.back);
        fname = findViewById(R.id.et_fn);
        lname = findViewById(R.id.et_ln);
        login = findViewById(R.id.et_login);
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);

        confirm=findViewById(R.id.btn_confirm);
        edit1=findViewById(R.id.btn_edit);
        edit2=findViewById(R.id.btn_edit2);
        edit3=findViewById(R.id.btn_edit3);
        edit4=findViewById(R.id.btn_edit4);
        edit5=findViewById(R.id.btn_edit5);
        edit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                fname.setVisibility(View.VISIBLE);
                edit1.setVisibility(View.INVISIBLE);
            }
        });
        edit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lname.setVisibility(View.VISIBLE);
                edit2.setVisibility(View.INVISIBLE);
            }
        });
        edit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email.setVisibility(View.VISIBLE);
                edit3.setVisibility(View.INVISIBLE);
            }
        });
        edit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.setVisibility(View.VISIBLE);
                edit4.setVisibility(View.INVISIBLE);
            }
        });
        edit5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.setVisibility(View.VISIBLE);
                edit5.setVisibility(View.INVISIBLE);
            }
        });
        avatar = findViewById(R.id.iv_avatar);
        //       go back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), settings.class));
            }
        });


//        //db reference
        dbref = FirebaseDatabase.getInstance().getReference("_user_");
//
////        go back
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getBaseContext(),settings.class ));
//            }
//        });
//        avatar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Intent i = new Intent();
//                //i.setType("image/*");
//                //i.setAction(Intent.ACTION_GET_CONTENT);
//                //startActivityForResult(i, REQUEST);
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select picture"), SELECT_PICTURE);
//
//            }
//        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fn = fname.getText().toString().trim();
                final String ln = lname.getText().toString().trim();
                final String log = login.getText().toString().trim();
                final String mail = email.getText().toString().trim();
                final String pwd = password.getText().toString().trim();
               dbref.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dss:snapshot.getChildren()) {
                            User u = dss.getValue(User.class);
                            if (u.equals(((logged) getApplication()).getLogged())) {
                                if (fname.getVisibility() == View.VISIBLE) {
                                    if (TextUtils.isEmpty(fn)) {
                                        fname.setError("First Name is required");
                                        return;
                                    } else dbref.child(dss.getKey()).child("fName").setValue(fn);
                                }

                                if (lname.getVisibility() == View.VISIBLE) {
                                    if (TextUtils.isEmpty(ln)) {
                                        lname.setError("Last Name is required");
                                        return;
                                    } else dbref.child(dss.getKey()).child("lName").setValue(ln);
                                }
                                if (email.getVisibility() == View.VISIBLE) {
                                    if (TextUtils.isEmpty(mail)) {
                                        email.setError("Email is required");
                                        return;
                                    } else dbref.child(dss.getKey()).child("email").setValue(mail);
                                }
                                if (login.getVisibility() == View.VISIBLE) {
                                    if (TextUtils.isEmpty(log)) {
                                        login.setError("First Name is required");
                                        return;
                                    } else dbref.child(dss.getKey()).child("login").setValue(log);
                                }
                                if (password.getVisibility() == View.VISIBLE) {
                                    if (TextUtils.isEmpty(pwd) || pwd.length() < 6) {
                                        password.setError("Password of 6 or more characters is required");
                                        return;
                                    } else
                                        dbref.child(dss.getKey()).child("password").setValue(pwd);
                                }


                            }
                        }
                    }



                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

    }
}

//
//
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        //verify the image pick
//        if(requestCode == SELECT_PICTURE)
//        {
//            url = data.getData();
//            Picasso.get().load(url).into(avatar) ;
//        }
//    }
//
//
//
//    private String getExt(Uri uri){
//        ContentResolver resolver= getContentResolver();
//        MimeTypeMap mime = MimeTypeMap.getSingleton();
//        return mime.getExtensionFromMimeType(resolver.getType(uri));
//    }
//
//
//
//}
////agapie@gmail.com
////iulian