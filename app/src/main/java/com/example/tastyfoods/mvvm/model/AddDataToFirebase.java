package com.example.tastyfoods.mvvm.model;


import android.util.Log;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddDataToFirebase {
    private static final FirebaseFirestore FIRESTORE = FirebaseFirestore.getInstance();

    public void addFood(Food food) {
        Map<String, Object> temp = new HashMap<>();
        temp.put("name", food.getName());
        temp.put("image", food.getImage());
        temp.put("description", food.getDescription());
        temp.put("price", food.getPrice());
        temp.put("ratePoint", food.getRatePoint());
        temp.put("categoryId", food.getCategoryId());
        // Add a new document with a generated ID
        FIRESTORE.collection("food")
                .add(temp)
                .addOnSuccessListener(documentReference -> Log.d("Debug", "DocumentSnapshot added with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w("Debug", "Error adding document", e));
    }

    public void addCategory(Category category) {
        Map<String, Object> temp = new HashMap<>();
        temp.put("categoryId", category.getCategoryId());
        temp.put("image", category.getImage());
        temp.put("description", category.getDescription());
        temp.put("name", category.getName());
        // Add a new document with a generated ID
        FIRESTORE.collection("category")
                .add(temp)
                .addOnSuccessListener(documentReference -> Log.d("Debug", "DocumentSnapshot added with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w("Debug", "Error adding document", e));
    }
    public void addBill(Bill bill)
    {
        Map<String, Object> temp = new HashMap<>();
        temp.put("BillId", bill.getBillID());
        temp.put("Status", bill.isStatus());
        temp.put("DateTime", bill.getDateTime());
        temp.put("totalMoney", bill.getTotalMoney());
        // Add a new document with a generated ID
        FIRESTORE.collection("bill")
                .add(temp)
                .addOnSuccessListener(documentReference -> Log.d("Debug", "DocumentSnapshot added with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w("Debug", "Error adding document", e));
    }
    public  void addBillDetail(BillDetail billDetail)
    {
        Map<String, Object>temp= new HashMap<>();
        temp.put("BillDetailID", billDetail.getBillDetailId());
        temp.put("BillId", billDetail.getBillId());
        temp.put("Money", billDetail.getMoney());
        temp.put("Amount", billDetail.getAmount());
        FIRESTORE.collection("billetail")
                .add(temp)
                .addOnSuccessListener(documentReference -> Log.d("Debug", "DocumentSnapshot added with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w("Debug", "Error adding document", e));
    }
}
