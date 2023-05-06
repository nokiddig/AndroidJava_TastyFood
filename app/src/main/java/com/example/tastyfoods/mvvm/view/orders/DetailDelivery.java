package com.example.tastyfoods.mvvm.view.orders;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tastyfoods.R;

public class DetailDelivery extends AppCompatActivity {

    TextView txtMoney, txtVouchers, txtTotal, txtNameFood;
    Button btnReceived;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_delivery);
        init();

    }
    private void init()
    {
//        txtMoney = findViewById(R.id.txtMoney);
//        txtNameFood = findViewById(R.id.txtnameFood);
//        txtTotal = findViewById(R.id.txtTotal);
//        txtVouchers = findViewById(R.id.txtVouchers);
//        btnReceived = findViewById(R.id.btnReceived);
    }
}