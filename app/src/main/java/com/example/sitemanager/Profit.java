package com.example.sitemanager;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import java.util.ArrayList;
import java.util.List;

public class Profit extends AppCompatActivity {

    ListView lis;
    TextView toin;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,tot;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    AddCost addCost;


String total;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profit);
        addCost=new AddCost();
        lis=(ListView)findViewById(R.id.listtrans);
        toin=(TextView)findViewById(R.id.total);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("SiteCost");
        list=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.list_trans,R.id.wname,list);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot da : dataSnapshot.getChildren())
                {
                    addCost=da.getValue(AddCost.class);
                    list.add(addCost.getRawCost()+ ""+addCost.getQuantity());
                }
                lis.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
