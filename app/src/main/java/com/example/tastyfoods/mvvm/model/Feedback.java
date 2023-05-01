package com.example.tastyfoods.mvvm.model;

public class Feedback {
    private  String content, phoneNumber;
    private int ratePoint, feedbackId, foodId;

    public Feedback() {
    }

    public Feedback(String content, String phoneNumber, int ratePoint, int feedbackId, int foodId) {
        this.content = content;
        this.phoneNumber = phoneNumber;
        this.ratePoint = ratePoint;
        this.feedbackId = feedbackId;
        this.foodId = foodId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRatePoint() {
        return ratePoint;
    }

    public void setRatePoint(int ratePoint) {
        this.ratePoint = ratePoint;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
}
