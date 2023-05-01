package com.example.tastyfoods.mvvm.model;

public class ItemSearch {
     private int image;
     private String name, describe, price;
     private int button;

    public ItemSearch(int image, String name, String describe, String price, int button) {
        this.image = image;
        this.name = name;
        this.describe = describe;
        this.price = price;
        this.button = button;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getButton() {
        return button;
    }

    public void setButton(int button) {
        this.button = button;
    }
}
