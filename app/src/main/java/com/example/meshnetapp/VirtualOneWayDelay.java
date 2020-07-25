package com.example.meshnetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class VirtualOneWayDelay extends AppCompatActivity {
    Button btn;
    EditText senderName, receiver, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual_one_way_delay);

        senderName = findViewById(R.id.senderNamevirtual);
        receiver = findViewById(R.id.virtualreceiver);
        message = findViewById(R.id.messagevirtual);

        btn = findViewById(R.id.btnsendvirtual);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDataRate();
            }
        });

    }
    public void openDataRate()
    {
        Intent intent = new Intent(this, VirtualOneWayDelayResults.class);
        intent.putExtra("receiverName", receiver.getText().toString().trim());
        intent.putExtra("senderName", senderName.getText().toString().trim());
        intent.putExtra("message", message.getText().toString().trim());
        startActivity(intent);
    }


}