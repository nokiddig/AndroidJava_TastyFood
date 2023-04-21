package com.example.tastyfoods.mvvm.model;

public class CartDetail {
    private int cartDetailId, userId;
    private int money, amount;

    public CartDetail(int cartDetailId, int userId, int money, int amount) {
        this.cartDetailId = cartDetailId;
        this.userId = userId;
        this.money = money;
        this.amount = amount;
    }

    public int getCartDetailId() {
        return cartDetailId;
    }

    public void setCartDetailId(int cartDetailId) {
        this.cartDetailId = cartDetailId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
