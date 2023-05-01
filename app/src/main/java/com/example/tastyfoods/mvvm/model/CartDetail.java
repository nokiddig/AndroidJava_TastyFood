package com.example.tastyfoods.mvvm.model;

public class CartDetail {
    private int cartDetailId, foodId, money, amount;
    private String phoneNumber;

    public CartDetail() {
    }

    public CartDetail(int cartDetailId, int foodId, int money, int amount, String phoneNumber) {
        this.cartDetailId = cartDetailId;
        this.foodId = foodId;
        this.money = money;
        this.amount = amount;
        this.phoneNumber = phoneNumber;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getCartDetailId() {
        return cartDetailId;
    }

    public void setCartDetailId(int cartDetailId) {
        this.cartDetailId = cartDetailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
