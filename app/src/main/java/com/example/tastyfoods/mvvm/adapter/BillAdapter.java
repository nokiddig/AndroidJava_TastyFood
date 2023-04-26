package com.example.tastyfoods.mvvm.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tastyfoods.R;
import com.example.tastyfoods.mvvm.model.Bill;
import com.example.tastyfoods.mvvm.view.orders.OnItemClickListener;

import java.util.List;


public class BillAdapter extends  RecyclerView.Adapter<BillAdapter.billViewAdapter>{
   private List<Bill> listBills;
   private OnItemClickListener listener;
   private Context mContext;

    public List<Bill> getListBills() {
        return listBills;
    }

    public void setListBills(List<Bill> listBills) {
        this.listBills = listBills;
    }

    public BillAdapter(List<Bill> listBills, Context mContext) {
        this.listBills = listBills;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public billViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_bill, parent, false);
        return new billViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull billViewAdapter holder, int position) {
        Bill bill = listBills.get(position);
        if (bill == null)
        {
            return;
        }
        holder.txtDateTime.setText(bill.getDateTime().toString());
        holder.txtTotal.setText(String.valueOf(bill.getTotalMoney()));
        holder.bind(position,listener);
    }

    @Override
    public int getItemCount() {
        if(listBills != null)
        {
            return listBills.size();
        }
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
        public void bind (final int postion, final OnItemClickListener listener)
        {
            btnReset.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v)
                {
                    listener.onItemClick(postion);
                }
            });
        }
    }
}
