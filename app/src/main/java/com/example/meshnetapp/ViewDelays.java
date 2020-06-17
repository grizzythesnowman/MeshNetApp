package com.example.meshnetapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewDelays extends AppCompatActivity {

    private FirebaseRecyclerOptions<OneWayDelay> options;
    private FirebaseRecyclerAdapter<OneWayDelay, MyViewHolder> adapter;
    private RecyclerView recyclerView;

    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_delays);
        reff = FirebaseDatabase.getInstance().getReference().child("OneWayDelay");
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        options = new FirebaseRecyclerOptions.Builder<OneWayDelay>().setQuery(reff,OneWayDelay.class).build();
        adapter = new FirebaseRecyclerAdapter<OneWayDelay, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull OneWayDelay model) {
                    holder.textViewSender.setText(model.getSenderName());
                    holder.textViewReceiver.setText(model.getReceiverName());
                    holder.textViewMessage.setText(model.getMessage());
                    holder.textViewt0.setText(model.getT0().toString());
                    holder.textViewt1.setText(model.getT1().toString());
                    holder.textViewt2.setText(model.getT2().toString());
                    holder.textViewt3.setText(model.getT3().toString());
        }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_layout,parent, false);

                return new MyViewHolder(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}