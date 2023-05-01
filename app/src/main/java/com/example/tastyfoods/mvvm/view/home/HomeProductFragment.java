package com.example.tastyfoods.mvvm.view.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tastyfoods.R;
import com.example.tastyfoods.mvvm.adapter.CategoryAdapter;
import com.example.tastyfoods.mvvm.adapter.HomeProductAdapter;
import com.example.tastyfoods.mvvm.model.Category;
import com.example.tastyfoods.mvvm.model.Food;
import com.example.tastyfoods.mvvm.viewmodels.home.CategoryViewModel;
import com.example.tastyfoods.mvvm.viewmodels.home.HomeProductViewModel;

import java.util.List;

public class HomeProductFragment extends Fragment {
    private RecyclerView recyclerView;
    private HomeProductAdapter homeProductAdapter;
    private HomeProductViewModel homeProductViewModel;
    private static HomeProductFragment instance;

    public static HomeProductFragment getInstance() {
        if (instance == null){
            instance = new HomeProductFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_product, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_product);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);

        homeProductViewModel = new ViewModelProvider(this).get(HomeProductViewModel.class);
        homeProductViewModel.getFoods().observe(getViewLifecycleOwner(), new Observer<List<Food>>() {
            @Override
            public void onChanged(List<Food> foods) {
                homeProductAdapter = new HomeProductAdapter(getContext(), foods);
                recyclerView.setAdapter(homeProductAdapter);
            }
        });

        return view;
    }
}