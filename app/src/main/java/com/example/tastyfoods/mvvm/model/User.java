package com.example.tastyfoods.mvvm.model;

import java.util.Date;

public class User {
    private  String name, address;
    private int money;
    private String phoneNumber, password, image;
    private Date birthday;

    public User(String name, String address, int money, String phoneNumber, String password, String image, Date birthday) {
        this.name = name;
        this.address = address;
        this.money = money;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.image = image;
        this.birthday = birthday;
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
