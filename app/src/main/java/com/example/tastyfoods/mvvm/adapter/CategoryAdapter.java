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
import com.example.tastyfoods.mvvm.model.Category;
import com.google.firebase.storage.FirebaseStorage;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Category> mCategories;
    private Context mContext;

    public CategoryAdapter(Context context, List<Category> categories) {
        mContext = context;
        mCategories = categories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_category_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = mCategories.get(position);

        holder.tvName.setText(category.getName());

        FirebaseStorage storage = FirebaseStorage.getInstance();
        Glide.with(mContext)
                .load(category.getImage())
                .centerCrop()
                .placeholder(R.drawable.anh)
                .into(holder.ivImage);

        holder.ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // show product by category
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImage;
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.productImage);
            tvName = itemView.findViewById(R.id.textviewPrice);
        }
    }

    public List<Category> getMCategories() {
        return mCategories;
    }

    public void setMCategories(List<Category> mCategories) {
        this.mCategories = mCategories;
    }
}