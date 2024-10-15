package com.example.wastewizard;

public class complaints {

    String address, complaint;

    public complaints() {
    }

    public complaints(String address, String complaint) {
        this.address = address;
        this.complaint = complaint;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }
}
