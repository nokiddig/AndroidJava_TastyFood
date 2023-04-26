package com.example.tastyfoods.mvvm.view.orders;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;

import com.example.tastyfoods.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.mapbox.maps.MapView;
import com.mapbox.maps.MapboxMap;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.Plugin;

public class Delivery extends Fragment  {
    private OrderFragment order;
    private View view;
    private  OrderFragment orderFragment;
    private MapView mapView;
    private MapboxMap map;
    public Delivery() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bill_list, container, false);

        TextView txtnameFood, txtamount, txtadd, txttotal;
        Button btnReceived;
        txtnameFood = view.findViewById(R.id.txtnameFood);
        txtamount = view.findViewById(R.id.txtAmount);
        txtadd =view.findViewById(R.id.txtAddress);
        txttotal = view.findViewById(R.id.txtTotal);
        btnReceived = view.findViewById(R.id.btnReceived);
        mapView = view.findViewById(R.id.map);

        return view;
    }
}
