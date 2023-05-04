package com.example.tastyfoods.mvvm.model;

import java.io.Serializable;

public class Food implements Serializable {

    private int foodId, price, categoryId, sales;
    private String name, description, image;
    private double ratePoint;

    public Food(int foodId, int price, int categoryId, int sales, String name, String description, String image, double ratePoint) {
        this.foodId = foodId;
        this.price = price;
        this.categoryId = categoryId;
        this.sales = sales;
        this.name = name;
        this.description = description;
        this.image = image;
        this.ratePoint = ratePoint;
    }

    public Food() {
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getRatePoint() {
        return ratePoint;
    }

    public void setRatePoint(double ratePoint) {
        this.ratePoint = ratePoint;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
