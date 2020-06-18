package com.example.meshnetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    //FloatingActionButton fabAddDevice;
    ListView nodeList;
    Button transmit, onewaydelay, devices;
    ArrayList<Node> Nodes;

    WifiManager wifiManager;
    List<ScanResult> scanResults;

    DatabaseReference refRoot;
    DatabaseReference refDevices;
    DatabaseReference refPayloads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //nodeList = (ListView)findViewById(R.id.nodeList);
        transmit = findViewById(R.id.transmit);
        onewaydelay = findViewById(R.id.onewaydelay);
        devices = findViewById(R.id.viewdevices);
        Nodes = new ArrayList<>();

        transmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TransmitPrepareActivity.class);
                startActivityForResult(intent,1);
            }
        });
        onewaydelay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OneWayDelayActivity.class);
                intent.putExtra("t0", DataRateActivity.class);
                intent.putExtra("t1", DataRateActivity.class);
                intent.putExtra("t2", DataRateActivity.class);
                intent.putExtra("t3", DataRateActivity.class);
                intent.putExtra("senderName", DataRateActivity.class);
                intent.putExtra("receiver", DataRateActivity.class);
                startActivity(intent);
            }
        });
        devices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewDevices.class);
                startActivity(intent);
            }
        });

        refRoot = FirebaseDatabase.getInstance().getReference();
        refDevices = refRoot.child("Devices");
        refPayloads = refRoot.child("Payloads");

    }

    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
