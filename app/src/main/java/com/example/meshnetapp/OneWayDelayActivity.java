package com.example.meshnetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OneWayDelayActivity extends AppCompatActivity {
    Button btn;
    EditText senderName, receiver;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_way_delay);
        senderName = (EditText)findViewById(R.id.editTextTextPersonName);
        receiver = (EditText)findViewById(R.id.editTextTextPersonName2);
        btn = findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           openDataRate();

            }
        });

    }
    public void openDataRate()
    {
        Intent intent = new Intent(this, DataRateActivity.class);
        intent.putExtra("receiverName", receiver.getText().toString().trim());
        intent.putExtra("senderName", senderName.getText().toString().trim());
        startActivity(intent);
    }

}