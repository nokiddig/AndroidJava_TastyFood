package com.example.tastyfoods.mvvm.view.search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.tastyfoods.R;
import com.example.tastyfoods.mvvm.view.search.ListSearchFragment;


public class SearchFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameLayoutListSearch, new ListSearchFragment()).commit();
        ImageButton imageButtonBack = view.findViewById(R.id.imageButtonBack);
        imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.popBackStack("search", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        return view;
    }
}