package com.example.tastyfoods.mvvm.viewmodels.Orders.deliveryhitory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tastyfoods.R;
import com.example.tastyfoods.data.model.Bill;
import com.example.tastyfoods.viewmodel.order.Order.Order;

import java.util.ArrayList;
import java.util.List;


/**
 * A fragment representing a list of Items.
 */
public class HictoryFragment extends Fragment {

    private Order order;
    private RecyclerView list;

    private  View view;
    public HictoryFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_bill_list, container, false);
         order =(Order)getActivity();
         list = view.findViewById(R.id.list);
         LinearLayoutManager linearLayoutManager = new LinearLayoutManager(order);
        list.setLayoutManager(linearLayoutManager);
// adapter
         billAdapter bill = new billAdapter(getListBill());
         list.setAdapter(bill);

         RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(order, DividerItemDecoration.VERTICAL);
         list.addItemDecoration(itemDecoration);
        return view;
    }
    //fix du lieu
    private List<Bill> getListBill() {
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill(null, null, "Ngày giao : 10/10/2022", 50000));
        bills.add(new Bill(null, null, "Ngày giao : 10/10/2022", 60000));
        return bills;
    }
}