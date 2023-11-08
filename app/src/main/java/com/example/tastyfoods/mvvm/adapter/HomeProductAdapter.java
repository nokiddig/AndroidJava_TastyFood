package com.example.tastyfoods.mvvm.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tastyfoods.R;
import com.example.tastyfoods.mvvm.model.Food;
import com.example.tastyfoods.mvvm.view.productdetail.ProductDetailFragment;
import com.example.tastyfoods.mvvm.viewmodels.cartdetail.CartViewModel;

import androidx.fragment.app.FragmentManager;

import java.util.List;

public class HomeProductAdapter extends RecyclerView.Adapter<HomeProductAdapter.ViewHolder>{

        private List<Food> mFoods;
        private final Context CONTEXT;

    public HomeProductAdapter(Context context, List<Food> foods) {
            CONTEXT = context;
            mFoods = foods;
        }

        @NonNull
        @Override
        public HomeProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(CONTEXT).inflate(R.layout.item_home_product, parent, false);
            return new HomeProductAdapter.ViewHolder(view);
        }
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Food food = mFoods.get(position);

            holder.textViewName.setText(food.getName());
            holder.textViewPrice.setText(String.valueOf(food.getPrice()).concat("đ"));

            Glide.with(CONTEXT)
                    .load(food.getImage())
                    .centerCrop()
                    .placeholder(R.drawable.anh)
                    .into(holder.imageViewProduct);
            holder.imageViewProduct.setOnClickListener(view -> {
                // show product detail
                // send data to Fragment ProductDetailFragment
                Bundle bundle = new Bundle();
                bundle.putSerializable("food", food);
                ProductDetailFragment productDetailFragment = new ProductDetailFragment();
                productDetailFragment.setArguments(bundle);

                // replace this Fragment to ProductDetailFragment
                FragmentManager fragmentManager = ((AppCompatActivity) CONTEXT).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, productDetailFragment)
                        .addToBackStack("productDetail")
                        .commit();
            });
            holder.buttonAdd.setOnClickListener(view -> {
                Toast.makeText(view.getContext(), "Thêm sản phẩm thành công!", Toast.LENGTH_SHORT).show();
                // add to cart
                new CartViewModel().addToCart(food);
            });
        }

        @Override
        public int getItemCount() {
            return mFoods.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {

            ImageView imageViewProduct;
            TextView textViewName, textViewPrice;
            ImageButton buttonAdd;

            public ViewHolder(View itemView) {
                super(itemView);
                imageViewProduct = itemView.findViewById(R.id.productImage);
                textViewName = itemView.findViewById(R.id.textViewNameProduct);
                textViewPrice = itemView.findViewById(R.id.textviewPrice);
                buttonAdd = itemView.findViewById(R.id.imageButton);
            }
        }
}
