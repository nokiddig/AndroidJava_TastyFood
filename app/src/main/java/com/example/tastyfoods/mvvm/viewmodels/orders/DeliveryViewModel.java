package com.example.tastyfoods.mvvm.viewmodels.orders;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tastyfoods.mvvm.model.Bill;
import com.example.tastyfoods.mvvm.model.Delivery;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DeliveryViewModel extends ViewModel {
    private MutableLiveData<Bill> mBill = new MutableLiveData<>();
    private final String COLLECTION_BILL = "bill", COLLECTION_BILL_DETAIL = "billDetail", COLLECTION_FOOD = "food",
            FIELD_STATUS = "status", FIELD_BILL_ID = "billId", FIELD_NAME = "name",
            FIELD_TOTAL = "totalMoney", FIELD_FOOD_ID = "foodId", FIELD_MONEY = "money", FIELD_AMOUNT = "amount";



    public LiveData<Bill> getBillComing()
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection(COLLECTION_BILL).whereEqualTo(FIELD_STATUS, false).addSnapshotListener((value, error) -> {
            try {
                Log.d("SDT", ": " + value.getDocuments().size());
                if (value != null) {
                    for (QueryDocumentSnapshot doc : value) {
                        if (doc.getString("phoneNumber").equals(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber()) ){
                            Bill bill = doc.toObject(Bill.class);
                            mBill.postValue(bill);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return mBill;
    }

    public void updateBill(Bill bill){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(COLLECTION_BILL).document(String.valueOf(bill.getBillId())).set(bill);
    }
}
