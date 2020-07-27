package com.example.meshnetapp;

public class Devices {
    String Mac;
    String Name;
    boolean Connected;

    public Devices() {
    }

    public Devices(String mac, String name, boolean connected) {
        Mac = mac;
        Name = name;
        Connected = connected;
    }

    public boolean getConnected() {
        return Connected;
    }

    public void setConnected(boolean connected) {
        Connected = connected;
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

    @Override
    public String toString() {
        return "Devices{" +
                "Mac='" + Mac + '\'' +
                ", Name='" + Name + '\'' +
                ", Connected=" + Connected +
                '}';
    }
}
