package com.example.tastyfoods.mvvm.viewmodels.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tastyfoods.mvvm.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProfileViewModel extends ViewModel {
    MutableLiveData<User> user = new MutableLiveData<>(new User());
    public LiveData<User> getUser(String phoneNumber) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("user").document(phoneNumber).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        user.postValue(documentSnapshot.toObject(User.class));
                    }
                });
        return user;
    }

    public void updateUser(User newUser) {
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", newUser.getName());
        updates.put("address", newUser.getAddress());
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("user").document(Objects.requireNonNull(user.getValue()).getPhoneNumber()).update(updates)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        user.postValue(newUser);
                    }
                });
    }
}
