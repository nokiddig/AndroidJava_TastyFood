package com.example.tastyfoods.mvvm.view.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tastyfoods.R;
import com.example.tastyfoods.mvvm.adapter.CategoryAdapter;
import com.example.tastyfoods.mvvm.model.Food;
import com.example.tastyfoods.mvvm.model.ItemSearch;
import com.example.tastyfoods.mvvm.adapter.SearchAdapter;
import com.example.tastyfoods.mvvm.viewmodels.search.SearchViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListSearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "name";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private ArrayList<ItemSearch> itemSearchArrayList;
    private int[] image;
    private String[] describe;

    private String[] name;
    private String[] price;
    private int[] button;
    private RecyclerView recyclerView;

    public ListSearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment FragmentListSearch.
     */
    // TODO: Rename and change types and number of parameters
    public static ListSearchFragment newInstance(String param1) {
        ListSearchFragment fragment = new ListSearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerviewSearch);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        SearchViewModel searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);

        try {
            int categoryId = Integer.parseInt(mParam1);
            searchViewModel.findByCategory(categoryId).observe(getViewLifecycleOwner(), foods -> {
                SearchAdapter searchAdapter = new SearchAdapter(getContext(), (ArrayList<Food>) foods);
                recyclerView.setAdapter(searchAdapter);
            });
        } catch (NumberFormatException nfe) {
            searchViewModel.findByName(mParam1).observe(getViewLifecycleOwner(), foods -> {
                SearchAdapter searchAdapter = new SearchAdapter(getContext(), (ArrayList<Food>) foods);
                recyclerView.setAdapter(searchAdapter);
            });
        }
    }
}