package com.example.tastyfoods;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.tastyfoods.mvvm.viewmodels.Orders.delivery.DeliveryFragment;
import com.example.tastyfoods.mvvm.viewmodels.Orders.deliveryhitory.HictoryFragment;
import com.example.tastyfoods.mvvm.viewmodels.Orders.order.orderfragment;
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
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new DeliveryFragment()).addToBackStack(null).commit();
                        bottomNavigationView.getMenu().findItem(R.id.homefragment).setChecked(true);
                        break;
                    case R.id.cartfragment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new DeliveryFragment()).addToBackStack(null).commit();
                        bottomNavigationView.getMenu().findItem(R.id.cartfragment).setChecked(true);
                        break;
                    case R.id.deliveryfragment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new orderfragment()).addToBackStack(null).commit();
                        bottomNavigationView.getMenu().findItem(R.id.deliveryfragment).setChecked(true);
                        break;
                    case R.id.profilesfragment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new DeliveryFragment()).addToBackStack(null).commit();
                        bottomNavigationView.getMenu().findItem(R.id.profilesfragment).setChecked(true);
                        break;
                }
                return false;
            }
        });

    }
}