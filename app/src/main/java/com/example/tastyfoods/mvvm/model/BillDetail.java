package com.example.tastyfoods.mvvm.model;

public class BillDetail {
    private int billDetailId, billId, foodId, money, amount;

    public BillDetail() {
    }

    public BillDetail(int billDetailId, int billId, int foodId, int money, int amount) {
        this.billDetailId = billDetailId;
        this.billId = billId;
        this.foodId = foodId;
        this.money = money;
        this.amount = amount;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
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
