package com.example.adsinjava.model;

public class Tax {

    private String id ="";

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String userId="";
    public Tax(String userId, Double taxSum)
    {
        this.userId=userId;
        this.taxSum=taxSum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getTaxSum() {
        return taxSum;
    }

    public void setTaxSum(Double taxSum) {
        this.taxSum = taxSum;
    }

    private Double taxSum=null;

}
