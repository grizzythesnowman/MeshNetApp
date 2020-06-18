package com.example.meshnetapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class MyDeviceViewHolder extends RecyclerView.ViewHolder {
    TextView textViewname, textViewmac;
    public MyDeviceViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewname = itemView.findViewById(R.id.textViewname);
        textViewmac = itemView.findViewById(R.id.textViewMac);
    }
}
