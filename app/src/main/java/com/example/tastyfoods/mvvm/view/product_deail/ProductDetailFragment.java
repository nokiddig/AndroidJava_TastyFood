package com.example.tastyfoods.mvvm.view.product_deail;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tastyfoods.R;
import com.example.tastyfoods.mvvm.adapter.CategoryAdapter;
import com.example.tastyfoods.mvvm.adapter.CommentsProductAdapter;
import com.example.tastyfoods.mvvm.adapter.HomeProductAdapter;
import com.example.tastyfoods.mvvm.model.Feedback;
import com.example.tastyfoods.mvvm.model.Food;
import com.example.tastyfoods.mvvm.viewmodels.home.HomeProductViewModel;
import com.example.tastyfoods.mvvm.viewmodels.product_detail.CommentsProductViewModel;
import com.example.tastyfoods.mvvm.viewmodels.product_detail.ProductDetailViewModel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ProductDetailFragment extends Fragment {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CommentsProductAdapter commentsProductAdapter;
    private CommentsProductViewModel commentsProductViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        RecyclerView comments;
        ImageView imgFood;
        TextView foodName, monNey, desCrible, ratePoint, sell;
        foodName = view.findViewById(R.id.text_name);
        monNey = view.findViewById(R.id.text_money);
        ratePoint = view.findViewById(R.id.text_ratePoint);
        desCrible = view.findViewById(R.id.text_describe);
        sell = view.findViewById(R.id.text_sell);
        comments = view.findViewById(R.id.recycler_view_comments);
        imgFood = view.findViewById(R.id.imageFood);
        ProductDetailViewModel viewModel = new ViewModelProvider(this).get(ProductDetailViewModel.class);
        viewModel.getDataName().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                foodName.setText(s);
            }
        });
        viewModel.getDataDescription().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                desCrible.setText(s);
            }
        });
        viewModel.getDataPrice().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                monNey.setText(String.valueOf(s));
            }
        });
        viewModel.getDataPrice().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                monNey.setText(String.valueOf(s));
            }
        });
        viewModel.getDataSell().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                sell.setText(String.valueOf(s));
            }
        });
        viewModel.getDataRatePoint().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                ratePoint.setText(String.valueOf(s));
            }
        });
        viewModel.getDataImg().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Glide.with(imgFood.getContext())
                        .load(s)
                        .into(imgFood);
            }
        });
        //Hien List Comments
        comments.setHasFixedSize(true);
        commentsProductViewModel = new ViewModelProvider(this).get(CommentsProductViewModel.class);
        commentsProductViewModel.getFeedbacks().observe(getViewLifecycleOwner(), new Observer<List<Feedback>>() {
            @Override
            public void onChanged(List<Feedback> feedbacks) {
                commentsProductAdapter = new CommentsProductAdapter(getContext(), feedbacks);
                comments.setAdapter(commentsProductAdapter);
            }
        });
        return view;
    }
}

