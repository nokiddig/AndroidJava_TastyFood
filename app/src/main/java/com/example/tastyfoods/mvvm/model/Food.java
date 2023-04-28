package com.example.tastyfoods.mvvm.model;

import java.io.Serializable;

public class Food implements Serializable {

    private int foodId;
    private String name, description, image;
    private double ratePoint;
    private int price, categoryId;

    public Food(int foodId, String name, String description, String image, double ratePoint, int price, int categoryId) {
        this.foodId = foodId;
        this.name = name;
        this.description = description;
        this.image = image;
        this.ratePoint = ratePoint;
        this.price = price;
        this.categoryId = categoryId;
    }

    public Food() {
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
