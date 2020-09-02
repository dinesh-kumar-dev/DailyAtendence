package com.dineshpro.dailyatendence.model;

public class User {

   private String id;
   private String name;
   private String contact;
   private String date;
    private String time;

    public User(){}

    public User( String id,String name, String contact, String date, String time) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.date = date;
        this.time = time;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
