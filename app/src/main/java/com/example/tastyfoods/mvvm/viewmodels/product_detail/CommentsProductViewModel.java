package com.example.tastyfoods.mvvm.viewmodels.product_detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tastyfoods.mvvm.model.Category;
import com.example.tastyfoods.mvvm.model.Feedback;
import com.example.tastyfoods.mvvm.model.Food;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class CommentsProductViewModel extends ViewModel {
    private final MutableLiveData<List<Feedback>> mFeedbacks = new MutableLiveData<>();
    public LiveData<List<Feedback>> getFeedbacks() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("feedback").addSnapshotListener((value, error) -> {
            try {
                if (value != null) {
                    List<Feedback> feedbacks = new ArrayList<>();
                    for (QueryDocumentSnapshot doc : value) {
                        Feedback feedback = doc.toObject(Feedback.class);
                        feedbacks.add(feedback);
                    }
                    mFeedbacks.postValue(feedbacks);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return mFeedbacks;
    }
}

