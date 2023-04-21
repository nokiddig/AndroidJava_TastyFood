package com.example.tastyfoods.mvvm.model;

public class BillDetail {
    private  int billDetailId, billId;
    private int money, amount;

    public BillDetail(int billDetailId, int billId, int money, int amount) {
        this.billDetailId = billDetailId;
        this.billId = billId;
        this.money = money;
        this.amount = amount;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(int billDetailId) {
        this.billDetailId = billDetailId;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
