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
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class add_eatery extends AppCompatActivity {

    public static final int SELECT_PICTURE = 1;
    //public static final int REQUEST = 1;
    public static final String URL = "URL";
    String s;
    Uri url;
    EditText name, location, description;
    RadioButton street, restaurant, veg, nveg;
    ImageView add_image;
    Button complete;
    String downloadURL;
    DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("Eatery");
    StorageReference sref = FirebaseStorage.getInstance().getReference("images");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);
        //        hide the actionbar
        getSupportActionBar().hide();
        name = findViewById(R.id.et_name);
        location = findViewById(R.id.et_location);
        description = findViewById(R.id.et_description);
        final String type = getIntent().getStringExtra("Type");
        veg = (RadioButton) findViewById(R.id.rb_veg);
        nveg = (RadioButton) findViewById(R.id.rb_nveg);
        add_image = findViewById(R.id.iv_add_image);
        complete = findViewById(R.id.btn_complete);


        //select image
        add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent();
                //i.setType("image/*");
                //i.setAction(Intent.ACTION_GET_CONTENT);
                //startActivityForResult(i, REQUEST);
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select picture"), SELECT_PICTURE);

            }
        });


        //upload image
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String n = name.getText().toString().trim();
                if (TextUtils.isEmpty(n)) {
                    name.setError(" Name is required");
                    return;
                }
                final String d = description.getText().toString().trim();
                if (TextUtils.isEmpty(d)) {
                    description.setError(" A brief description is required");
                    return;
                }
                final String l = location.getText().toString().trim();
                if (TextUtils.isEmpty(l)) {
                    location.setError(" A location is required");
                    return;
                }

                if (veg.isChecked())
                    s = "Vegetarian";
                else if (nveg.isChecked())
                    s = "Non-Vegetarian";
                else
                    Toast.makeText(add_eatery.this, "Select serving type", Toast.LENGTH_SHORT).show();
                try {
                    final StorageReference reference = sref.child(n + "." + getExt(url));
                    reference.putFile(url).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadURL = uri.toString();

                                }
                            });
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                }
                catch (Exception e)
                {Toast.makeText(add_eatery.this, "Please add a picture!", Toast.LENGTH_SHORT).show();}
                final ArrayList<Eatery> check = new ArrayList<>();
                Query dbref_check = FirebaseDatabase.getInstance().getReference("Eatery").orderByChild("name").equalTo(n);
                dbref_check.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dss : snapshot.getChildren()) {
                            check.add(dss.getValue(Eatery.class));
                        }
                        if (check.size() == 0) {
                            Eatery e = new Eatery(n, downloadURL, d, l, s, type, 0, 0);
                            dbref.child(dbref.push().getKey()).setValue(e);
                            Toast.makeText(add_eatery.this, "Eatery added !", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getBaseContext(), dashboard.class));
                        } else if (check.size() != 0) {
                            boolean exists = false;
                            for (int i = 0; i < check.size(); i++)
                                if (l.compareToIgnoreCase(check.get(i).getLocation()) == 0)
                                    exists = true;
                            if (exists == false) {

                                Eatery e = new Eatery(n, downloadURL, d, l, s, type, 0, 0);
                                dbref.child(dbref.push().getKey()).setValue(e);
                                Toast.makeText(add_eatery.this, "Eatery added !", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getBaseContext(), dashboard.class));
                            } else
                                Toast.makeText(add_eatery.this, "Eatery already exists !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //verify the image pick
        if (requestCode == SELECT_PICTURE) {
            url = data.getData();
            Picasso.get().load(url).into(add_image);
        }
    }


    private String getExt(Uri uri) {
        ContentResolver resolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(resolver.getType(uri));
    }


}

