package com.example.tastyfoods.mvvm.model;

public class Delivery {
    private String Address, NameFood, Amount, Total, Price;

    public Delivery(String address, String nameFood, String amount, String total, String price) {
        Address = address;
        NameFood = nameFood;
        Amount = amount;
        Total = total;
        Price = price;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getNameFood() {
        return NameFood;
    }

    public void setNameFood(String nameFood) {
        NameFood = nameFood;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
