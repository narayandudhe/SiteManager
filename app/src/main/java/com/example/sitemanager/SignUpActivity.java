package com.example.sitemanager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;


public class SignUpActivity extends AppCompatActivity {
    private EditText usemail, uspassword, usname,date,mob,add;
    private ImageButton ussignup;
    private Button ussignin;
    private FirebaseAuth firebaseAuth;
    private ImageView ivprofile;
    String email,name,password,dob,address,mobile;
    private FirebaseStorage firebaseStorage;
    private  StorageReference storageReference;
    private static int PICK_IMAGE=123;
    Uri imagePath;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() !=null)
       {
        imagePath=data.getData();
           try {
               Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imagePath);
               ivprofile.setImageBitmap(bitmap);
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setup();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();

        storageReference=firebaseStorage.getReference();
       // StorageReference myRef=storageReference.child(firebaseAuth.getUid()).getRoot();

        ivprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"select Image"),PICK_IMAGE);
            }
        });




        ussignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if(validate()){
                String uname=usname.getText().toString().trim();
                String uemail=usemail.getText().toString().trim();
                String upassword=uspassword.getText().toString().trim();
                firebaseAuth.createUserWithEmailAndPassword(uemail,upassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            sendUserData();
                            Toast.makeText(SignUpActivity.this, "Registration done", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUpActivity.this,ProfileActivity.class));
                        }
                        else
                        {
                            Toast.makeText(SignUpActivity.this,"Registration feild",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
             }
            }
        });

        ussignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,Login.class));
            }
        });

    }
    private void setup(){
      usemail=(EditText)findViewById(R.id.email);
      uspassword=(EditText)findViewById(R.id.password);
      usname=(EditText)findViewById(R.id.name);
      ussignup=(ImageButton)findViewById(R.id.signup);
      ussignin=(Button)findViewById(R.id.signin);
      ivprofile=(ImageView)findViewById(R.id.userimage);
      date=(EditText)findViewById(R.id.dob);
      add=(EditText)findViewById(R.id.address);
      mob=(EditText)findViewById(R.id.mobile);
    }
    private  boolean validate(){
        boolean result=false;
       name=usname.getText().toString();
       email=usemail.getText().toString();
       password=uspassword.getText().toString();
       dob=date.getText().toString();
       address=add.getText().toString();
       mobile=mob.getText().toString();

      if(name.isEmpty() || email.isEmpty() || password.isEmpty() || dob.isEmpty() || address.isEmpty() || mobile.isEmpty() || imagePath == null)
      {
          Toast.makeText(this,"Enter all details",Toast.LENGTH_SHORT).show();
      }
      else {
          result=true;
      }
      return result;
    }
    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("Worker").child(firebaseAuth.getUid());
        StorageReference imageRef=storageReference.child("profilepic").child(firebaseAuth.getUid());
        UploadTask uploadTask=imageRef.putFile(imagePath);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignUpActivity.this,"file upload filed",Toast.LENGTH_SHORT).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(SignUpActivity.this,"uploaded file",Toast.LENGTH_SHORT).show();

            }
        });
        userProfile userprofile=new userProfile(name,dob,address,email,mobile);
        myRef.setValue(userprofile);
    }
}