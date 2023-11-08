package com.example.tastyfoods.mvvm.view.productdetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tastyfoods.R;
import com.example.tastyfoods.mvvm.adapter.CommentsProductAdapter;
import com.example.tastyfoods.mvvm.model.Feedback;
import com.example.tastyfoods.mvvm.model.Food;
import com.example.tastyfoods.mvvm.viewmodels.cartdetail.CartViewModel;
import com.example.tastyfoods.mvvm.viewmodels.productdetail.CommentsProductViewModel;
import com.example.tastyfoods.mvvm.viewmodels.productdetail.ProductDetailViewModel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ProductDetailFragment extends Fragment {
    private final FirebaseFirestore DB = FirebaseFirestore.getInstance();
    private CommentsProductAdapter commentsProductAdapter;
    private CommentsProductViewModel commentsProductViewModel;
    private Food food;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            food = (Food) bundle.getSerializable("food");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        RecyclerView comments;
        ImageView imgFood;
        TextView foodName, money, describe, ratePoint, sell;
        Button buttonAdd;
        buttonAdd = view.findViewById(R.id.btn_add);
        foodName = view.findViewById(R.id.text_name);
        money = view.findViewById(R.id.text_money);
        ratePoint = view.findViewById(R.id.text_ratePoint);
        describe = view.findViewById(R.id.text_describe);
        sell = view.findViewById(R.id.text_sell);
        comments = view.findViewById(R.id.recycler_view_comments);
        imgFood = view.findViewById(R.id.imageFood);
        ProductDetailViewModel viewModel = new ProductDetailViewModel(food);
        viewModel.getDataFood().observe(getViewLifecycleOwner(), new Observer<Food>() {
            @Override
            public void onChanged(Food f) {
                Glide.with(imgFood.getContext())
                        .load(f.getImage())
                        .into(imgFood);
                ratePoint.setText(String.valueOf(f.getRatePoint()));
                foodName.setText(f.getName());
                money.setText(String.valueOf(f.getPrice()));
                describe.setText(f.getDescription());
                sell.setText(String.valueOf(f.getSales()));

            }
        });
        //Hien List Comments
        comments.setHasFixedSize(true);
        commentsProductViewModel = new ViewModelProvider(this).get(CommentsProductViewModel.class);
        commentsProductViewModel.getFeedbacks(food).observe(getViewLifecycleOwner(), new Observer<List<Feedback>>() {
            @Override
            public void onChanged(List<Feedback> feedbacks) {
                commentsProductAdapter = new CommentsProductAdapter(getContext(), feedbacks);
                comments.setAdapter(commentsProductAdapter);
            }
        });
        Button buttonBack = view.findViewById(R.id.btn_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getParentFragmentManager().getBackStackEntryCount() == 0) {
                    return;
                }
                getParentFragmentManager().popBackStack();
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Thêm sản phẩm thành công!", Toast.LENGTH_SHORT).show();
                new CartViewModel().addToCart(food);
            }
        });
        return view;
    }
}

