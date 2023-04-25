package com.example.tastyfoods.mvvm.viewmodels.Orders.deliveryhitory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tastyfoods.R;

import java.util.List;

public class billAdapter extends  RecyclerView.Adapter<billAdapter.billViewAdapter>{

    @NonNull
    @Override
    public billViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_bill, parent, false);
        return new billViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull billViewAdapter holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class billViewAdapter extends RecyclerView.ViewHolder{
        private TextView  txtTotal,  txtDateTime;
        private Button btnReset;
        public billViewAdapter(@NonNull View itemView) {
            super(itemView);
            txtTotal =itemView.findViewById(R.id.txt_total);
            txtDateTime=itemView.findViewById(R.id.txt_datetime);
            btnReset =itemView.findViewById(R.id.btnReset);
        }
    }
}
