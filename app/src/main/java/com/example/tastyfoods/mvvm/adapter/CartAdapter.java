package com.example.tastyfoods.mvvm.adapter;

import android.content.Context;
import android.util.Log;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
    private List<CartDetail> mCart;
    private Context mContext;

    public CartAdapter(Context context, List<CartDetail> cartDetails) {
        mContext = context;
        mCart = cartDetails;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartDetail cartDetail = mCart.get(position);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Log.d("Food", cartDetail.getFoodId() + " done      --------------------");
        db.collection("food").document(String.valueOf(cartDetail.getFoodId())).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Food tmp = documentSnapshot.toObject(Food.class);
                Glide.with(mContext).load(tmp.getImage()).into(holder.imageViewProduct);
                holder.textViewPrice.setText(tmp.getPrice() + "");
                holder.textViewName.setText(tmp.getName());
            }
        });
        holder.textViewAmount.setText(cartDetail.getAmount() + "");
        holder.textViewTotal.setText(cartDetail.getMoney() + "");
    }

    @Override
    public int getItemCount() {
        return mCart.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewProduct;
        TextView textViewName, textViewAmount, textViewPrice, textViewTotal;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewProduct = itemView.findViewById(R.id.image_cart);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewAmount = itemView.findViewById(R.id.textViewAmount);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewTotal = itemView.findViewById(R.id.textViewSum);
        }
    }

    public List<CartDetail> getMCart() {
        return mCart;
    }
}
