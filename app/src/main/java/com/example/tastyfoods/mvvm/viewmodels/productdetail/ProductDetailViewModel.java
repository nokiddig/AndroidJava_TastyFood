package com.example.tastyfoods.mvvm.viewmodels.productdetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tastyfoods.mvvm.model.Food;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class ProductDetailViewModel extends ViewModel {

    private final MutableLiveData<Food> dataFood = new MutableLiveData<>();
    public ProductDetailViewModel(Food food) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("food").document(String.valueOf(food.getFoodId()))
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                        if(documentSnapshot != null){
                            dataFood.postValue(documentSnapshot.toObject(Food.class));
                        }
                    }
                });
    }

    public LiveData<Food> getDataFood() {
        return dataFood;
    }
}