package com.example.sitemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WorkerDashboard extends AppCompatActivity {

    Button ad,vsw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_dashboard);
        ad=(Button)findViewById(R.id.adddetails);
        vsw=(Button)findViewById(R.id.viewsitedet);
        ad.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WorkerDashboard.this,AddNewSiteCost.class);
                startActivity(i);
            }
        });
        vsw.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(WorkerDashboard.this,ViewSiteWorker.class);
                startActivity(i1);
            }
        });
    }
}
