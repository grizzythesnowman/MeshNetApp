package com.example.meshnetapp;

public class Devices {
    String Mac;
    String Name;

    public Devices() {
    }

    public Devices(String mac, String name) {
        Mac = mac;
        Name = name;
    }

    public String getMac() {
        return Mac;
    }

    public void setMac(String mac) {
        Mac = mac;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
