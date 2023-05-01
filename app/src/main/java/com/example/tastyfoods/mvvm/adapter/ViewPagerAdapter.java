package com.example.tastyfoods.mvvm.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.tastyfoods.mvvm.tab1Fragment;
import com.example.tastyfoods.mvvm.tab3Fragment;
import com.example.tastyfoods.mvvm.tab4Fragment;
import com.example.tastyfoods.mvvm.view.CartFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new tab1Fragment();
            case 1:
                return new CartFragment();
            case 2:
                return new tab3Fragment();
            case 3:
                return new tab4Fragment();
            default:
                return new tab1Fragment();
        }

    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
