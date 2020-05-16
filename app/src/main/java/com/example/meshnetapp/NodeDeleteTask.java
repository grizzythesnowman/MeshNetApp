package com.example.meshnetapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.snackbar.Snackbar;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;


public class NodeDeleteTask extends AsyncTask<String, String, String>{
    String IP;
    Context myContext;
    Activity myActivity;

    public NodeDeleteTask(String ip, Context context, Activity activity) {
        myContext = context;
        myActivity = activity;
        IP = ip;
    }

    protected void onPreExecute() {
        Intent intent = new Intent();
        intent.putExtra("Result", "Deleted");
        myActivity.setResult(RESULT_OK, intent);
        myActivity.finish();
    }

    protected void onPostExecute(String Result){
    }

    @Override
    protected String doInBackground(String... strings) {

        byte[] data;
        HttpPost httppost;
        StringBuffer buffer = null;
        HttpResponse response;
        HttpClient httpclient;
        InputStream inputStream;
        final String JSONResult;
        List<NameValuePair> DeviceInfo;
        DeviceInfo = new ArrayList<>(2);
        DeviceInfo.add(new BasicNameValuePair("ip", IP.trim()));

        try {
            httpclient = new DefaultHttpClient();
            httppost = new HttpPost("http://192.168.2.3/MeshNetWebService/DeleteNode.php");
            httppost.setEntity(new UrlEncodedFormEntity(DeviceInfo));
            response = httpclient.execute(httppost);

            inputStream = response.getEntity().getContent();
            data = new byte[256];
            buffer = new StringBuffer();
            int len = 0;

            while (-1 != (len = inputStream.read(data)) ) {
                buffer.append(new String(data, 0, len));
            }

            JSONResult = buffer.toString();
            inputStream.close();

            myActivity.runOnUiThread(new Runnable(){
                public void run() {

                    //Snackbar.make(myActivity.findViewById(R.id.mainactivity), JSONResult, Snackbar.LENGTH_LONG)
                    //        .setAction("Action", null).show();
                    //Toast.makeText(myActivity, JSONResult, Toast.LENGTH_LONG).show();

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
