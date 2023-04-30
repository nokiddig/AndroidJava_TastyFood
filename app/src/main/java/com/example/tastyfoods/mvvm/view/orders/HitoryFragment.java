package com.example.tastyfoods.mvvm.view.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tastyfoods.MainActivity;
import com.example.tastyfoods.R;
import com.example.tastyfoods.mvvm.adapter.BillAdapter;
import com.example.tastyfoods.mvvm.model.Bill;
import com.example.tastyfoods.mvvm.viewmodels.orders.BillViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A fragment representing a list of Items.
 */
public class HitoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private BillAdapter billAdapter;
    private BillViewModel billViewModel;
    private  View view;

    public HitoryFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bill_list, container, false);
        recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
// adapter
        billViewModel = new ViewModelProvider(this).get(BillViewModel.class);
        billViewModel.getBills().observe(getViewLifecycleOwner(), new Observer<List<Bill>>() {
            @Override
            public void onChanged(List<Bill> bills) {
                billAdapter = new BillAdapter(bills, getContext());
                recyclerView.setAdapter(billAdapter);
            }
        });

        return view;
        //
    }


    //fix du lieu
}