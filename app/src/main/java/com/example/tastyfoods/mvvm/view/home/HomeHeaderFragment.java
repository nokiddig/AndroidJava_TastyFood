package com.example.tastyfoods.mvvm.view.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tastyfoods.R;
import com.example.tastyfoods.mvvm.adapter.HomeSliderAdapter;
import com.example.tastyfoods.mvvm.model.HomeSliderItem;

import java.util.ArrayList;
import java.util.List;

public class HomeHeaderFragment extends Fragment {

    ViewPager2 viewPager2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_header, container, false);
        viewPager2 = view.findViewById(R.id.viewPager);
        List<HomeSliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new HomeSliderItem(R.drawable.banner1));
        sliderItems.add(new HomeSliderItem(R.drawable.banner2));
        sliderItems.add(new HomeSliderItem(R.drawable.banner3));
        sliderItems.add(new HomeSliderItem(R.drawable.banner4));
        viewPager2.setAdapter(new HomeSliderAdapter(sliderItems, viewPager2));
        return view;
    }
}