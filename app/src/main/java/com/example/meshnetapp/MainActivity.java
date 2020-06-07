package com.example.meshnetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    //FloatingActionButton fabAddDevice;
    ListView nodeList;
    Button transmit;
    ArrayList<Node> Nodes;

    WifiManager wifiManager;
    List<ScanResult> scanResults;

    DatabaseReference refRoot;
    DatabaseReference refDevices;
    DatabaseReference refPayloads;

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
                //Toast.makeText(MainActivity.this, "Clicked txtId: " + Nodes.get(i).txtId ,Toast.LENGTH_SHORT).show();
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

        refRoot = FirebaseDatabase.getInstance().getReference();
        refDevices = refRoot.child("Devices");
        refPayloads = refRoot.child("Payloads");

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

        new NetworkScanTask(MainActivity.this).execute();

//        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//
//        if( !wifiManager.isWifiEnabled()){
//            Toast.makeText(this, "Wifi is diabled", Toast.LENGTH_SHORT).show();
//            wifiManager.setWifiEnabled(true);
//        }
//        scanWifi();
    }

//    public void scanWifi(){
//        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
//        wifiManager.startScan();
//    }
//
//    BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            scanResults = wifiManager.getScanResults();
//            unregisterReceiver(this);
//            for(ScanResult scanResult : scanResults){
//                for(int i = 0; i < Nodes.size(); i++){
//                    if(Nodes.get(i).Mac.equals(scanResult.BSSID)){
//                        Nodes.get(i).isOnline = true;
//                    }
//                }
//                Log.d("Wifi Scan","Found: " + scanResult.SSID + " Mac Adress: " + scanResult.BSSID);
//            }
//
////            for(int i = 0; i < Nodes.size(); i++){
////                if(Nodes.get(i).isOnline){
////                    showToast(Nodes.get(i).Name + " is online");
////                }
////                else showToast(Nodes.get(i).Name + " is offline");
////            }
//        }
//    };

    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public MainActivity onFinishAddDeviceDialog(){
//        return this;
//    }
}
