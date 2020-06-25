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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewDevices extends AppCompatActivity {
    private FirebaseRecyclerOptions<Devices> options;
    private FirebaseRecyclerAdapter<Devices, MyDeviceViewHolder> adapter;
    private RecyclerView recyclerView;

    DatabaseReference reff;
    DatabaseReference connectionReff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_devices);
        reff = FirebaseDatabase.getInstance().getReference().child("Devices");
        connectionReff = FirebaseDatabase.getInstance().getReference().child("Connection");

        recyclerView = (RecyclerView)findViewById(R.id.recyclerDevices);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        options = new FirebaseRecyclerOptions.Builder<Devices>().setQuery(connectionReff,Devices.class).build();
        adapter = new FirebaseRecyclerAdapter<Devices,MyDeviceViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final MyDeviceViewHolder holder, int position, @NonNull final Devices model) {

                if(model.getConnected() == true) {
                    DatabaseReference connRef = getRef(position);
                    String Id = connRef.getKey();

                    DatabaseReference deviceRef = reff.child(Id);
                    deviceRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            holder.textViewname.setText(dataSnapshot.child("name").getValue().toString());
                            holder.textViewmac.setText(dataSnapshot.child("mac").getValue().toString());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
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