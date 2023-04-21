package com.example.tastyfoods.mvvm.viewmodels.Orders.order;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tastyfoods.R;
import com.example.tastyfoods.mvvm.viewmodels.Orders.delivery.DeliveryFragment;
import com.example.tastyfoods.mvvm.viewmodels.Orders.deliveryhitory.HictoryFragment;

public class orderfragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orderfragment, container, false);
        Button btnHitory =view.findViewById(R.id.btnHitory);
        Button btnDelivery =view.findViewById(R.id.btnDelivery);
        btnHitory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction =getChildFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_main, new HictoryFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        btnDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.fragment_main, new DeliveryFragment());
                fragmentTransaction.commit();
            }
        });
        return view;
    }
}