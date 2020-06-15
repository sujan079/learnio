package com.example.learnio.model;

import java.io.Serializable;

public class User implements Serializable {

    private String uid;

    private String username;
    private String email;
    private String imageUrl;
    private String phone;

    public User() {
    }

    public User(String uid, String username, String email, String imageUrl) {
        this.uid = uid;
        this.username = username;
        this.email = email;
        this.imageUrl = imageUrl;
        this.phone="";
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
