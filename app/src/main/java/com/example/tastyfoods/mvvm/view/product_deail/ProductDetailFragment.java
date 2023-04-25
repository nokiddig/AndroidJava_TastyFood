package com.example.tastyfoods.mvvm.view.product_deail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tastyfoods.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProductDetailFragment extends Fragment {
    private  FirebaseFirestore db = FirebaseFirestore.getInstance();
    private TextView foodName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        foodName = view.findViewById(R.id.text_name);
        getName();
        return view;
    }
    public void getName(){
        // Lấy collection "users"
        CollectionReference usersRef = db.collection("food");

    // Lấy document có id = "ABC123" trong collection "users"
        DocumentReference docRef = usersRef.document("banhmi");

    // Lấy dữ liệu của document
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    // Lấy dữ liệu từ DocumentSnapshot
                    String name = documentSnapshot.getString("name");
                    foodName.setText(name);
                }
            }
        });
    }
}