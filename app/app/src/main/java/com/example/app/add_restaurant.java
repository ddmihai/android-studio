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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class add_restaurant extends AppCompatActivity {

    public static final int SELECT_PICTURE = 1;
    //public static final int REQUEST = 1;
    public static final String URL = "URL";
    String t;
    String s;
    Uri url;
    EditText name,location,description;
    RadioButton street,restaurant,veg,nveg;
    ImageView add_image;
    Button complete;
    DatabaseReference dbref= FirebaseDatabase.getInstance().getReference("Eatery");
    StorageReference sref= FirebaseStorage.getInstance().getReference("images");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);
        //        hide the actionbar
        getSupportActionBar().hide();
        name=findViewById(R.id.et_name);
        location=findViewById(R.id.et_location);
        description=findViewById(R.id.et_description);
        street=(RadioButton)findViewById(R.id.rb_street);
        restaurant=(RadioButton)findViewById(R.id.rb_restaurant);
        veg=(RadioButton)findViewById(R.id.rb_veg);
        nveg=(RadioButton)findViewById(R.id.rb_nveg);
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
                final String n=name.getText().toString().trim();
                if(TextUtils.isEmpty(n))  {name.setError(" Name is required"); return;}
                final String d=description.getText().toString().trim();
                if(TextUtils.isEmpty(d))  {description.setError(" A brief description is required"); return;}
                final String l=location.getText().toString().trim();
                if(TextUtils.isEmpty(l))  {location.setError(" A location is required"); return;}

                if(street.isChecked())
                    t="Street Food";
                else if(restaurant.isChecked())
                        t="Restaurant";
                else Toast.makeText(add_restaurant.this,"Select place type",Toast.LENGTH_SHORT).show();
                if(veg.isChecked())
                    s="Vegetarian";
                else if(nveg.isChecked())
                        s="Non-Vegetarian";
                else  Toast.makeText(add_restaurant.this,"Select serving type",Toast.LENGTH_SHORT).show();
                final StorageReference reference = sref.child(n+getExt(url));
                reference.putFile(url).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String downloadURL = uri.toString();
                                Eatery e=new Eatery(n,downloadURL,d,l,s,t);
                                dbref.child(dbref.push().getKey()).setValue(e);
                                Toast.makeText(add_restaurant.this,"Eatery added !",Toast.LENGTH_SHORT).show();


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
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //verify the image pick
        if(requestCode == SELECT_PICTURE)
        {
            url = data.getData();
            Picasso.get().load(url).into(add_image) ;
        }
    }



    private String getExt(Uri uri){
        ContentResolver resolver= getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(resolver.getType(uri));
    }



}

