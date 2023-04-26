package com.example.tastyfoods.mvvm.viewmodels.orders;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tastyfoods.mvvm.model.Bill;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class BillViewModel extends ViewModel {
    private MutableLiveData<List<Bill>> mBill = new MutableLiveData<>();

    public LiveData<List<Bill>> getBills() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("bIll").addSnapshotListener((value, error) -> {
            try {
                if (value != null) {
                    List<Bill> bills = new ArrayList<>();
                    for (QueryDocumentSnapshot doc : value) {
                        Bill bill = doc.toObject(Bill.class);
                        bills.add(bill);
                    }
                    mBill.postValue(bills);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return mBill;
    }
}
