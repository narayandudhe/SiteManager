package com.example.sitemanager;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateProfile extends AppCompatActivity {
    private EditText usemail, uspassword, usname,date,mob,add;
    private ImageButton updateprofile;
    private Button update;
    private FirebaseAuth firebaseAuth;
    private ImageView ivprofile;
  private   String email,name,password,dob,address,mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        setup();
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("Worker").child(firebaseAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userProfile userprofile=dataSnapshot.getValue(userProfile.class);
                usname.setText(userprofile.getUname());
                usemail.setText(userprofile.getUemail());
                mob.setText(userprofile.getUmobile());
                date.setText(userprofile.getUdob());
                add.setText(userprofile.getUaddress());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UpdateProfile.this,"could not connect",Toast.LENGTH_SHORT).show();
            }
        });
        updateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserData();
                Toast.makeText(UpdateProfile.this, "update  done", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UpdateProfile.this,ProfileActivity.class));

            }
        });
    }
    private void sendUserData(){
        name=usname.getText().toString();
        email=usemail.getText().toString();
        password=uspassword.getText().toString();
        dob=date.getText().toString();
        address=add.getText().toString();
        mobile=mob.getText().toString();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        userProfile userprofile=new userProfile(name,dob,address,email,mobile);
        myRef.setValue(userprofile);
    }
    private void setup(){
        usemail=(EditText)findViewById(R.id.email);
        uspassword=(EditText)findViewById(R.id.password);
        usname=(EditText)findViewById(R.id.name);
        updateprofile=(ImageButton)findViewById(R.id.signup);
        ivprofile=(ImageView)findViewById(R.id.userimage);
        date=(EditText)findViewById(R.id.dob);
        add=(EditText)findViewById(R.id.address);
        mob=(EditText)findViewById(R.id.mobile);
    }
}
