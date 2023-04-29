package com.example.tastyfoods.mvvm.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tastyfoods.R;

import java.util.List;

public class DeliveyAdapter extends RecyclerView.Adapter<DeliveyAdapter.deliveryViewAdapter> {


    @NonNull
    @Override
    public deliveryViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull deliveryViewAdapter holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class  deliveryViewAdapter extends RecyclerView.ViewHolder {
        private TextView nameFood, address, amount, total;
        private Button btnRecived;
        public deliveryViewAdapter(@NonNull View itemView) {
            super(itemView);
            nameFood = itemView.findViewById(R.id.txtnameFood);
            address =itemView.findViewById(R.id.txtAddress);
            amount = itemView.findViewById(R.id.txtAmount);
            total = itemView.findViewById(R.id.txtTotal);
            btnRecived =itemView.findViewById(R.id.btnReceived);
        }
    }

}
