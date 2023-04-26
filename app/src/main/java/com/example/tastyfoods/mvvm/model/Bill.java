package com.example.tastyfoods.mvvm.model;

import java.util.Date;

public class Bill {
    private  int billId, userId;
    private boolean status;
    private Date dateTime;
    private int totalMoney;

    public Bill(int billId, int userId, boolean status, Date dateTime, int totalMoney) {
        this.billId = billId;
        this.userId = userId;
        this.status = status;
        this.dateTime = dateTime;
        this.totalMoney = totalMoney;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getBillID() {
        return billId;
    }

    public void setBillID(int billId) {
        this.billId = billId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

}
