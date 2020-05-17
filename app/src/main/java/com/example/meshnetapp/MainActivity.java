package com.example.meshnetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    //FloatingActionButton fabAddDevice;
    ListView nodeList;
    Button transmit;
    ArrayList<Node> Nodes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nodeList = (ListView)findViewById(R.id.nodeList);
        transmit = findViewById(R.id.transmit);
        Nodes = new ArrayList<>();

        updateList(MainActivity.this, MainActivity.this);

        nodeList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                //Toast.makeText(MainActivity.this, "Clicked Id: " + Nodes.get(i).Id ,Toast.LENGTH_SHORT).show();
                String Id = Nodes.get(i).Id;
                Intent intent = new Intent(MainActivity.this, NodeActivity.class);
                intent.putExtra("Id", Id);
                startActivityForResult(intent,1);
            }
        });

        transmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TransmitPrepareActivity.class);
                startActivityForResult(intent,1);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK) {
                String strEditText = data.getStringExtra("Result");
                Toast.makeText(MainActivity.this, strEditText, Toast.LENGTH_LONG).show();
                if (strEditText.equals("Deleted")) updateList(MainActivity.this, MainActivity.this);
                if (strEditText.equals("Updated")) updateList(MainActivity.this, MainActivity.this);
            }
        }
    }
    public void updateList(Context myContext, Activity myActivity){
//        NodesGetTask getDevicesTask = new NodesGetTask(myContext, myActivity, this);
//        getDevicesTask.execute();
//        Nodes = getDevicesTask.Nodes;
        new NodesGetTask(myContext, myActivity, this, "list").execute();
    }

    public void onGetDeviceCompleted(ArrayList<Node> devices){
        Nodes = devices;
    }

//    @Override
//    public MainActivity onFinishAddDeviceDialog(){
//        return this;
//    }
}
