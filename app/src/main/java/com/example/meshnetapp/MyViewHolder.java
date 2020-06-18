package com.example.meshnetapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView textViewSender, textViewReceiver, textViewMessage, textViewt0, textViewt1, textViewt2, textViewt3;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewSender = itemView.findViewById(R.id.textViewSender);
        textViewReceiver = itemView.findViewById(R.id.textViewReceiver);
        textViewMessage = itemView.findViewById(R.id.textViewMessage);
        textViewt0 = itemView.findViewById(R.id.textViewt0);
        textViewt1 = itemView.findViewById(R.id.textViewt1);
        textViewt2 = itemView.findViewById(R.id.textViewt2);
        textViewt3 = itemView.findViewById(R.id.textViewt3);

    }
}
