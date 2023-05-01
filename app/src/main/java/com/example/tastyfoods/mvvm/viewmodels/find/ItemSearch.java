package com.example.tastyfoods.mvvm.viewmodels.find;

public class ItemSearch {
     int image;
     String name;
     String describe;

     String price;
     int button;

    public ItemSearch(int image, String name, String describe, String price, int button) {
        this.image = image;
        this.name = name;
        this.describe = describe;
        this.price = price;
        this.button = button;
    }
}
