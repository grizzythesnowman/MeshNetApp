package com.example.meshnetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SimulateMesh extends AppCompatActivity {

    Button add, delete, transmit, onewaydelay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulate_mesh);

        add = findViewById(R.id.btnAddNodes);
        delete = findViewById(R.id.removeNodes);
        transmit = findViewById(R.id.transmitSim);
        onewaydelay = findViewById(R.id.onewaydelaySim);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimulateMesh.this, AddVirtualNodes.class);
                startActivity(intent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimulateMesh.this, DeleteNode.class);
                startActivity(intent);
            }
        });
        transmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimulateMesh.this, TransmitVirtualNodes.class);
                startActivity(intent);

            }

        });
        onewaydelay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimulateMesh.this, VirtualOneWayDelay.class);
                startActivity(intent);

            }
        });


    }
}