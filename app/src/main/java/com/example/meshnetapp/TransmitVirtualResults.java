package com.example.meshnetapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TransmitVirtualResults extends AppCompatActivity {

    Button done;
    TextView senders, receivers, messages;
    String senderNode, receiverNode, message, transID;
    DatabaseReference reff;
    DatabaseReference transRef;
    TransmitPacketsClass transmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transmit_virtual_results);

        done = findViewById(R.id.btnDoneSim);
        senders = findViewById(R.id.lblSenderNameSim);
        receivers = findViewById(R.id.lblReceiverNameSim);
        messages = findViewById(R.id.lblMessageSim);

        senderNode = getIntent().getStringExtra("senderNode");
        receiverNode = getIntent().getStringExtra("receiverNode");
        message = getIntent().getStringExtra("message");

        reff = FirebaseDatabase.getInstance().getReference("VirtualTransmitPacket");
        transmit = new TransmitPacketsClass();
        transmit.setSenderNode(senderNode);
        transmit.setReceiverNode(receiverNode);
        transmit.setMessage(message);

        transID = reff.push().getKey();
        transRef = reff.child(transID);
        transRef.setValue(transmit);

        transRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String senderNode = dataSnapshot.child("senderNode").getValue().toString();
                String receiverNode = dataSnapshot.child("receiverNode").getValue().toString();
                String message = dataSnapshot.child("message").getValue().toString();

                senders.setText(senderNode);
                receivers.setText(receiverNode);
                messages.setText(message);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransmitVirtualResults.this, SimulateMesh.class);
                startActivity(intent);
            }
        });

    }
}