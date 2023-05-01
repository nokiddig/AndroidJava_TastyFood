package com.example.tastyfoods.mvvm.viewmodels.productdetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tastyfoods.mvvm.model.Food;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ProductDetailViewModel extends ViewModel {

    private final MutableLiveData<String> dataName = new MutableLiveData<>();
    private final MutableLiveData<String> dataDescription = new MutableLiveData<>();
    private final MutableLiveData<String> dataPrice = new MutableLiveData<>();
    private final MutableLiveData<String> dataSell = new MutableLiveData<>();
    private final MutableLiveData<String> dataRatePoint = new MutableLiveData<>();
    private final MutableLiveData<String> dataImg = new MutableLiveData<>();
    public ProductDetailViewModel(Food food) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("food").document(String.valueOf(food.getFoodId()))
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                        if (documentSnapshot.exists()) {
                            String value = documentSnapshot.getString("name");
                            dataName.setValue(value);
                        }
                        if (documentSnapshot.exists()) {
                            String value = documentSnapshot.getString("description");
                            dataDescription.setValue(value);
                        }
                        if (documentSnapshot.exists()) {
                            long priceLong = documentSnapshot.getLong("price");
                            int value = (int) priceLong;
                            dataPrice.setValue(String.valueOf(value));
                        }
                        if (documentSnapshot.exists()) {
                            long priceLong = documentSnapshot.getLong("sales");
                            int value = (int) priceLong;
                            dataSell.setValue(String.valueOf(value));
                        }
                        if (documentSnapshot.exists()) {
                            double priceLong = documentSnapshot.getDouble("ratePoint");
                            double value = (double) priceLong;
                            dataRatePoint.setValue(String.valueOf(value));
                        }
                        if (documentSnapshot.exists()) {
                            String value = documentSnapshot.getString("image");
                            dataImg.setValue(value);
                        }
                    }
                });
    }

    public LiveData<String> getDataName() {
        return dataName;
    }
    public LiveData<String> getDataDescription() {
        return dataDescription;
    }
    public LiveData<String> getDataPrice() {
        return dataPrice;
    }
    public LiveData<String> getDataSell() {
        return dataSell;
    }
    public LiveData<String> getDataRatePoint() {return dataRatePoint;}
    public LiveData<String> getDataImg() {
        return dataImg;
    }
}