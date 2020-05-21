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

        String ip = "";
        String mac = "dc:4f:22:60:84:fa";

        try {

            bufferedReader = new BufferedReader(new FileReader("/proc/net/arp"));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] splitted = line.split(" +");
                if (splitted != null && splitted.length >= 4) {
                    String foundIp = splitted[0];
                    String foundMac = splitted[3];
                    if (foundMac.matches(mac.toLowerCase())) {

                        Context context = mContextRef.get();

                        if (context != null) {

                            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                            WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

                            WifiInfo connectionInfo = wm.getConnectionInfo();
                            int ipAddress = connectionInfo.getIpAddress();
                            String ipString = Formatter.formatIpAddress(ipAddress);

                            Log.d(TAG, "activeNetwork: " + String.valueOf(activeNetwork));
                            Log.d(TAG, "ipString: " + String.valueOf(ipString));

                            String prefix = ipString.substring(0, ipString.lastIndexOf(".") + 1);
                            Log.d(TAG, "prefix: " + prefix);

                            InetAddress address = InetAddress.getByName(foundIp);
                            boolean reachable = address.isReachable(1000);
                            String hostName = address.getCanonicalHostName();

                            if (reachable){
                                Log.i(TAG, "Host: " + String.valueOf(hostName) + "(" + String.valueOf(foundIp) + ") is reachable!");
                            }
                            else Log.i(TAG, "Host: " + String.valueOf(hostName) + "(" + String.valueOf(foundIp) + ") is not reachable...");

                        }

                        Log.i(TAG, "IP Address for " + mac + " has been found. ("+ip+")");
                    }
                }
            }



        } catch(Exception e){
            Log.e("MyClass", "Exception reading the arp table.", e);
        }

        return null;
    }
}