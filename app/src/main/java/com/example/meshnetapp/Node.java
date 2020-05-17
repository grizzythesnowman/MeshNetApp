package com.example.meshnetapp;

import android.app.Activity;
import android.content.Context;

public class Node {
    public String Id;
    public String Mac;
    public String Name;
    public String IP;

    public Node(){}

    public Node(String id, String mac, String Name){}

    public Node(String Id, Context context, Activity activity, String purpose){
        GetNodeInfo(Id, context, activity, purpose);
    }

    public void GetNodeInfo(String Id, Context context, Activity activity, String purpose){
        new NodeGetTask(Id, context, activity, this, purpose).execute();
    }

    public void onGetNodeCompleted(Node d){
        Id = d.Id;
        Mac = d.Mac;
        Name = d.Name;
    }

}
