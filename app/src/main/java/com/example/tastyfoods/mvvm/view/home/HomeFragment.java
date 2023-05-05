package com.example.tastyfoods.mvvm.view.home;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tastyfoods.R;
import com.example.tastyfoods.mvvm.adapter.CategoryAdapter;
import com.example.tastyfoods.mvvm.adapter.HomeProductAdapter;
import com.example.tastyfoods.mvvm.adapter.HomeSliderAdapter;
import com.example.tastyfoods.mvvm.model.Food;
import com.example.tastyfoods.mvvm.model.HomeSliderItem;
import com.example.tastyfoods.mvvm.view.search.SearchFragment;
import com.example.tastyfoods.mvvm.viewmodels.home.CategoryViewModel;
import com.example.tastyfoods.mvvm.viewmodels.home.ProductViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    public static final int ALL_PRODUCT = 0;
    private RecyclerView recyclerViewProduct;
    private ProductViewModel productViewModel;
    private static HomeFragment instance;

    public static HomeFragment getInstance() {
        if (instance == null) {
            instance = new HomeFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        this.init(view);
        return view;
    }

    private void init(View view) {
        ConstraintLayout layoutHeader = view.findViewById(R.id.layoutTop);
        this.setUpLayoutHeader(view);
        ConstraintLayout layoutCategory = view.findViewById(R.id.layoutCenter);
        this.setUpLayoutCategory(view);
        ConstraintLayout layoutProduct = view.findViewById(R.id.layoutBottom);
        this.setUpLayoutProduct(view, ALL_PRODUCT);
    }

    private void setUpLayoutProduct(View view, int categoryId) {
        recyclerViewProduct = view.findViewById(R.id.recycler_view_product);
        recyclerViewProduct.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewProduct.setHasFixedSize(true);

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productViewModel.getFoods(categoryId).observe(getViewLifecycleOwner(), new Observer<List<Food>>() {
            @Override
            public void onChanged(List<Food> foods) {
                HomeProductAdapter homeProductAdapter = new HomeProductAdapter(getContext(), foods);
                recyclerViewProduct.setAdapter(homeProductAdapter);
            }
        });
    }

    private void setUpLayoutCategory(View view) {
        RecyclerView recyclerView;
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);

        CategoryViewModel categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        categoryViewModel.getCategories().observe(getViewLifecycleOwner(), categories -> {
            CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(), categories);
            recyclerView.setAdapter(categoryAdapter);
        });
    }

    private void setUpLayoutHeader(View view) {
        ViewPager2 viewPager2;
        SearchView searchView;
        TextView textViewHello;
        searchView = view.findViewById(R.id.searchView);
        viewPager2 = view.findViewById(R.id.viewPager);
        List<HomeSliderItem> sliderItems = new ArrayList<>();
        int[] bannerId = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3, R.drawable.banner4};
        for (int i = 0; i < bannerId.length; i++){
            sliderItems.add(new HomeSliderItem(bannerId[i]));
        }
        viewPager2.setAdapter(new HomeSliderAdapter(sliderItems, viewPager2));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("search", query);
                SearchFragment searchFragment = new SearchFragment();
                searchFragment.setArguments(bundle);

                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, searchFragment)
                        .addToBackStack("search")
                        .commit();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}