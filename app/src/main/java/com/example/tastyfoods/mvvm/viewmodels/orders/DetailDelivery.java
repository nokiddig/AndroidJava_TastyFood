package com.example.tastyfoods.mvvm.viewmodels.orders;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.tastyfoods.R;

public class DetailDelivery extends AppCompatActivity {

    TextView txtMoney, txtVouchers, txtTotal, txtNameFood;
    Button btnRecived;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_delivery);
        init();

    }
    private void init()
    {
        txtMoney =(TextView) findViewById(R.id.txtMoney);
        txtNameFood=(TextView) findViewById(R.id.txtnameFood);
        txtTotal =(TextView) findViewById(R.id.txtTotal);
        txtVouchers =(TextView) findViewById(R.id.txtVouchers);
        btnRecived =(Button) findViewById(R.id.btnReceived);
    }
}