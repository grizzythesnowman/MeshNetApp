package com.example.meshnetapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class DeleteAdapter extends FirebaseRecyclerAdapter<Devices, DeleteAdapter.DeleteViewHolder>{


    public DeleteAdapter(@NonNull FirebaseRecyclerOptions<Devices> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DeleteViewHolder holder, final int position, @NonNull Devices model) {
        holder.name.setText(model.getName());
        holder.mac.setText(model.getMac());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("VirtualDevice").child(getRef(position).getKey()).setValue(null)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
            }
        });

    }

    @NonNull
    @Override
    public DeleteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.delete, parent, false);
        return new DeleteViewHolder(view);
    }

    class DeleteViewHolder extends RecyclerView.ViewHolder{

        TextView name, mac;
        ImageView delete;

        public DeleteViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.deviceName);
            mac = itemView.findViewById(R.id.macName);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}
