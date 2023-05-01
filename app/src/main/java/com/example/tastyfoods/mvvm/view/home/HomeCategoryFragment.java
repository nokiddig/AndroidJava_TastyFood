package com.example.tastyfoods.mvvm.view.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tastyfoods.R;
import com.example.tastyfoods.mvvm.adapter.CategoryAdapter;
import com.example.tastyfoods.mvvm.viewmodels.home.CategoryViewModel;

public class HomeCategoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_category, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);

        CategoryViewModel categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        categoryViewModel.getCategories().observe(getViewLifecycleOwner(), categories -> {
            categoryAdapter = new CategoryAdapter(getContext(), categories);
            recyclerView.setAdapter(categoryAdapter);
        });

        return view;
    }
}
