package com.example.sitemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button ad,wo,lo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ad=(Button)findViewById(R.id.admin);
        wo=(Button)findViewById(R.id.worker);
        lo=(Button)findViewById(R.id.login);
        ad.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,AdminDashboard.class);
                startActivity(i);
            }
        });
        wo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(MainActivity.this,WorkerDashboard.class);
                startActivity(i1);
            }
        });
        lo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(i2);
            }
        });



    }
}
