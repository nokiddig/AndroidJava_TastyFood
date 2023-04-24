package com.example.tastyfoods.mvvm.viewmodels.orders;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.BasemapStyle;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.example.tastyfoods.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class Delivery extends Fragment {
    private OrderFragment order;
    private View view;
    private  OrderFragment orderFragment;
    private FirebaseFirestore db;
    private ArcGISMap map ;
    String basemapUrl = "https://services.arcgisonline.com/ArcGIS/rest/services/World_Topo_Map/MapServer";
    Basemap basemap = Basemap.createImagery();
    public Delivery() {
    }

    @Override
    public void onAttach( Context context) {
        super.onAttach(context);

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
        MapView mapView;
        txtnameFood = view.findViewById(R.id.txtnameFood);
        txtamount = view.findViewById(R.id.txtAmount);
        txtadd =view.findViewById(R.id.txtAddress);
        txttotal = view.findViewById(R.id.txtTotal);
        btnReceived = view.findViewById(R.id.btnReceived);
        mapView =view.findViewById(R.id.mapView);
        ArcGISMap map = new ArcGISMap(Basemap.Type.TOPOGRAPHIC, 34.056295, -117.195800, 10);

        mapView.setMap(map);
        btnReceived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
