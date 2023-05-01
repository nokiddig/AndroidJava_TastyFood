package com.example.tastyfoods;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.tastyfoods.mvvm.view.home.HomeFragment;
import com.example.tastyfoods.mvvm.view.orders.Delivery;
import com.example.tastyfoods.mvvm.view.orders.OrderFragment;
import com.example.tastyfoods.mvvm.view.product_deail.ProductDetailFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=(BottomNavigationView) findViewById(R.id.bottomnav);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homefragment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment()).addToBackStack(null).commit();
                        bottomNavigationView.getMenu().findItem(R.id.homefragment).setChecked(true);
                        break;
                    case R.id.cartfragment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new Delivery()).addToBackStack(null).commit();
                        break;
                    case R.id.deliveryfragment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new OrderFragment()).addToBackStack(null).commit();
                        bottomNavigationView.getMenu().findItem(R.id.deliveryfragment).setChecked(true);
                        break;
                    case R.id.profilesfragment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new ProductDetailFragment()).addToBackStack(null).commit();
                        bottomNavigationView.getMenu().findItem(R.id.profilesfragment).setChecked(true);
                        break;
                }
                return false;
            }
        });

    }
}