package com.example.tastyfoods.mvvm.viewmodels.find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tastyfoods.R;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder>{

    Context context;
    ArrayList<ItemSearch> itemSearchArrayList;



    // tạo 1 contructer cho class


    public SearchAdapter(Context context, ArrayList<ItemSearch> itemSearchArrayList) {
        this.context = context;
        this.itemSearchArrayList = itemSearchArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.list_item_search, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ItemSearch itemSearch = itemSearchArrayList.get(position);
        holder.image_item.setImageResource(itemSearch.image);
        holder.name_item.setText(itemSearch.name);
        holder.describe_item.setText(itemSearch.describe);
        holder.price_item.setText(itemSearch.price);
        holder.icon.setImageResource(itemSearch.button);


    }

    @Override
    public int getItemCount() {
        return itemSearchArrayList.size();
    }

    //  KHAI BÁO ÁNH XẠ
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        // đưa fragmnet_list_search vào
        ImageView image_item;
        TextView name_item;
        TextView describe_item;
        TextView price_item;
        ImageButton icon;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image_item = itemView.findViewById(R.id.image_item);
            name_item = itemView.findViewById(R.id.name_item);
            describe_item = itemView.findViewById(R.id.describe_item);
            price_item = itemView.findViewById(R.id.price_item);
            icon = itemView.findViewById(R.id.icon);
        }
    }
}
