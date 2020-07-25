package com.example.meshnetapp;

import android.app.Application;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Module extends Application {
    public ArrayList<String> garrList = new ArrayList<>();
    public ArrayAdapter<String> garrAdp;
    public String gvalue_name;
    public String gvalue_mac;

    public String getGvalue_name() {
        return gvalue_name;
    }

    public void setGvalue_name(String gvalue_name) {
        this.gvalue_name = gvalue_name;
    }

    public String getGvalue_mac() {
        return gvalue_mac;
    }

    public void setGvalue_mac(String gvalue_mac) {
        this.gvalue_mac = gvalue_mac;
    }
}
