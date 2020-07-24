package com.example.meshnetapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DeleteNode extends AppCompatActivity {

  private Spinner spinner;
    Button delete;
    private DatabaseReference reff;
    private ArrayList<String> arrayList = new ArrayList<>();
    Devices device = new Devices();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_node);
        spinner = findViewById(R.id.spinner);
        delete = findViewById(R.id.deleteButton);

        reff = FirebaseDatabase.getInstance().getReference();
        reff.child("VirtualDevice").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for(DataSnapshot item : dataSnapshot.getChildren()){
                    arrayList.add(item.child("name").getValue(String.class));
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(DeleteNode.this,R.layout.spinner_layout,arrayList);
                spinner.setAdapter(arrayAdapter);

               // device = (Devices) spinner.getSelectedItem();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

       final String name = device.getName();

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              reff =  FirebaseDatabase.getInstance().getReference("VirtualDevice").child(name);
              reff.removeValue();
            }
        });
    }
}