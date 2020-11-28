package com.example.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
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
    Button confirm;
    DatabaseReference dbref;
    StorageReference sref = FirebaseStorage.getInstance().getReference("usersProfile");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalsettings);
        //        hide the actionbar
        getSupportActionBar().hide();

        back = findViewById(R.id.back);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        login = findViewById(R.id.login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        avatar = findViewById(R.id.iv_avatar);
 //       go back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),settings.class ));
            }
        });
    }
}
//        //db reference
//        dbref = FirebaseDatabase.getInstance().getReference("_user_");
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
//        confirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final String fn=fname.getText().toString().trim();
//                final String ln=lname.getText().toString().trim();
//                final String log=login.getText().toString().trim();
//                final String mail=email.getText().toString().trim();
//                final String pwd=password.getText().toString().trim();
//                if(TextUtils.isEmpty(fn))  {fname.setError("First Name is required"); return;}
//                if(TextUtils.isEmpty(ln))  {lname.setError("Last Name is required");return;}
//                if(TextUtils.isEmpty(log)) {login.setError("Username is required");return;}
//                if(TextUtils.isEmpty(mail)){email.setError("Email is required");return;}
//                if(TextUtils.isEmpty(pwd)) {password.setError("Password is required");return;}
//                if(pwd.length()<6){password.setError("Password needs to be 6 characters or more");return;}
//
//                dbref.orderByChild("email").equalTo(((logged) getApplication()).getLogged().getEmail()).addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        dbref.child(snapshot.getKey()).child("fName").setValue(fn);
//                        dbref.child(snapshot.getKey()).child("lName").setValue(ln);
//                        dbref.child(snapshot.getKey()).child("login").setValue(log);
//                        dbref.child(snapshot.getKey()).child("password").setValue(pwd);
//                        dbref.child(snapshot.getKey()).child("email").setValue(mail);
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//
//
//
//            }
//        });
//
//    }
//
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