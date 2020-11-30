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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class personalsettings extends AppCompatActivity {
    public static final int SELECT_PICTURE = 1;
    public static final String URL = "URL";
    Uri url;
    String path;
    ImageView back, avatar;
    EditText fname, lname, login, email, password;
    Button confirm, edit1, edit2, edit3, edit4, edit5;
    int ok=0;
    DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("_user_");;
    StorageReference sref = FirebaseStorage.getInstance().getReference("usersProfile");

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //verify the image pick
        if (requestCode == SELECT_PICTURE && resultCode==RESULT_OK && data!=null) {
            url = data.getData();
            Picasso.get().load(url).into(avatar);
        }
    }


    private String getExt(Uri uri) {
        ContentResolver resolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(resolver.getType(uri));
    }

    private void reauth(final String nmail, final String npwd) {
        String CURRENT_EMAIL = ((logged) getApplication()).getLogged().getEmail();
        String CURRENT_PWD = ((logged) getApplication()).getLogged().getPassword();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        // Get auth credentials from the user for re-authentication
        AuthCredential credential = EmailAuthProvider
                .getCredential(CURRENT_EMAIL, CURRENT_PWD); // Current Login Credentials \\
        // Prompt the user to re-provide their sign-in credentials
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //Now change your email address \\
                        //----------------Code for Changing Email Address----------\\
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        user.updatePassword(npwd);
                        user.updateEmail(nmail);
                    }
                });
    }

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

        confirm = findViewById(R.id.btn_confirm);
        edit1 = findViewById(R.id.btn_edit);
        edit2 = findViewById(R.id.btn_edit2);
        edit3 = findViewById(R.id.btn_edit3);
        edit4 = findViewById(R.id.btn_edit4);
        edit5 = findViewById(R.id.btn_edit5);
        edit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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


//        go back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),settings.class ));
            }
        });
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ok=1;
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select picture"), SELECT_PICTURE);

            }
        });

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren())
                {
                    User u=ds.getValue(User.class);
                    if(u.equals(((logged)getApplication()).getLogged())) {
                        path = ds.getKey();
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fn = fname.getText().toString().trim();
                final String ln = lname.getText().toString().trim();
                final String log = login.getText().toString().trim();
                final String mail = email.getText().toString().trim();
                final String pwd = password.getText().toString().trim();


                if (fname.getVisibility() == View.VISIBLE) {
                    if (TextUtils.isEmpty(fn)) {
                        fname.setError("First Name is required");
                        return;
                    } else dbref.child(path).child("fName").setValue(fn);
                }

                if (lname.getVisibility() == View.VISIBLE) {
                    if (TextUtils.isEmpty(ln)) {
                        lname.setError("Last Name is required");
                        return;
                    } else dbref.child(path).child("lName").setValue(ln);
                }
                if (email.getVisibility() == View.VISIBLE) {
                    if (TextUtils.isEmpty(mail)) {
                        email.setError("Email is required");
                        return;
                    } else dbref.child(path).child("email").setValue(mail);
                }
                if (login.getVisibility() == View.VISIBLE) {
                    if (TextUtils.isEmpty(log)) {
                        login.setError("First Name is required");
                        return;
                    } else dbref.child(path).child("login").setValue(log);
                }
                if (password.getVisibility() == View.VISIBLE) {
                    if (TextUtils.isEmpty(pwd) || pwd.length() < 6) {
                        password.setError("Password of 6 or more characters is required");
                        return;
                    } else
                        dbref.child(path).child("password").setValue(pwd);
                }
                if (email.getVisibility() == View.VISIBLE && password.getVisibility() == View.VISIBLE)
                    reauth(mail, pwd);
                else if (email.getVisibility() == View.VISIBLE)
                    reauth(mail, ((logged) getApplication()).getLogged().getPassword());
                else if (password.getVisibility() == View.VISIBLE)
                    reauth(((logged) getApplication()).getLogged().getEmail(), pwd);
                if(ok==1) {
                    final StorageReference reference = sref.child(((logged) getApplication()).getLogged().getEmail() + "." + getExt(url));
                    reference.putFile(url).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String downloadURL = uri.toString();
                                    dbref.child(path).child("url").setValue(downloadURL);
                                    //((logged) getApplication()).getLogged().setUrl(downloadURL);
                                }
                            });
                        }
                    });
                }

                Toast.makeText(personalsettings.this, "Details Updated", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getBaseContext(), MainActivity.class));


            }
        });
    }
}