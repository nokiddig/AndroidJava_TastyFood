package com.example.tastyfoods.mvvm.view.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.tastyfoods.R;
import com.example.tastyfoods.mvvm.model.User;
import com.example.tastyfoods.mvvm.viewmodels.login.LoginActivity;
import com.example.tastyfoods.mvvm.viewmodels.profile.ProfileViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {
    private static final String ARG_PARAM1 = "phoneNumber";
    private ProfileViewModel profileViewModel;
    private String mParam1;
    private TextView textViewUserName, textViewPhoneNumber;
    private EditText editTextFullName, editTextAddress, editTextAvatar;
    private AppCompatButton buttonChangeAvatar, buttonSave;
    private FloatingActionButton buttonLogout;
    private ImageView imageViewAvatar;

    public ProfileFragment() {}
    public static ProfileFragment newInstance(String phoneNumber) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, phoneNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        this.initViews(view);
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        profileViewModel.getUser(mParam1).observe(getViewLifecycleOwner(), user -> {
            if (user == null){
                return;
            }
            textViewUserName.setText(user.getName()==null?"null":user.getName());
            textViewPhoneNumber.setText(user.getPhoneNumber()==null?"null":user.getPhoneNumber());
            editTextFullName.setText(user.getName()==null?"null":user.getName());
            editTextAddress.setText(user.getAddress()==null?"null":user.getAddress());
            if (user.getImage()!=null && user.getImage().length()>=10 && getActivity()!=null) {
                Glide.with(getActivity())
                        .load(user.getImage())
                        .centerCrop()
                        .placeholder(R.drawable.anh)
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .into(imageViewAvatar);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonSave.setOnClickListener(view1 -> {
            User newUser = new User(editTextFullName.getText().toString(),
                    editTextAddress.getText().toString(),0,
                    textViewPhoneNumber.getText().toString(),
                    null,
                    editTextAvatar.getText().toString(),
                    null);
            profileViewModel.updateUser(newUser);
            editTextAvatar.setVisibility(View.INVISIBLE);
        });

        buttonLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent=new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        });

        buttonChangeAvatar.setOnClickListener(view12 -> editTextAvatar.setVisibility(View.VISIBLE));
    }

    private void initViews(View view) {
        textViewPhoneNumber = view.findViewById(R.id.textViewPhoneNumber);
        textViewUserName = view.findViewById(R.id.textViewUserName);
        editTextAddress = view.findViewById(R.id.editTextAddress);
        editTextFullName = view.findViewById(R.id.editTextFullName);
        buttonSave = view.findViewById(R.id.buttonSave);
        buttonChangeAvatar = view.findViewById(R.id.buttonChangeAvatar);
        buttonLogout = view.findViewById(R.id.buttonLogout);
        imageViewAvatar = view.findViewById(R.id.imageViewAvatar);
        editTextAvatar = view.findViewById(R.id.editTextChangeAvatar);
        editTextAvatar.setVisibility(View.INVISIBLE);
    }
}