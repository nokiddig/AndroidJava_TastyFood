package com.example.tastyfoods.mvvm.model;

public class Feedback {
    private  String content;
    private int ratePoint, feedbackId, foodId;

    public Feedback(String content, int ratePoint, int feedbackId, int foodId) {
        this.content = content;
        this.ratePoint = ratePoint;
        this.feedbackId = feedbackId;
        this.foodId = foodId;
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
