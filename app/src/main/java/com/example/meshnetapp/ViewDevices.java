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

public class ViewDevices extends AppCompatActivity {
    private FirebaseRecyclerOptions<Devices> options;
    private FirebaseRecyclerAdapter<Devices, MyDeviceViewHolder> adapter;
    private RecyclerView recyclerView;

    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_devices);
        reff = FirebaseDatabase.getInstance().getReference().child("Devices");
        recyclerView = (RecyclerView)findViewById(R.id.recyclerDevices);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        options = new FirebaseRecyclerOptions.Builder<Devices>().setQuery(reff,Devices.class).build();
        adapter = new FirebaseRecyclerAdapter<Devices,MyDeviceViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyDeviceViewHolder holder, int position, @NonNull Devices model) {
                if(model.getConnected() == true) {
                    holder.textViewname.setText(model.getName());
                    holder.textViewmac.setText(model.getMac());
                }

            }

            @NonNull
            @Override
            public MyDeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_devices,parent, false);

                return new MyDeviceViewHolder(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }
}