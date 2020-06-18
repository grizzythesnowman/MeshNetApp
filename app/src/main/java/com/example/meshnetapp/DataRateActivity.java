package com.example.meshnetapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

public class DataRateActivity extends AppCompatActivity {

    TextView senders,recievers, tzero, tone,ttwo,tthree,txtonewaydelay;
    DatabaseReference reff;
    DatabaseReference delayRef;
    OneWayDelay onewaydelay;

    String delayId;
    String receiverName;
    String senderName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_rate);
        Toast.makeText(DataRateActivity.this, "Database succesfully Connected", Toast.LENGTH_SHORT).show();
        senders = (TextView)findViewById(R.id.textView8);
        recievers = (TextView)findViewById(R.id.textView9);
        tzero = (TextView)findViewById(R.id.textView10);
        tone = (TextView)findViewById(R.id.textView11);
        ttwo = (TextView)findViewById(R.id.textView12);
        tthree = (TextView)findViewById(R.id.textView13);
        txtonewaydelay = (TextView)findViewById(R.id.textView14);

        senderName = getIntent().getStringExtra("senderName");
        receiverName = getIntent().getStringExtra("receiverName");

        reff = FirebaseDatabase.getInstance().getReference("OneWayDelay");

        Float t0 = Float.parseFloat("0.00");
        Float t1 = Float.parseFloat("0.00");
        Float t2 = Float.parseFloat("0.00");
        Float t3 = Float.parseFloat("0.00");

        onewaydelay= new OneWayDelay();
        onewaydelay.setReceiverName(senderName);
        onewaydelay.setSenderName(receiverName);
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
                String t0 = dataSnapshot.child("t0").getValue().toString();
                String t1 = dataSnapshot.child("t1").getValue().toString();
                String t2 = dataSnapshot.child("t2").getValue().toString();
                String t3 = dataSnapshot.child("t3").getValue().toString();
                senders.setText(senderName);
                recievers.setText(receiver);

                tzero.setText(t0);
                tone.setText(t1);
                ttwo.setText(t2);
                tthree.setText(t3);
                Toast.makeText(DataRateActivity.this, "result", Toast.LENGTH_SHORT).show();
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