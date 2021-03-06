package com.example.meshnetapp;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.util.ArrayList;

public class NodeGetTask extends AsyncTask<String, String, String>{
    String Id;
    ArrayList<Node> Nodes;
    Context myContext;
    Activity myActivity;
    Node caller;
    String purpose;

    public NodeGetTask(String id, Context context, Activity activity, Node c, String p ) {
        myContext = context;
        myActivity = activity;
        caller = c;
        Id = id;
        purpose = p;
    }

    protected void onPreExecute() {

    }

    protected void onPostExecute(String Result){
        caller.onGetNodeCompleted(Nodes.get(0));
    }

    @Override
    protected String doInBackground(String... strings) {

        byte[] data;
        HttpGet httpget;
        StringBuffer buffer = null;
        HttpResponse response;
        HttpClient httpclient;
        InputStream inputStream;
        String JSONResult;
        //nameValuePairs.add(new BasicNameValuePair("amount", amount.trim()));

        try {
            httpclient = new DefaultHttpClient();
            httpget = new HttpGet("http://192.168.2.3/MeshNetWebService/Node.php?Id=" + Id);
            response = httpclient.execute(httpget);


            inputStream = response.getEntity().getContent();
            data = new byte[256];
            buffer = new StringBuffer();
            int len = 0;

            while (-1 != (len = inputStream.read(data)) ) {
                buffer.append(new String(data, 0, len));
            }
            JSONResult = buffer.toString();
            inputStream.close();

            if (JSONResult != null) {
                JSONArray jsonArray = null;

                try {
                    jsonArray = new JSONArray(JSONResult);
                    JSONObject jsonObject;
                    Node node;
                    Nodes = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {

                        node = new Node();

                        jsonObject = jsonArray.getJSONObject(i);
                        node.Id = jsonObject.getString("nodeId");
                        node.Mac = jsonObject.getString("nodeMac");
                        node.Name = jsonObject.getString("nodeName");
                        node.isOnline = false;
                        node.IP = "";
                        node.Signal = "";

                        Nodes.add(node);
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            myActivity.runOnUiThread(new Runnable(){
                public void run() {

                    switch (purpose){
                        case "info":
                            TextView Id;
                            TextView Mac;
                            TextView Name;

                            Id = myActivity.findViewById(R.id.lblId);
                            Mac = myActivity.findViewById(R.id.lblMac);
                            Name = myActivity.findViewById(R.id.lblName);

                            Id.setText(Nodes.get(0).Id);
                            Mac.setText(Nodes.get(0).Mac);
                            Name.setText(Nodes.get(0).Name);
                            //Toast.makeText(myContext, "Clicked txtId: " + Nodes.get(0).txtId ,Toast.LENGTH_SHORT).show();
                            break;
                        case "path":
                            break;
                    }


                }
            });
        }
        catch (Exception e) {
            final String s = e.getMessage();
            myActivity.runOnUiThread(new Runnable(){
                public void run() {
                    Toast.makeText(myActivity, "Error: " + s, Toast.LENGTH_LONG).show();
                }
            });
        }

        return null;
    }

}