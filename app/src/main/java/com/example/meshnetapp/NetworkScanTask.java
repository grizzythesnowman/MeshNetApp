package com.example.meshnetapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.text.format.Formatter;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

class NetworkScanTask extends AsyncTask<Void, Void, Void> {

    private static final String TAG = "nstask";

    private WeakReference<Context> mContextRef;

    public NetworkScanTask(Context context) {
        mContextRef = new WeakReference<Context>(context);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Log.d(TAG, "Let's sniff the network");

        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/net/arp"));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] splitted = line.split(" +");
                if (splitted != null && splitted.length >= 4) {
                    String ip = splitted[0];
                    String mac = splitted[3];
                    if (mac.matches("dc:4f:22:60:84:fa".toLowerCase())) {
                        Log.i(TAG, "IP Address for " + mac + " has been found. ("+ip+")");
//                        Node thisNode = new Node(ip, mac);
//                        publishProgress(thisNode);
                    }
                }
            }

        } catch(Exception e){
            Log.e("MyClass", "Exception reading the arp table.", e);
        }

//        try {
//            Context context = mContextRef.get();
//
//            if (context != null) {
//
//                ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//                WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
//
//                WifiInfo connectionInfo = wm.getConnectionInfo();
//                int ipAddress = connectionInfo.getIpAddress();
//                String ipString = Formatter.formatIpAddress(ipAddress);
//
//
//                Log.d(TAG, "activeNetwork: " + String.valueOf(activeNetwork));
//                Log.d(TAG, "ipString: " + String.valueOf(ipString));
//
//                String prefix = ipString.substring(0, ipString.lastIndexOf(".") + 1);
//                Log.d(TAG, "prefix: " + prefix);
//
//                for (int i = 0; i < 255; i++) {
//                    String testIp = prefix + String.valueOf(i);
//
//                    InetAddress address = InetAddress.getByName(testIp);
//                    boolean reachable = address.isReachable(1000);
//                    String hostName = address.getCanonicalHostName();
//
//                    if (reachable){
//                        Log.i(TAG, "Host: " + String.valueOf(hostName) + "(" + String.valueOf(testIp) + ") is reachable!");
//
//
////                        NetworkInterface ni =  NetworkInterface.getByInetAddress(address);
////
////                        int retryCounter = 0;
////                        while(ni == null && retryCounter < 30){
////                            Log.i(TAG, "NetworkInterface for " + String.valueOf(hostName) + " failed... Retrying...");
////
////                            ni =  NetworkInterface.getByInetAddress(address);
////
////                            retryCounter++;
////                        }
////
////                        if (ni != null) {
////                            byte[] mac = ni.getHardwareAddress();
////
////                            if (mac != null) {
////                                /*
////                                 * Extract each array of mac address and convert it
////                                 * to hexadecimal with the following format
////                                 * 08-00-27-DC-4A-9E.
////                                 */
////                                for (int j = 0; j < mac.length; j++) {
////                                    Log.i(TAG, "Address for " + String.valueOf(hostName) + " has been found.");
//////                                    System.out.format("%02X%s",
//////                                            mac[j], (j < mac.length - 1) ? "-" : "");
////                                }
////                            } else {
////                                Log.i(TAG, "Address for " + String.valueOf(hostName) + " doesn't exist or is not accessible.");
////                            }
////                        }
////                        else {
////                            Log.i(TAG, "NetworkInterface for " + String.valueOf(hostName) + " failed.");
////                        }
//                    }
//                    else Log.i(TAG, "Host: " + String.valueOf(hostName) + "(" + String.valueOf(testIp) + ") is not reachable...");
//
//                }
//
//            }
//        } catch (Throwable t) {
//            Log.e(TAG, "Well that's not good.", t);
//        }

        return null;
    }
}