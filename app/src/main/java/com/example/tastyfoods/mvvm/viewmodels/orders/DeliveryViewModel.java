package com.example.tastyfoods.mvvm.viewmodels.orders;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tastyfoods.mvvm.view.orders.DeliveryFragment;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class DeliveryViewModel extends ViewModel {
    private MutableLiveData<List<DeliveryFragment>> mDelivery = new MutableLiveData<>();



    private LiveData<List<DeliveryFragment>> getDelivery()
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<DeliveryFragment>deliveries = new ArrayList<>();
        CollectionReference billCollection = db.collection("bill");
        CollectionReference billDetailCollection = db.collection("billDetail");
        CollectionReference foodCollection = db.collection("food");
        CollectionReference userCollection = db.collection("register");

        return mDelivery;
    }
}
