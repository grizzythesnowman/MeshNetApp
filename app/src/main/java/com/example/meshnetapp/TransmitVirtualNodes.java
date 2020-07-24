package com.example.meshnetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class TransmitVirtualNodes extends AppCompatActivity {

    Button snd;
    EditText senderNode, receiverNode, message;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transmit_virtual_nodes);

        snd = findViewById(R.id.btnSendVirtual);
        senderNode = findViewById(R.id.txtSenderVirtual);
        receiverNode = findViewById(R.id.txtReceiverVirtual);
        message = findViewById(R.id.txtMessageVirtual);
        backButton = findViewById(R.id.backButtonVirtual);

        snd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransmitVirtualNodes.this, TransmitVirtualResults.class);
                intent.putExtra("senderNode", senderNode.getText().toString().trim());
                intent.putExtra("receiverNode", receiverNode.getText().toString().trim());
                intent.putExtra("message",message.getText().toString().trim());
                startActivityForResult(intent,1);
            }
        });


    }
}