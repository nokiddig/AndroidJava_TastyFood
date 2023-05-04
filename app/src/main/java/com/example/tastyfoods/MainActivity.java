package com.example.tastyfoods;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.tastyfoods.mvvm.view.CartFragment;
import com.example.tastyfoods.mvvm.view.home.HomeFragment;
import com.example.tastyfoods.mvvm.view.orders.DeliveryFragment;
import com.example.tastyfoods.mvvm.view.orders.OrderFragment;
import com.example.tastyfoods.mvvm.view.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomnav);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment()).addToBackStack("start").commit();
        bottomNavigationView.getMenu().findItem(R.id.homefragment).setChecked(true);
        String phoneNumber = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homefragment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment()).commit();
                        bottomNavigationView.getMenu().findItem(R.id.homefragment).setChecked(true);
                        break;
                    case R.id.cartfragment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,CartFragment.newInstance(phoneNumber)).addToBackStack("cart").commit();
                        break;
                    case R.id.deliveryfragment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new OrderFragment()).addToBackStack("delivery").commit();
                        bottomNavigationView.getMenu().findItem(R.id.deliveryfragment).setChecked(true);
                        break;
                    case R.id.profilesfragment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, ProfileFragment.newInstance(phoneNumber)).addToBackStack("profile").commit();
                        bottomNavigationView.getMenu().findItem(R.id.profilesfragment).setChecked(true);
                        break;
                }
                return false;
            }
        });

    }
}