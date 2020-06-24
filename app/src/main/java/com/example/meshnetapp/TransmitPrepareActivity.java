package com.example.meshnetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class TransmitPrepareActivity extends AppCompatActivity {

    Button btnSend;
    EditText senderNode, receiverNode, message;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transmit_prepare);

        btnSend = findViewById(R.id.btnSend);
        backButton = findViewById(R.id.backButton);
        senderNode = findViewById(R.id.txtSender);
        receiverNode = findViewById(R.id.txtReceiver);
        message = findViewById(R.id.txtMessage);

        btnSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(TransmitPrepareActivity.this, TransmitResultActivity.class);
                intent.putExtra("senderNode", senderNode.getText().toString().trim());
                intent.putExtra("receiverNode", receiverNode.getText().toString().trim());
                intent.putExtra("message",message.getText().toString().trim());
                startActivityForResult(intent,1);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}
