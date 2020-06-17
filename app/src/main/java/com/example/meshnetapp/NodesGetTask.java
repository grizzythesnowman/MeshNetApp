package com.example.meshnetapp;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NodesGetTask extends AsyncTask<String, String, String>{
    String recievedDevices;
    ArrayList<Node> Nodes;
    Context myContext;
    Activity myActivity;
    MainActivity mainActivity;
    //TransmitMessage transmitMessage;
    String purpose;
    ArrayList<Node> caller;

    public NodesGetTask(Context context, Activity activity, MainActivity caller, String p ) {
        myContext = context;
        myActivity = activity;
        mainActivity = caller;
        purpose = p;
    }

//    public NodesGetTask(Context context, Activity activity, TransmitMessage caller, String p ) {
//        myContext = context;
//        myActivity = activity;
//        //transmitMessage = caller;
//        purpose = p;
//    }

    protected void onPreExecute() {

    }

    protected void onPostExecute(String Result){
        mainActivity.onGetDeviceCompleted(Nodes);
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
            httpget = new HttpGet("http://192.168.2.3/MeshNetWebService/Nodes.php");
            response = httpclient.execute(httpget);


            inputStream = response.getEntity().getContent();
            data = new byte[256];
            buffer = new StringBuffer();
            int len = 0;

            while (-1 != (len = inputStream.read(data)) ) {
                buffer.append(new String(data, 0, len));
            }

            if(buffer.toString().equals("")) return null;

            JSONResult = buffer.toString();
            inputStream.close();

            if (JSONResult != null) {
                JSONArray jsonArray = null;

                try {
                    jsonArray = new JSONArray(JSONResult);
                    JSONObject jsonObject;
                    Node device;
                    Nodes = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {

                        device = new Node();

                        jsonObject = jsonArray.getJSONObject(i);
                        device.Id = jsonObject.getString("nodeId");
                        device.Mac = jsonObject.getString("nodeMac");
                        device.Name = jsonObject.getString("nodeName");
                        device.IP = jsonObject.getString("nodeIP");

                        Nodes.add(device);
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            myActivity.runOnUiThread(new Runnable(){
                public void run() {
                    switch(purpose){
                        case "list":
                            ListView devicelist;

                            //devicelist = myActivity.findViewById(R.id.nodeList);

                            LinkedHashMap<String, String> ListFormat = new LinkedHashMap<>();
                            //ArrayList<String> Label = new ArrayList<>();

                            for(int i = 0; i < Nodes.size(); i++){
                                //Label.add(Nodes.get(i).txtName + " [" + Nodes.get(i).txtId +"]");
                                ListFormat.put(Nodes.get(i).Name , Nodes.get(i).Mac);
                            }

                            //ArrayAdapter adapter = new ArrayAdapter(myContext, android.R.layout.simple_list_item_1, Label);

                            List<LinkedHashMap<String, String>> listitems = new ArrayList<>();

                            SimpleAdapter adapter = new SimpleAdapter(myContext, listitems, R.layout.list_item_node,
                                    new String[]{"Title", "Subtitle" },
                                    new int[]{R.id.title, R.id.subtitle});

                            Iterator iterator = ListFormat.entrySet().iterator();

                            while (iterator.hasNext()){
                                LinkedHashMap<String, String> resultsMap = new LinkedHashMap<>();
                                Map.Entry pair = (Map.Entry)iterator.next();
                                resultsMap.put("Title", pair.getKey().toString());
                                resultsMap.put("Subtitle", pair.getValue().toString());
                                listitems.add(resultsMap);
                            }

                            //devicelist.setAdapter(adapter);
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
