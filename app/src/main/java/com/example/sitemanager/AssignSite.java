package com.example.sitemanager;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AssignSite extends AppCompatActivity {

    Spinner sp1,sp2,sp3;
    ImageButton imageButton;
    EditText li;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference,datasite,adds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_site);
        sp3=(Spinner)findViewById(R.id.sitena);
        sp1=(Spinner)findViewById(R.id.wo1);
        sp2=(Spinner)findViewById(R.id.wo2);
        imageButton=(ImageButton)findViewById(R.id.asssite);
        li=(EditText)findViewById(R.id.limit);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Worker");
        datasite=firebaseDatabase.getReference("Site");
        datasite.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<String> list2 = new ArrayList<String>();

                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                    String sName = areaSnapshot.child("site_name").getValue(String.class);
                    list2.add(sName);
                    sp3=(Spinner)findViewById(R.id.sitena);
                    ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(AssignSite.this, android.R.layout.simple_spinner_item, list2);
                    areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp3.setAdapter(areasAdapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AssignSite.this,"Site Not Found",Toast.LENGTH_SHORT).show();
            }
        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<String> list1 = new ArrayList<String>();

                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                    String wName = areaSnapshot.child("uname").getValue(String.class);
                    list1.add(wName);
                    ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(AssignSite.this, android.R.layout.simple_spinner_item, list1);
                    areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp1.setAdapter(areasAdapter);
                    sp2.setAdapter(areasAdapter);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AssignSite.this,"Worker not Created",Toast.LENGTH_SHORT).show();

            }
        });


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdata();
            }
        });

    }

    private void getdata() {
        String w1=String.valueOf(sp1.getSelectedItem());
        String w2=String.valueOf(sp2.getSelectedItem());
        String s1=String.valueOf(sp3.getSelectedItem());
        String l1=li.getText().toString();
        adds=firebaseDatabase.getReference("AssignSite");
        final String id = databaseReference.push().getKey();
        SiteAssign siteAssign=new SiteAssign(w1,w2,s1,l1);
        adds.child(id).setValue(siteAssign);
        Toast.makeText(AssignSite.this, "Worker Assigned", Toast.LENGTH_LONG).show();
    }

}
