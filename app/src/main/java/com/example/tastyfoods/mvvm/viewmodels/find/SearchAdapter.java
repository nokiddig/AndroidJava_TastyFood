package com.example.tastyfoods.mvvm.viewmodels.find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tastyfoods.R;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder>{

    Context context;
    ArrayList<ItemSearch> itemSearchArrayList;


    public SearchAdapter(Context context, ArrayList<ItemSearch> itemSearchArrayList) {
        this.context = context;
        this.itemSearchArrayList = itemSearchArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_search, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ItemSearch itemSearch = itemSearchArrayList.get(position);
        holder.imageView.setImageResource(itemSearch.image);
        holder.textViewName.setText(itemSearch.name);
        holder.textViewDescribe.setText(itemSearch.describe);
        holder.textViewPrice.setText(itemSearch.price);
        holder.imageButton.setImageResource(itemSearch.button);
    }

    @Override
    public int getItemCount() {
        return itemSearchArrayList.size();
    }

    //  KHAI BÁO ÁNH XẠ
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        // đưa fragment_list_search vào
        ImageView imageView;
        TextView textViewName;
        TextView textViewDescribe;
        TextView textViewPrice;
        ImageButton imageButton;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_item);
            textViewName = itemView.findViewById(R.id.name_item);
            textViewDescribe = itemView.findViewById(R.id.describe_item);
            textViewPrice = itemView.findViewById(R.id.price_item);
            imageButton = itemView.findViewById(R.id.icon);
        }
    }
}
