package com.example.meshnetapp;

public class OneWayDelay {
    private String senderName;
    private String receiverName;
    private String message;
    private Float t0;
    private Float t1;
    private Float t2;
    private Float t3;
    public OneWayDelay() {
    }
    public OneWayDelay(String senderName, String receiverName, String message, Float t0, Float t1, Float t2, Float t3) {
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.message = message;
        this.t0 = t0;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiver) {
        this.receiverName = receiver;
    }

    public Float getT0() {
        return t0;
    }

    public void setT0(Float t0) {
        this.t0 = t0;
    }

    public Float getT1() {
        return t1;
    }

    public void setT1(Float t1) {
        this.t1 = t1;
    }

    public Float getT2() {
        return t2;
    }

    public void setT2(Float t2) {
        this.t2 = t2;
    }

    public Float getT3() {
        return t3;
    }

    public void setT3(Float t3) {
        this.t3 = t3;
    }
}
