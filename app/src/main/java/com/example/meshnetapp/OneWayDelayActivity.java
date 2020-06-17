package com.example.meshnetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OneWayDelayActivity extends AppCompatActivity {
    Button btn;
    EditText senderName, receiverName;
    DatabaseReference reff;
    OneWayDelay onewaydelay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_way_delay);
        senderName = (EditText)findViewById(R.id.editTextTextPersonName);
        receiverName = (EditText)findViewById(R.id.editTextTextPersonName2);
        btn = findViewById(R.id.button2);
        onewaydelay= new OneWayDelay();
        reff = FirebaseDatabase.getInstance().getReference().child("OneWayDelay");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Float t0 = Float.parseFloat("2904.148");
                Float t1 = Float.parseFloat("2903.4");
                Float t2 = Float.parseFloat("2903.404");
                Float t3 = Float.parseFloat("2908.344");
                onewaydelay.setReceiver(receiverName.getText().toString().trim());
                onewaydelay.setSenderName(senderName.getText().toString().trim());
                onewaydelay.setT0(t0);
                onewaydelay.setT1(t1);
                onewaydelay.setT2(t2);
                onewaydelay.setT3(t3);
                reff.push().setValue(onewaydelay);


                openDataRate();
            }
        });

    }
    public void openDataRate()
    {
        Intent intent = new Intent(this, DataRateActivity.class);

        startActivity(intent);
    }

}