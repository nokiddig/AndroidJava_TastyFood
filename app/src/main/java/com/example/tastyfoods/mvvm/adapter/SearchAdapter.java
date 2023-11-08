package com.example.tastyfoods.mvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.tastyfoods.R;
import com.example.tastyfoods.mvvm.model.Food;
import com.example.tastyfoods.mvvm.viewmodels.cartdetail.CartViewModel;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder>{

    Context context;
    ArrayList<Food> itemSearchArrayList;


    public SearchAdapter(Context context, ArrayList<Food> itemSearchArrayList) {
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
        Food itemSearch = itemSearchArrayList.get(position);
        holder.textViewName.setText(String.valueOf(itemSearch.getName()));
        holder.textViewDescribe.setText(itemSearch.getDescription().substring(0, Math.min(itemSearch.getDescription().length(), 100)).concat("..."));
        holder.textViewPrice.setText(String.valueOf(itemSearch.getPrice()));
        holder.imageButton.setImageResource(R.drawable.baseline_control_point_24);
        Glide.with(context)
                .load(itemSearch.getImage())
                .centerCrop()
                .placeholder(R.drawable.anh)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(holder.imageView);
        holder.imageButton.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), "Thêm sản phẩm thành công!", Toast.LENGTH_SHORT).show();
            // add to cart
            new CartViewModel().addToCart(itemSearch);
        });
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
