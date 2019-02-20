package com.example.sitemanager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddNewSiteCost extends AppCompatActivity {

    Spinner raw;
    ImageView rawbill;
    EditText req,quan;
    ImageButton sub;
    Uri imageuri;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,nadd;
    Uri imagePath;
    private StorageReference storageReference;
    private FirebaseStorage firebaseStorage;
    Uri downloadurl;
    private static int PICK_IMAGE=123;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() !=null)
        {
            imagePath=data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imagePath);
                rawbill.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_site_cost);
        raw=(Spinner)findViewById(R.id.rawmat);
        rawbill=(ImageView)findViewById(R.id.bill);
        req=(EditText)findViewById(R.id.costp);
        quan=(EditText)findViewById(R.id.quantity);
        sub=(ImageButton)findViewById(R.id.submit);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("SiteRawMat");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<String> list2 = new ArrayList<String>();
                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                    String sName = areaSnapshot.getValue(String.class);
                    list2.add(sName);
                    ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(AddNewSiteCost.this, android.R.layout.simple_spinner_item, list2);
                    areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    raw.setAdapter(areasAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AddNewSiteCost.this,"raw material Not Found",Toast.LENGTH_SHORT).show();

            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getall();
            }
        });
        rawbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"select Image"),PICK_IMAGE);
            }
        });

    }

    private void getall() {
       final String w1=String.valueOf(raw.getSelectedItem());
        final String reqi=req.getText().toString();
        final String quani=quan.getText().toString();
        final int r=Integer.parseInt(reqi);
        final int q= Integer.parseInt(quani);
        final long ans=r*q;
        final  String Cal=Integer.toString((int) ans);
        nadd=firebaseDatabase.getReference("SiteCost");
        final String id = databaseReference.push().getKey();

        Toast.makeText(AddNewSiteCost.this, "Worker Assigned", Toast.LENGTH_LONG).show();
        firebaseStorage=FirebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference();
        final UploadTask uploadTask;
        // [START upload_get_download_url]
        final StorageReference imageRef=storageReference.child("SiteRaw").child(id);
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
                    Toast.makeText(AddNewSiteCost.this, url,Toast.LENGTH_SHORT).show();
                    AddCost addCost=new AddCost(w1,reqi,quani,Cal,url);
                    nadd.child(id).setValue(addCost);
                    Toast.makeText(AddNewSiteCost.this, "new site cost   added", Toast.LENGTH_LONG).show();
                } else {
                    // Handle failures
                    // ...
                    Toast.makeText(AddNewSiteCost.this,"file upload filed",Toast.LENGTH_SHORT).show();

                }
            }
        });
// [END upload_get_download_url]

    }
}
