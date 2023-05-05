package com.example.tastyfoods.mvvm.viewmodels.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tastyfoods.mvvm.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProfileViewModel extends ViewModel {
    private static ProfileViewModel instance;
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
        if (newUser.getName() != null) {
            updates.put("name", newUser.getName());
        }
        if (newUser.getAddress() != null) {
            updates.put("address", newUser.getAddress());
        }
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("user").document(Objects.requireNonNull(user.getValue()).getPhoneNumber()).update(updates)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        user.postValue(newUser);
                    }
                });
    }

    public static ProfileViewModel getInstance() {
        if (instance == null) {
            instance = new ProfileViewModel();
            instance.getUser(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber());
        }
        return instance;
    }

    public MutableLiveData<User> getUser() {
        return user;
    }

    public void updateMoney(int money) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("user").document(Objects.requireNonNull(user.getValue()).getPhoneNumber())
            .update("money", user.getValue().getMoney()+money)
            .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                user.postValue(user.getValue());
            }
        });
    }
}
