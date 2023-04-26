package com.example.tastyfoods.mvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tastyfoods.R;
import com.example.tastyfoods.mvvm.model.Food;

import java.util.List;

public class HomeProductAdapter extends RecyclerView.Adapter<HomeProductAdapter.ViewHolder>{

        private List<Food> mFoods;
        private Context mContext;

    public HomeProductAdapter(Context context, List<Food> foods) {
            mContext = context;
            mFoods = foods;
        }

        @NonNull
        @Override
        public HomeProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.home_product_item, parent, false);
            return new HomeProductAdapter.ViewHolder(view);
        }
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Food food = mFoods.get(position);

            holder.textViewName.setText(food.getName());
            holder.textViewPrice.setText(food.getPrice() + "đ");
            // tải hình ảnh
            Glide.with(mContext)
                    .load(food.getImage())
                    .centerCrop()
                    .placeholder(R.drawable.anh)
                    .into(holder.imageViewProduct);
            holder.imageViewProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // show product detail
                }
            });
//            holder.buttonAdd.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    // add to cart
//                }
//            });
        }

        @Override
        public int getItemCount() {
            return mFoods.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {

            ImageView imageViewProduct;
            TextView textViewName, textViewPrice;
            Button buttonAdd;

            public ViewHolder(View itemView) {
                super(itemView);
                imageViewProduct = itemView.findViewById(R.id.productImage);
                textViewName = itemView.findViewById(R.id.textViewNameProduct);
                textViewPrice = itemView.findViewById(R.id.textviewPrice);
                //buttonAdd = itemView.findViewById(R.id.imageButton);
            }
        }

    public List<Food> getMFoods() {
        return mFoods;
    }

    public void setMFoods(List<Food> mFoods) {
        this.mFoods = mFoods;
    }
}