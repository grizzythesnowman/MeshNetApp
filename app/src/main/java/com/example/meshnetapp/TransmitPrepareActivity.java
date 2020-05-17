package com.example.meshnetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TransmitPrepareActivity extends AppCompatActivity {

    Button Send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transmit_prepare);

        Send = findViewById(R.id.btnSend);

        Send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(TransmitPrepareActivity.this, TransmitResultActivity.class);
                startActivityForResult(intent,1);
            }
        });

    }
}
