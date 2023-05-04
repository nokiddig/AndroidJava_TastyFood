package com.example.tastyfoods.mvvm.view.home;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tastyfoods.R;
import com.example.tastyfoods.mvvm.adapter.HomeSliderAdapter;
import com.example.tastyfoods.mvvm.model.HomeSliderItem;
import com.example.tastyfoods.mvvm.view.search.SearchFragment;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeHeaderFragment extends Fragment {

    private ViewPager2 viewPager2;
    private SearchView searchView;
    TextView textViewHello;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_header, container, false);
        this.init(view);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("search", query);
                Bundle bundle = new Bundle();
                bundle.putSerializable("search", query);
                SearchFragment searchFragment = new SearchFragment();
                searchFragment.setArguments(bundle);

                // replace this Fragment to ProductDetailFragment
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
        return view;
    }

    private void init(View view) {
        searchView = view.findViewById(R.id.searchView);
        viewPager2 = view.findViewById(R.id.viewPager);
        List<HomeSliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new HomeSliderItem(R.drawable.banner1));
        sliderItems.add(new HomeSliderItem(R.drawable.banner2));
        sliderItems.add(new HomeSliderItem(R.drawable.banner3));
        sliderItems.add(new HomeSliderItem(R.drawable.banner4));
        viewPager2.setAdapter(new HomeSliderAdapter(sliderItems, viewPager2));
        textViewHello = view.findViewById(R.id.textViewXinChao);
    }
}