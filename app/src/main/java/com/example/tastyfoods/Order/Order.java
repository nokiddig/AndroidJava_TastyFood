package com.example.tastyfoods.Order;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.tastyfoods.R;
import com.example.tastyfoods.mvvm.viewmodels.Orders.delivery.DeliveryFragment;
import com.example.tastyfoods.mvvm.viewmodels.Orders.deliveryhitory.HictoryFragment;

public class Order extends AppCompatActivity {

    Button btnHitory, btnDelivery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        init();
        btnHitory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.fragment_main, new HictoryFragment());
                fragmentTransaction.commit();
            }
        });
        btnDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.fragment_main, new DeliveryFragment());
                fragmentTransaction.commit();
            }
        });
    }
    private void init()
    {
        btnHitory =(Button) findViewById(R.id.btnHitory);
        btnDelivery =(Button) findViewById(R.id.btnDelivery);
    }
}