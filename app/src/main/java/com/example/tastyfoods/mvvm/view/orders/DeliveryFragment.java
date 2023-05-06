package com.example.tastyfoods.mvvm.view.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.tastyfoods.R;
import com.example.tastyfoods.mvvm.model.Bill;
import com.example.tastyfoods.mvvm.viewmodels.orders.DeliveryViewModel;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.MapboxMap;
import com.mapbox.maps.Style;

import com.mapbox.maps.plugin.locationcomponent.OnIndicatorBearingChangedListener;
import com.mapbox.maps.plugin.locationcomponent.OnIndicatorPositionChangedListener;

public class DeliveryFragment extends Fragment  {

    private View view;
    private DeliveryViewModel deliveryViewModel;
    private TextView txtAddress, txtTotal, txtDatetime;
    private Button btnReceived;

    private MapView mapView ;

    private MarkerOptions markerOptions ;

    private MapboxMap mapboxMap;
    public DeliveryFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private OnIndicatorBearingChangedListener onIndicatorBearingChangedListener = new OnIndicatorBearingChangedListener() {
        @Override
        public void onIndicatorBearingChanged(double bearing) {
            CameraOptions cameraOptions = new CameraOptions.Builder().bearing(bearing).build();
            mapView.getMapboxMap().setCamera(cameraOptions);
        }
    };
    private OnIndicatorPositionChangedListener onIndicatorPositionChangedListener = new OnIndicatorPositionChangedListener() {
        @Override
        public void onIndicatorPositionChanged(@NonNull Point point) {

        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_delivery, container, false);
        init();
        createMap();
        deliveryViewModel = new ViewModelProvider(this).get(DeliveryViewModel.class);
       deliveryViewModel.getBillComing().observe(getViewLifecycleOwner(), bill -> {
           txtAddress.setText(bill.getAddress());
           txtTotal.setText(String.valueOf(bill.getTotalMoney()));
           txtDatetime.setText(bill.getDateTime().toString());
       });


        btnReceived.setOnClickListener(v -> {
            Bill bill = deliveryViewModel.getBillComing().getValue();
            if (bill==null){
                return;
            }
            bill.setStatus(true);
            deliveryViewModel.updateBill(bill);
            onStop();
            HistoryFragment historyFragment = new HistoryFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_main, historyFragment);
            transaction.commit();
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onDestroy();
    }

    private void init()
    {
        txtAddress =view.findViewById(R.id.txtAddress);
        txtTotal = view.findViewById(R.id.txtTotal);
        txtDatetime = view.findViewById(R.id.txt_DateTime);
        btnReceived = view.findViewById(R.id.btnReceived);
        mapView = view.findViewById(R.id.map);
    }
    private void createMap()
    {
        if (mapView != null) {
            mapboxMap = mapView.getMapboxMap();
            if (mapboxMap != null) {
                mapboxMap.loadStyleUri(Style.MAPBOX_STREETS);
            }
            LatLng latLng = new LatLng(-23, 345);
            markerOptions = new MarkerOptions().position(latLng).title("Món ăn của bạn đang tới");
        }
    }
}
