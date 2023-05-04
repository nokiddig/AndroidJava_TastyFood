package com.example.tastyfoods.mvvm.model;

import java.util.Date;

public class Bill {
    private  int billId, totalMoney;
    private boolean status;
    private Date dateTime;
    private String phoneNumber;

    public Bill() {
    }

    public Bill(int billId, boolean status, Date dateTime, int totalMoney, String phoneNumber) {
        this.billId = billId;
        this.status = status;
        this.dateTime = dateTime;
        this.totalMoney = totalMoney;
        this.phoneNumber = phoneNumber;
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
