package com.example.meshnetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddVirtualNodes extends AppCompatActivity {

    Button add;
    EditText name, mac;
    DatabaseReference reffDevices, reff;

    String  deviceID;
    Devices devices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_virtual_nodes);

        add = findViewById(R.id.btnAdd);
        name = findViewById(R.id.txtdevicenamesim);
        mac = findViewById(R.id.txtMacSim);
        reff = FirebaseDatabase.getInstance().getReference("VirtualDevice");

        devices = new Devices();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                devices.setName(name.getText().toString().trim());
                devices.setMac(mac.getText().toString().trim());

                deviceID = reff.push().getKey();
                reffDevices = reff.child(deviceID);
                reffDevices.setValue(devices);

                Toast.makeText(AddVirtualNodes.this, "Node was added",Toast.LENGTH_LONG).show();

            }
        });

    }
}