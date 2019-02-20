package com.example.sitemanager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.util.Measure;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.net.URI;

public class AddSite extends AppCompatActivity {

    private ImageButton addbut;
    private ImageView ivsite;
    private EditText sname,saddress,scost;
    private DatabaseReference databaseReference;
    private static int PICK_IMAGE=123;
    Uri imagePath;
    private  StorageReference storageReference;
    private FirebaseStorage firebaseStorage;
    Uri downloadurl;
    String na,sa,sc;



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() !=null)
        {
            imagePath=data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imagePath);
                ivsite.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_site);
        addbut=(ImageButton)findViewById(R.id.addsitebt);
        sname=(EditText)findViewById(R.id.sitename);
        saddress=(EditText)findViewById(R.id.siteaddress);
        scost=(EditText)findViewById(R.id.sitecost);
        ivsite=(ImageView)findViewById(R.id.siteimage);
        databaseReference = FirebaseDatabase.getInstance().getReference("Site");
        addbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               senddata();
            }
        });
        ivsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"select Image"),PICK_IMAGE);
            }
        });

    }
    public void senddata() {
        na = sname.getText().toString();
         sa = saddress.getText().toString();
        sc = scost.getText().toString();
        if (na.isEmpty() || sa.isEmpty() || sc.isEmpty() || imagePath == null) {
            Toast.makeText(AddSite.this, "Please Enter all values", Toast.LENGTH_LONG).show();
        } else {
          final String id = databaseReference.push().getKey();
            firebaseStorage=FirebaseStorage.getInstance();
            storageReference=firebaseStorage.getReference();
            final UploadTask uploadTask;
            // [START upload_get_download_url]
            final StorageReference imageRef=storageReference.child("SiteImage").child(id);
            uploadTask = imageRef.putFile(imagePath);

            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    // Continue with the task to get the download URL
                    return imageRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        String url=downloadUri.toString();
                        Toast.makeText(AddSite.this, url,Toast.LENGTH_SHORT).show();
                        AddSited addSited = new AddSited(na, sa, sc,url);
                        databaseReference.child(id).setValue(addSited);
                        Toast.makeText(AddSite.this, "site  added", Toast.LENGTH_LONG).show();
                    } else {
                        // Handle failures
                        // ...
                        Toast.makeText(AddSite.this,"file upload filed",Toast.LENGTH_SHORT).show();

                    }
                }
            });
// [END upload_get_download_url]



        }
    }
}