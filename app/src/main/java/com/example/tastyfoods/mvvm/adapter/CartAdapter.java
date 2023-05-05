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
import com.example.tastyfoods.mvvm.model.CartDetail;
import com.example.tastyfoods.mvvm.model.Food;
import com.example.tastyfoods.mvvm.viewmodels.cartdetail.CartViewModel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
    private List<CartDetail> mCart;
    private final Context CONTEXT;
    private final int ADD = 1, REMOVE = -1;

    public CartAdapter(Context context, List<CartDetail> cartDetails) {
        CONTEXT = context;
        mCart = cartDetails;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(CONTEXT).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartDetail cartDetail = mCart.get(position);
        CartViewModel cartViewModel = new CartViewModel();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("food")
            .document(String.valueOf(cartDetail.getFoodId()))
            .get()
            .addOnSuccessListener(documentSnapshot -> {
                Food tmp = documentSnapshot.toObject(Food.class);
                assert tmp != null;
                Glide.with(CONTEXT).load(tmp.getImage()).into(holder.imageViewProduct);
                holder.textViewPrice.setText(String.valueOf(tmp.getPrice()));
                holder.textViewName.setText(tmp.getName());
        });
        holder.textViewAmount.setText(String.valueOf(cartDetail.getAmount()));
        holder.textViewTotal.setText(String.valueOf(cartDetail.getMoney()));
        holder.imageViewAdd.setOnClickListener(view -> {
                cartDetail.setAmount(cartDetail.getAmount() + ADD);
                cartViewModel.updateCart(cartDetail);
        });

        holder.imageViewMinus.setOnClickListener(view -> {
            cartDetail.setAmount(cartDetail.getAmount() + REMOVE);
            cartViewModel.updateCart(cartDetail);
        });
    }

    @Override
    public int getItemCount() {
        return mCart.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProduct;
        TextView textViewName, textViewAmount, textViewPrice, textViewTotal;
        ImageView imageViewMinus, imageViewAdd;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewProduct = itemView.findViewById(R.id.image_cart);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewAmount = itemView.findViewById(R.id.textViewAmount);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewTotal = itemView.findViewById(R.id.textViewSum);
            imageViewMinus = itemView.findViewById(R.id.minus);
            imageViewAdd = itemView.findViewById(R.id.add);
        }
    }
}
