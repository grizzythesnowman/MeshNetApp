package com.example.meshnetapp;

import android.app.Activity;
import android.content.Context;

public class Node {
    public String Id;
    public String Mac;
    public String Name;
    public String IP;

    public Node(){}

    public Node(String IP, Context context, Activity activity, String purpose){
        GetNodeInfo(IP, context, activity, purpose);
    }

    public void GetNodeInfo(String IP, Context context, Activity activity, String purpose){
        new NodeGetTask(IP, context, activity, this, purpose).execute();
    }

    public void onGetNodeCompleted(Node d){
        Id = d.Id;
        Mac = d.Mac;
        Name = d.Name;
        IP = d.IP;
    }

}
