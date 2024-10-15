package com.example.wastewizard;

public class users {
    String name, username, password, address;

    public users() {
    }

    public users(String name,String address, String username, String password) {
        this.name = name;
        this.address = address;
        this.username = username;
        this.password = password;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}
