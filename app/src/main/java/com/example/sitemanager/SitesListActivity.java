package com.example.sitemanager;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SitesListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sites_list);

        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Site");
        Toast.makeText(SitesListActivity.this,"oncreate",Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onStart() {
        Toast.makeText(SitesListActivity.this,"onstart",Toast.LENGTH_SHORT).show();

        super.onStart();
        FirebaseRecyclerAdapter<AddSited,ViewHolder> firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<AddSited, ViewHolder>(
                        AddSited.class,
                        R.layout.row,
                        ViewHolder.class,
                        databaseReference) {

                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, AddSited addSited, int position) {
                        Toast.makeText(SitesListActivity.this,"oncrpop",Toast.LENGTH_SHORT).show();

                        viewHolder.setDetails(getApplicationContext(),addSited.getSite_name(),addSited.getSite_address(),addSited.getUrl());
                    }
                };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}
