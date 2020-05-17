package com.example.meshnetapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

public class NodeActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, NodeEditDialog.EditDeviceDialogListener {
    Node device;
    TextView Name;
    TextView IP;
    TextView Role;
    TextView Status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_node);

        Name = findViewById(R.id.lblName);
        IP = findViewById(R.id.lblId);
        Role = findViewById(R.id.lblMac);
        Status = findViewById(R.id.lblIP);
        device = new Node(getIntent().getStringExtra("txtId"), NodeActivity.this, NodeActivity.this, "info");

    }

    public void showMenu(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.menu_node);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        Bundle args = new Bundle();
        args.putString("txtId", device.Id);
        args.putString("txtName", device.Name);
        switch(menuItem.getItemId()){
            case R.id.editDevice:
                NodeEditDialog editDevice = new NodeEditDialog();
                editDevice.setArguments(args);
                editDevice.show(getSupportFragmentManager(), "Edit Node");
                return true;
            case R.id.sendMessage:
//                SendMessageDialog sendMessage = new SendMessageDialog();
//                sendMessage.setArguments(args);
//                sendMessage.show(getSupportFragmentManager(), "btnSend Message");
                return true;
            case R.id.deleteDevice:
                AlertDialog.Builder builder = new AlertDialog.Builder(NodeActivity.this);
                builder.setMessage("Are you sure you want to delete this node?")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                new NodeDeleteTask(IP.getText().toString(), NodeActivity.this, NodeActivity.this).execute();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            default:
                return false;
        }
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater(R.menu.menu_node, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

//    public void updateList(Context myContext, Activity myActivity){
////        GetNodesTask getDevicesTask = new GetNodesTask(myContext, myActivity, this);
////        getDevicesTask.execute();
////        Nodes = getDevicesTask.Nodes;
//        device.GetConnectedDevices(device.txtId, NodeActivity.this, NodeActivity.this, "info");
//    }

    public void updateInfo(Context myContext, Activity myActivity){
//        GetNodesTask getDevicesTask = new GetNodesTask(myContext, myActivity, this);
//        getDevicesTask.execute();
//        Nodes = getDevicesTask.Nodes;
        device = new Node(device.IP, NodeActivity.this, NodeActivity.this, "info");
        //device.GetConnectedDevices(device.txtId, NodeActivity.this, NodeActivity.this, "info");
    }

    @Override
    public NodeActivity onFinishEditDeviceDialog(){
        return this;
    }

//    @Override
//    public NodeActivity onFinishAddConnectionDialog(){
//        return this;
//    }

//    @Override
//    public NodeActivity onFinishSendMessageDialog(){
//        return this;
//    }
}
