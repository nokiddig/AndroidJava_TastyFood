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

import com.example.tastyfoods.R;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.MapboxMap;
import com.mapbox.maps.Style;

import com.mapbox.maps.plugin.LocationPuck2D;
import com.mapbox.maps.plugin.locationcomponent.OnIndicatorBearingChangedListener;
import com.mapbox.maps.plugin.locationcomponent.OnIndicatorPositionChangedListener;


public class DeliveryFragment extends Fragment  {

    private View view;

    TextView txtNameFood, txtAmount, txtAdd, txtTotal;
    Button btnReceived;

    MapView mapView ;

    MarkerOptions markerOptions ;

    MapboxMap mapboxMap;
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
        txtNameFood = view.findViewById(R.id.txtnameFood);
        txtAmount = view.findViewById(R.id.txtAmount);
        txtAdd =view.findViewById(R.id.txtAddress);
        txtTotal = view.findViewById(R.id.txtTotal);
        btnReceived = view.findViewById(R.id.btnReceived);
        mapView = view.findViewById(R.id.map);
        if (mapView != null) {
            mapboxMap = mapView.getMapboxMap();
            if (mapboxMap != null) {
                mapboxMap.loadStyleUri(Style.MAPBOX_STREETS);
            }
            LatLng latLng = new LatLng(-23, 345);
            markerOptions = new MarkerOptions().position(latLng).title("Món ăn của bạn đang tới");
        }
        btnReceived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistoryFragment historyFragment = new HistoryFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_delivery, historyFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }
}
