package com.example.tastyfoods.mvvm.viewmodels.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tastyfoods.mvvm.model.Category;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class CategoryViewModel extends ViewModel {

    private MutableLiveData<List<Category>> mCategories = new MutableLiveData<>();

    public LiveData<List<Category>> getCategories() {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("category").addSnapshotListener((value, error) -> {
            try {
                if (value != null) {
                    List<Category> categories = new ArrayList<>();
                    for (QueryDocumentSnapshot doc : value) {
                        Category category = doc.toObject(Category.class);
                        categories.add(category);
                    }
                    mCategories.postValue(categories);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return mCategories;
    }
}