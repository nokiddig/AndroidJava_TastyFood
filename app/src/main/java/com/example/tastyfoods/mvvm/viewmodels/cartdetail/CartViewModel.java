package com.example.tastyfoods.mvvm.viewmodels.cartdetail;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tastyfoods.mvvm.model.CartDetail;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class CartViewModel extends ViewModel {
    private MutableLiveData<List<CartDetail>> mCart = new MutableLiveData<>();
    private MutableLiveData<Integer> total = new MutableLiveData<>(0);

    public MutableLiveData<List<CartDetail>> getCartDetails(String phoneNumber) {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("cartDetail").whereEqualTo("phoneNumber", phoneNumber).addSnapshotListener((value, error) -> {
            try {
                if (value != null) {
                    List<CartDetail> cartDetails = new ArrayList<>();
                    int sum = 0;
                    for (QueryDocumentSnapshot doc : value) {
                        CartDetail cartDetail = doc.toObject(CartDetail.class);
                        cartDetails.add(cartDetail);
                        sum += cartDetail.getMoney();
                        Log.d("Sy", cartDetail.getPhoneNumber() + " done      --------------------");
                    }
                    total.postValue(sum);
                    mCart.postValue(cartDetails);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return mCart;
    }

    public MutableLiveData<Integer> getTotal() {
        return total;
    }
}
