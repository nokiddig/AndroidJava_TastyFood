package com.example.tastyfoods.mvvm.view.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tastyfoods.R;
import com.mapbox.maps.MapView;
import com.mapbox.maps.MapboxMap;
import com.mapbox.maps.Style;

public class Delivery extends Fragment  {
    private View view;

    TextView txtnameFood, txtamount, txtadd, txttotal;
    Button btnReceived;
    public Delivery() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_delivery, container, false);

        MapView mapView ;

        txtnameFood = view.findViewById(R.id.txtnameFood);
        txtamount = view.findViewById(R.id.txtAmount);
        txtadd =view.findViewById(R.id.txtAddress);
        txttotal = view.findViewById(R.id.txtTotal);
        btnReceived = view.findViewById(R.id.btnReceived);
//        mapView = view.findViewById(R.id.map);
//        if (mapView != null && mapView.getMapboxMap() != null) {
//            mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS);
//        }
        btnReceived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HitoryFragment hitoryFragment = new HitoryFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_delivery, hitoryFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }

}
