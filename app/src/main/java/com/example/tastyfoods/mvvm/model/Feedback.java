package com.example.tastyfoods.mvvm.model;

public class Feedback {
    private  String content;
    private int ratePoint, feedbackId, foodId, userId;

    public Feedback() {
    }

    public Feedback(String content, int ratePoint, int feedbackId, int foodId, int userId) {
        this.content = content;
        this.ratePoint = ratePoint;
        this.feedbackId = feedbackId;
        this.foodId = foodId;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
