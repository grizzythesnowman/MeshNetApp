package com.example.meshnetapp;

public class TransmitPacketsClass {
    private String senderNode;
    private String receiverNode;
    private String message;

    public TransmitPacketsClass() {
    }

    public TransmitPacketsClass(String senderNode, String receiverNode, String message) {
        this.senderNode = senderNode;
        this.receiverNode = receiverNode;
        this.message = message;
    }

    public String getSenderNode() {
        return senderNode;
    }

    public void setSenderNode(String senderNode) {
        this.senderNode = senderNode;
    }

    public String getReceiverNode() {
        return receiverNode;
    }

    public void setReceiverNode(String receiverNode) {
        this.receiverNode = receiverNode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
