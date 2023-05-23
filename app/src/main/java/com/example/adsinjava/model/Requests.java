package com.example.adsinjava.model;

import java.io.Serializable;

public class Requests  {

    private String RequestText = "";
    private String Phone = "";

    private String id = "";

    public Requests() {}

    public String getRequestText() {
        return RequestText;
    }

    public void setRequestText(String requestText) {
        RequestText = requestText;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Requests(String RequestText, String Phone){
        this.RequestText = RequestText;
        this.Phone = Phone;

    }

    public  Requests(String RequestText, String Phone, String id)
    {
        this.RequestText=RequestText;
        this.Phone=Phone;
        this.id=id;
    }
}
