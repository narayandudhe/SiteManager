package com.example.sitemanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;
    public ViewHolder(View itemView){
        super(itemView);

        mView=itemView;

    }
    public void setDetails(Context ctx, String site_name, String site_address, String url)
    {
        TextView mname=mView.findViewById(R.id.sitend);
        TextView maddress=mView.findViewById(R.id.sitead);
        ImageView mimg=mView.findViewById(R.id.siteid);
        mname.setText(site_name);
        maddress.setText(site_address);
        Picasso.get().load(url).into(mimg);
    }
    public void setDetailss(Context ctx, String site_name, String worker1, String worker2, String limit)
    {
        TextView sname=mView.findViewById(R.id.sitend);
        TextView sw1=mView.findViewById(R.id.worker1);
        TextView sw2=mView.findViewById(R.id.worker2);
        TextView li=mView.findViewById(R.id.limit1);

        sname.setText(site_name);
        sw1.setText(worker1);
        sw2.setText(worker2);
        li.setText(limit);


    }
}
