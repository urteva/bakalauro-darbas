package com.example.adsinjava.model;

public class Contact {

    private String phoneNumb = "";
    private String id = "";

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String userId = "";
    private String contactDetails = "";

    public String getPhoneNumb() {
        return phoneNumb;
    }

    public void setPhoneNumb(String phoneNumb) {
        this.phoneNumb = phoneNumb;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }

    public Contact(String phoneNumb, String userId, String contactDetails, String otherDetails) {
        this.phoneNumb = phoneNumb;
        this.userId = userId;
        this.contactDetails = contactDetails;
        this.otherDetails = otherDetails;
    }

    private String otherDetails;

   public Contact(){};


}
