package com.example.meshnetapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class VirtualOneWayDelayResults extends AppCompatActivity {

    Button btn;
    TextView senders,recievers, messages, tzero, tone,ttwo,tthree,txtonewaydelay;
    DatabaseReference reff;
    DatabaseReference delayRef;
    OneWayDelay onewaydelay;

    String delayId;
    String receiverName;
    String senderName;
    String message;
    Random myRandom = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual_one_way_delay_results);

        btn  = (Button)findViewById(R.id.view);
        senders = (TextView)findViewById(R.id.txtViewSender);
        recievers = (TextView)findViewById(R.id.txtViewReceiver);
        messages = (TextView)findViewById(R.id.txtViewMessage);
        tzero = (TextView)findViewById(R.id.txtViewT0);
        tone = (TextView)findViewById(R.id.txtViewT1);
        tthree = (TextView)findViewById(R.id.txtViewT3);
        txtonewaydelay = (TextView)findViewById(R.id.txtViewOwd);

        senderName = getIntent().getStringExtra("senderName");
        receiverName = getIntent().getStringExtra("receiverName");
        message = getIntent().getStringExtra("message");

        reff = FirebaseDatabase.getInstance().getReference("VirtualOneWayDelay");

        Float t0 = Float.parseFloat(String.valueOf(myRandom.nextInt(100)));
        Float t1 = Float.parseFloat(String.valueOf(myRandom.nextInt(100)));
        Float t2 = Float.parseFloat(String.valueOf(myRandom.nextInt(100)));
        Float t3 = Float.parseFloat(String.valueOf(myRandom.nextInt(100)));

        onewaydelay= new OneWayDelay();
        onewaydelay.setReceiverName(senderName);
        onewaydelay.setSenderName(receiverName);
        onewaydelay.setMessage(message);
        onewaydelay.setT0(t0);
        onewaydelay.setT1(t1);
        onewaydelay.setT2(t2);
        onewaydelay.setT3(t3);

        delayId = reff.push().getKey();
        delayRef = reff.child(delayId);
        delayRef.setValue(onewaydelay);

        delayRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String senderName = dataSnapshot.child("senderName").getValue().toString();
                String receiver = dataSnapshot.child("receiverName").getValue().toString();
                String message = dataSnapshot.child("message").getValue().toString();
                String t0 = dataSnapshot.child("t0").getValue().toString();
                String t1 = dataSnapshot.child("t1").getValue().toString();
                String t2 = dataSnapshot.child("t2").getValue().toString();
                String t3 = dataSnapshot.child("t3").getValue().toString();
                senders.setText(senderName);
                recievers.setText(receiver);
                messages.setText(message);
                tzero.setText(t0);
                tone.setText(t1);
                ttwo.setText(t2);
                tthree.setText(t3);
                Toast.makeText(VirtualOneWayDelayResults.this, "result", Toast.LENGTH_SHORT).show();
                float ft0 = Float.parseFloat(t0);
                float ft1 = Float.parseFloat(t1);
                float ft2 = Float.parseFloat(t2);
                float ft3 = Float.parseFloat(t3);

                float owd = (float)(((ft3-ft0)-(ft2-ft1))/2);
                String temp = Float.toString(owd);
                txtonewaydelay.setText(temp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}