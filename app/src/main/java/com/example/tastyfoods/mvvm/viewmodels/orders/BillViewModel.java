package com.example.tastyfoods.mvvm.viewmodels.orders;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tastyfoods.mvvm.model.Bill;
import com.example.tastyfoods.mvvm.model.BillDetail;
import com.example.tastyfoods.mvvm.model.CartDetail;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class BillViewModel extends ViewModel {
    private MutableLiveData<List<Bill>> mBill = new MutableLiveData<>();

    public LiveData<List<Bill>> getBills() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("bill").addSnapshotListener((value, error) -> {
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
    public void saveBill(Bill bill, List<CartDetail> cartDetails){
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
            firebaseFirestore.collection("bill")
                    .orderBy("billId", Query.Direction.DESCENDING)
                    .limit(1)
                    .get()
                    .addOnSuccessListener(querySnapshot1 -> {
                        long maxBillId = 1;
                        if (!querySnapshot1.isEmpty()) {
                            DocumentSnapshot documentSnapshot = querySnapshot1.getDocuments().get(0);
                            Long id = documentSnapshot.getLong("billId");
                            maxBillId = id==null?0:id + 1;
                        }
                        bill.setBillId((int) maxBillId);
                        this.saveBillDetailsByCartDetail(cartDetails, maxBillId);
                        firebaseFirestore.collection("bill").document(String.valueOf(maxBillId))
                                .set(bill);
                    });
    }

    public void saveBillDetails(BillDetail billDetail){

    }

    public void saveBillDetailsByCartDetail(List<CartDetail> cartDetails, long billId){
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("billDetail")
                .orderBy("billDetailId", Query.Direction.DESCENDING)
                .limit(1)
                .get()
                .addOnSuccessListener(querySnapshot1 -> {
                    long maxBillDetailId = 0;
                    if (!querySnapshot1.isEmpty()) {
                        DocumentSnapshot documentSnapshot = querySnapshot1.getDocuments().get(0);
                        Long id = documentSnapshot.getLong("billDetailId");
                        maxBillDetailId = id == null ? 0 : id;
                    }
                    for (CartDetail cartDetail : cartDetails) {
                        maxBillDetailId++;
                        BillDetail insert = new BillDetail((int) maxBillDetailId, (int) billId, cartDetail.getFoodId(), cartDetail.getMoney(), cartDetail.getAmount());
                        firebaseFirestore.collection("billDetail").document(String.valueOf(maxBillDetailId))
                                .set(insert);
                    }
                });
    }
}
