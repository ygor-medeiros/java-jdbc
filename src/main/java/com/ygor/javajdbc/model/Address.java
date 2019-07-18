package com.ygor.javajdbc.model;

public class Address {

    private int user_id;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zip_code;

    public Address(){}

    public Address(int user_id, String street, String city, String state, String country, String zip_code) {
        this.user_id = user_id;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip_code = zip_code;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zip_code;
    }

    public void setZipCode(String zip_code) {
        this.zip_code = zip_code;
    }
}
