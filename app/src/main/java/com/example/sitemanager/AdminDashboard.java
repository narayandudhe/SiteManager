package com.example.sitemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class AdminDashboard extends AppCompatActivity {
    Button as,aw,vs,vw,gs,vp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        as=(Button)findViewById(R.id.addsite);
        aw=(Button)findViewById(R.id.addworker);
        vw=(Button)findViewById(R.id.assignsite);
        vs=(Button)findViewById(R.id.viewsite);
        gs=(Button)findViewById(R.id.getallsite);
        vp=(Button)findViewById(R.id.viewsiteprofit);
        as.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this,AddSite.class));
            }
        });
        aw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this,SitesListActivity.class));
            }
        });
        vw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this,AssignSite.class));
            }
        });
        vs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this,AddSite.class));
            }
        });

        gs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this,GetAllsite.class));
            }
        });
        vp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this,Profit.class));
            }
        });


    }
}
