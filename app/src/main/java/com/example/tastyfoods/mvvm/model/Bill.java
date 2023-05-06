package com.example.tastyfoods.mvvm.model;

import java.util.Date;

public class Bill {
    private  int billId, totalMoney;
    private boolean status;
    private Date dateTime;
    private String phoneNumber, address;

    public Bill() {
    }

    public Bill(int billId, int totalMoney, boolean status, Date dateTime, String phoneNumber, String address) {
        this.billId = billId;
        this.totalMoney = totalMoney;
        this.status = status;
        this.dateTime = dateTime;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
