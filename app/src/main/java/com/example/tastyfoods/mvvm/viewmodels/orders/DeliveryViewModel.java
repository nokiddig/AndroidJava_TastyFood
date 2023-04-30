package com.example.tastyfoods.mvvm.viewmodels.orders;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tastyfoods.mvvm.view.orders.Delivery;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class DeliveryViewModel extends ViewModel {
    private MutableLiveData<List<Delivery>> mDelivery = new MutableLiveData<>();



    private LiveData<List<Delivery>> getDelivery()
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<Delivery>deliveries = new ArrayList<>();
        CollectionReference billCollection = db.collection("bill");
        CollectionReference billDetailConllection = db.collection("billDetail");
        CollectionReference foodCollection = db.collection("food");
        CollectionReference userCollection = db.collection("register");



        return mDelivery;
    }
}
