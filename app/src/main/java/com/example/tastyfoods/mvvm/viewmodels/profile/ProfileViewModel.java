package com.example.tastyfoods.mvvm.viewmodels.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tastyfoods.mvvm.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProfileViewModel extends ViewModel {
    private final String COLLECTION_NAME = "user", FIELD_NAME = "name",
        FIELD_ADDRESS = "address", FIELD_AVATAR = "image",
        FIELD_MONEY = "money";
    private static ProfileViewModel instance;
    MutableLiveData<User> user = new MutableLiveData<>(new User());
    public LiveData<User> getUser(String phoneNumber) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(COLLECTION_NAME).document(phoneNumber).get()
                .addOnSuccessListener(documentSnapshot -> user.postValue(documentSnapshot.toObject(User.class)));
        return user;
    }

    public void updateUser(User newUser) {
        User updated = user.getValue();
        Map<String, Object> updates = new HashMap<>();
        if (updated != null) {
            updated.setName(newUser.getName());
            updated.setAddress(newUser.getAddress());
            if (newUser.getImage().length() > 20) {
                updated.setImage(newUser.getImage());
            }
        }
        updates.put(FIELD_NAME, newUser.getName());
        assert updated != null;
        updated.setName(newUser.getName());
        updates.put(FIELD_ADDRESS, newUser.getAddress());
        if (newUser.getImage().length() > 20){
            updates.put(FIELD_AVATAR, newUser.getImage());
        }
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(COLLECTION_NAME).document(Objects.requireNonNull(user.getValue()).getPhoneNumber()).update(updates)
                .addOnSuccessListener(unused -> Log.d("user", "updated"));
    }

    public static ProfileViewModel getInstance() {
        if (instance == null) {
            instance = new ProfileViewModel();
            instance.getUser(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getPhoneNumber());
        }
        return instance;
    }

    public MutableLiveData<User> getUser() {
        return user;
    }

    public void updateMoney(int money) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(COLLECTION_NAME).document(Objects.requireNonNull(user.getValue()).getPhoneNumber())
            .update(FIELD_MONEY, user.getValue().getMoney()+money)
            .addOnSuccessListener(unused -> user.postValue(user.getValue()));
    }
}
