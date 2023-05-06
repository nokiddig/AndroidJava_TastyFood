package com.example.tastyfoods.mvvm.viewmodels.cartdetail;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tastyfoods.mvvm.database.CartDAO;
import com.example.tastyfoods.mvvm.database.CartDatabase;
import com.example.tastyfoods.mvvm.model.CartDetail;
import com.example.tastyfoods.mvvm.model.Food;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartViewModel extends ViewModel {
    private MutableLiveData<List<CartDetail>> mCart = new MutableLiveData<>();
    private MutableLiveData<Integer> total = new MutableLiveData<>(0);
    private final FirebaseFirestore FIRESTORE = FirebaseFirestore.getInstance();
    public MutableLiveData<List<CartDetail>> getCartDetails(String phoneNumber) {
        FIRESTORE.collection("cartDetail").whereEqualTo("phoneNumber", phoneNumber).addSnapshotListener((value, error) -> {
            try {
                if (value != null) {
                    List<CartDetail> cartDetails = new ArrayList<>();
                    int sum = 0;
                    for (QueryDocumentSnapshot doc : value) {
                        CartDetail cartDetail = doc.toObject(CartDetail.class);
                        cartDetails.add(cartDetail);
                        sum += cartDetail.getMoney();
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

    public void addToCart(Food food){
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("cartDetail")
                .whereEqualTo("foodId", food.getFoodId())
                .limit(1).get().addOnSuccessListener(querySnapshot -> {
                    if (!querySnapshot.isEmpty()) {
                        DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);
                        CartDetail cartDetail = documentSnapshot.toObject(CartDetail.class);
                        assert cartDetail != null;
                        cartDetail.setAmount(cartDetail.getAmount()+1);
                        cartDetail.setMoney(cartDetail.getMoney()+ food.getPrice());
                        Log.d("TAG", "addToCart: " + cartDetail.getMoney() + food.getPrice());
                        firebaseFirestore.collection("cartDetail").document(documentSnapshot.getId())
                                .set(cartDetail);
                    }
                    else {
                        firebaseFirestore.collection("cartDetail")
                                .orderBy("cartDetailId", Query.Direction.DESCENDING)
                                .limit(1)
                                .get()
                                .addOnSuccessListener(querySnapshot1 -> {
                                    long maxCartDetailId = 1;
                                    if (!querySnapshot1.isEmpty()) {
                                        DocumentSnapshot documentSnapshot = querySnapshot1.getDocuments().get(0);
                                        Long id = documentSnapshot.getLong("cartDetailId");
                                        maxCartDetailId = id==null?0:id + 1;
                                    }
                                    CartDetail cartDetail = new CartDetail((int) maxCartDetailId, food.getFoodId(), food.getPrice(),
                                            1, Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getPhoneNumber());
                                    firebaseFirestore.collection("cartDetail").document(String.valueOf(maxCartDetailId))
                                            .set(cartDetail);
                                });
                    }
                });
    }

    public void updateCart(CartDetail cartDetail, int action){
        int foodPrice = cartDetail.getMoney()/cartDetail.getAmount();
        cartDetail.setMoney(cartDetail.getMoney() + action*foodPrice);
        cartDetail.setAmount(cartDetail.getAmount() + action);

        if (cartDetail.getAmount() == 0) {
            this.remove(cartDetail);
            return;
        }
        FIRESTORE.collection("cartDetail")
                .document(String.valueOf(cartDetail.getCartDetailId()))
                .set(cartDetail);
    }

    public void remove(CartDetail cartDetail) {

        FIRESTORE.collection("cartDetail").document(String.valueOf(cartDetail.getCartDetailId())).delete();
    }

    public MutableLiveData<Integer> getTotal() {
        return total;
    }

    public MutableLiveData<List<CartDetail>> getListCart() {
        return mCart;
    }

    public void clearCart() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference usersRef = db.collection("cartDetail");
        usersRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    document.getReference().delete();
                }
            } else {
                Log.d("Delete", "Error getting documents: ", task.getException());
            }
        });
    }
}
