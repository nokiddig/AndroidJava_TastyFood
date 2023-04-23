package com.example.tastyfoods.mvvm.view.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tastyfoods.R;
public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Khởi tạo FragmentB và FragmentC
        HomeCategoryFragment categoryFragment = new HomeCategoryFragment();
        HomeHeaderFragment headerFragment = new HomeHeaderFragment();
        HomeProductFragment productFragment = new HomeProductFragment();

        // Thêm FragmentB vào FrameLayout trong FragmentA
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.layoutTop, headerFragment);
        transaction.commit();

        // Thêm FragmentC vào FrameLayout trong FragmentA
        FragmentTransaction transaction2 = getChildFragmentManager().beginTransaction();
        transaction2.add(R.id.layoutCenter, categoryFragment);
        transaction2.commit();

        FragmentTransaction transaction3 = getChildFragmentManager().beginTransaction();
        transaction3.add(R.id.layoutBotom, productFragment);
        transaction3.commit();
        return view;
    }
}