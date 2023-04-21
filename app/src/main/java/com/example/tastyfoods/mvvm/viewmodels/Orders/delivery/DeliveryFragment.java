package com.example.tastyfoods.mvvm.viewmodels.Orders.delivery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tastyfoods.R;


public class DeliveryFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delivery, container, false);
        TextView txtnameFood, txtamount, txtadd, txttotal;
        Button btnReceived;
        txtnameFood = view.findViewById(R.id.txtnameFood);
        txtamount = view.findViewById(R.id.txtAmount);
        txtadd =view.findViewById(R.id.txtAddress);
        txttotal = view.findViewById(R.id.txtTotal);
        btnReceived = view.findViewById(R.id.btnReceived);
        return view ;
    }
}