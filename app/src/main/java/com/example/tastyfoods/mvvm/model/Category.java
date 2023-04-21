package com.example.tastyfoods.mvvm.model;

public class Category {
    private String name, image, description;
    private int categoryId;

    public Category(String name, String image, String description, int categoryId) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.categoryId = categoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
