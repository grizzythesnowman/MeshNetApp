package com.example.meshnetapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;

public class NodeActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, NodeEditDialog.EditDeviceDialogListener {
    Node node;
    TextView lblId;
    TextView lblMac;
    TextView lblName;
    TextView lblIP;
    Button btnEdit;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_node);

        lblId = findViewById(R.id.lblId);
        lblMac = findViewById(R.id.lblMac);
        lblName = findViewById(R.id.lblName);
        //lblIP = findViewById(R.id.lblIP);

        btnEdit = findViewById(R.id.editNode);
        btnDelete = findViewById(R.id.deleteNode);

        node = new Node(getIntent().getStringExtra("Id"), NodeActivity.this, NodeActivity.this, "info");

        btnEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("Id", node.Id);
                args.putString("Mac", node.Mac);
                args.putString("Name", node.Name);

                NodeEditDialog editDevice = new NodeEditDialog();
                editDevice.setArguments(args);
                editDevice.show(getSupportFragmentManager(), "Edit Node");
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(NodeActivity.this);
                builder.setMessage("Are you sure you want to delete this node?")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                new NodeDeleteTask(lblId.getText().toString(), NodeActivity.this, NodeActivity.this).execute();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
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
        args.putString("Id", node.Id);
        args.putString("Mac", node.Mac);
        args.putString("Name", node.Name);
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
                                new NodeDeleteTask(lblId.getText().toString(), NodeActivity.this, NodeActivity.this).execute();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            default:
                return false;
        }
    }

    public void updateInfo(Context myContext, Activity myActivity){
        node = new Node(node.Id, NodeActivity.this, NodeActivity.this, "info");
    }

    @Override
    public NodeActivity onFinishEditDeviceDialog(){
        return this;
    }

}
