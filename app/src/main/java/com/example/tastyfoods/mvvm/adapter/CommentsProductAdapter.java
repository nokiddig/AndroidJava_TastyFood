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

import com.example.tastyfoods.R;
import com.example.tastyfoods.mvvm.model.Feedback;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class CommentsProductAdapter extends RecyclerView.Adapter<CommentsProductAdapter.ViewHolder>{

    private List<Feedback> mFeedbacks;
    private Context mContext;
    public CommentsProductAdapter(Context context, List<Feedback> feedbacks) {
        mContext = context;
        mFeedbacks = feedbacks;
    }

    @NonNull
    @Override
    public CommentsProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.product_detail_list_comments, parent, false);
        return new CommentsProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Feedback feedback= mFeedbacks.get(position);
        holder.textComment.setText(feedback.getContent());
        int ratePoint = feedback.getRatePoint();
        switch (ratePoint){
            case 1:
                holder.img_ratePoint.setImageResource(R.drawable.ratepoint1);
                break;
            case 2:
                holder.img_ratePoint.setImageResource(R.drawable.ratepoint2);
                break;
            case 3:
                holder.img_ratePoint.setImageResource(R.drawable.ratepoint3);
                break;
            case 4:
                holder.img_ratePoint.setImageResource(R.drawable.ratepoint4);
                break;
            case 5:
                holder.img_ratePoint.setImageResource(R.drawable.ratepoint5);
                break;
        }
        Log.d("no", feedback.getUserId() + "");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("user").document(String.valueOf(feedback.getUserId()));

        docRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                holder.textNameUser.setText( documentSnapshot.getData().get("name").toString());
            } else {
                Log.d("TAG", "No such document");
            }
        }).addOnFailureListener(e -> {
            Log.d("TAG", "Error getting document: " + e.getMessage());
        });
        // tải hình ảnh
        //Glide.with(mContext)
        //.load(.getImage())
        // .centerCrop()
        //.placeholder(R.drawable.anh)
        //.into(holder.imageViewProduct);
    }

    @Override
    public int getItemCount() {
        return mFeedbacks.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textComment, textNameUser;
        ImageView img_ratePoint;
        public ViewHolder(View itemView) {
            super(itemView);
            textComment = itemView.findViewById(R.id.text_comments);
            textNameUser = itemView.findViewById(R.id.text_nameUser);
            img_ratePoint = itemView.findViewById(R.id.img_ratePoint);
        }
    }

    public List<Feedback> getMFeedbacks() {
        return mFeedbacks;
    }

    public void setMFoods(List<Feedback> mFoods) {
        this.mFeedbacks = mFeedbacks;
    }
}
