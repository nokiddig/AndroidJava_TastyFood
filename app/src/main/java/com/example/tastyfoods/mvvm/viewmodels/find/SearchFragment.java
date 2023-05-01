package com.example.tastyfoods.mvvm.viewmodels.find;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tastyfoods.R;


public class SearchFragment extends Fragment {

// trả về 1 view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Thổi phồng bố cục cho đoạn này
        return inflater.inflate(R.layout.fragment_search, container, false);
    }
}