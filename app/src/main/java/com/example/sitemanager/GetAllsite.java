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

public class GetAllsite extends AppCompatActivity {

    RecyclerView recyclerVieww;
    FirebaseDatabase firebaseDatabase1;
    DatabaseReference databaseReference1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_allsite);

        recyclerVieww=findViewById(R.id.recyclerviewsa);
        recyclerVieww.setHasFixedSize(true);
        recyclerVieww.setLayoutManager(new LinearLayoutManager(this));
        firebaseDatabase1=FirebaseDatabase.getInstance();
        databaseReference1=firebaseDatabase1.getReference("AssignSite");
        Toast.makeText(GetAllsite.this,"oncreate",Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onStart() {
        Toast.makeText(GetAllsite.this,"onstart",Toast.LENGTH_SHORT).show();

        super.onStart();
        FirebaseRecyclerAdapter<SiteAssign,ViewHolder> firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<SiteAssign, ViewHolder>(
                        SiteAssign.class,
                        R.layout.listrow,
                        ViewHolder.class,
                        databaseReference1) {

                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, SiteAssign siteAssign, int position) {
                        Toast.makeText(GetAllsite.this,"oncrpop",Toast.LENGTH_SHORT).show();

                        viewHolder.setDetailss(getApplicationContext(),siteAssign.getSiteName(),siteAssign.getWorker1(),siteAssign.getWorker2(),siteAssign.limit);
                    }
                };
        recyclerVieww.setAdapter(firebaseRecyclerAdapter);
    }
}
