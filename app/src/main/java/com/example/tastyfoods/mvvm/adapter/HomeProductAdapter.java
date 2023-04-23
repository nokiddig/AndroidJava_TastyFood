package com.example.tastyfoods.mvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tastyfoods.R;
import com.example.tastyfoods.mvvm.model.Food;
import com.google.firebase.storage.FirebaseStorage;

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
            View view = LayoutInflater.from(mContext).inflate(R.layout.home_item_product, parent, false);
            return new HomeProductAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Food food = mFoods.get(position);

            holder.tvName.setText(food.getName());
            holder.tvPrice.setText(food.getPrice() + "đ");
            // tải hình ảnh
//            FirebaseStorage storage = FirebaseStorage.getInstance();
            Glide.with(mContext)
                    .load(food.getImage())
                    .centerCrop()
                    .placeholder(R.drawable.anh)
                    .into(holder.ivImage);
        }

        @Override
        public int getItemCount() {
            return mFoods.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {

            ImageView ivImage;
            TextView tvName, tvPrice;

            public ViewHolder(View itemView) {
                super(itemView);

                ivImage = itemView.findViewById(R.id.productImage);
                tvName = itemView.findViewById(R.id.textViewNameProduct);
                tvPrice = itemView.findViewById(R.id.textviewPrice);
            }
        }

    public List<Food> getMFoods() {
        return mFoods;
    }

    public void setMFoods(List<Food> mFoods) {
        this.mFoods = mFoods;
    }
}
