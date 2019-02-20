package com.example.sitemanager;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    private TextView name,email,mobile,dob,address,sitename;
    private ImageView wimages;
    Button up;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name=(TextView)findViewById(R.id.wname);
        email=(TextView)findViewById(R.id.wemail);
        mobile=(TextView)findViewById(R.id.wmobile);
        dob=(TextView)findViewById(R.id.wdob);
        address=(TextView)findViewById(R.id.waddress);
        sitename=(TextView)findViewById(R.id.quantname);
        wimages=(ImageView)findViewById(R.id.wimage);
        up=(Button)findViewById(R.id.update);

        firebaseAuth=FirebaseAuth.getInstance();
      firebaseDatabase=FirebaseDatabase.getInstance();
      firebaseStorage=FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        storageReference.child("profilepic").child(firebaseAuth.getUid()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

            @Override
            public void onSuccess(Uri uri) {
                Toast.makeText(ProfileActivity.this,"image loaded",Toast.LENGTH_SHORT).show();
                Picasso.get().load(uri).into(wimages);
            }
        });
      DatabaseReference databaseReference=firebaseDatabase.getReference("Worker").child(firebaseAuth.getUid());
      databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userProfile userprofile=dataSnapshot.getValue(userProfile.class);
                name.setText(userprofile.getUname());
                email.setText(userprofile.getUemail());
                mobile.setText(userprofile.getUmobile());
                dob.setText(userprofile.getUdob());
                address.setText(userprofile.getUaddress());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this,"could not connect",Toast.LENGTH_SHORT).show();
            }
        });
    up.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i= new Intent(ProfileActivity.this,UpdateProfile.class);
            startActivity(i);
        }
    });
    }
}
