package com.example.adsinjava.model;

import java.io.Serializable;

public class Ads implements Serializable {
    private String description = "";
    private String phone="";
    private String price="";
    private String address="";
    private String imgURL="";
    private String type="";
    private String id="";
    private String userId="";

public Ads(){}

    public Ads(String description, String phone, String price, String address,  String type,  String userId) {
        this.description = description;
        this.phone = phone;
        this.price = price;
        this.address = address;

        this.type = type;
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}