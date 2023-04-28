package com.example.tastyfoods.mvvm.viewmodels.find;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tastyfoods.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentListSearch#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentListSearch extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<ItemSearch> itemSearchArrayList;
    private int[] image;
    private String[] describe;

    private String[] name;
    private String[] price;
    private int[] button;
    private RecyclerView recyclerview;

    public FragmentListSearch() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentListSearch.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentListSearch newInstance(String param1, String param2) {
        FragmentListSearch fragment = new FragmentListSearch();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_search, container, false);
    }

    // code

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // khởi tạo 1 phương thức
        dataInitialize();
        recyclerview = view.findViewById(R.id.recyclerviewSearch);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
        SearchAdapter searchAdapter = new SearchAdapter(getContext(), itemSearchArrayList);
        recyclerview.setAdapter(searchAdapter);
        searchAdapter.notifyDataSetChanged();

    }

    private void dataInitialize() {

        itemSearchArrayList = new ArrayList<>();
        image = new int[]{
                R.drawable.bread,
                R.drawable.pizza,
                R.drawable.sandwich_thit_bo,
                R.drawable.pizza_sea,
                R.drawable.coca,
                R.drawable.pepsi,

        };
        name = new String[]{
                "R.string.name_1",
                "R.string.name_2",
                "R.string.name_3",
                "R.string.name_4",
                "R.string.name_5",
                "R.string.name_6",
        };
        describe = new String[]{
                "R.string.describe_1",
                "R.string.describe_2",
                "R.string.describe_3",
                "R.string.describe_4",
                "R.string.describe_5",
                "R.string.describe_6",
        };
        price = new String[]{
                "R.string.price_1",
                "R.string.price_2",
                "R.string.price_3",
                "R.string.price_4",
                "R.string.price_5",
                "R.string.price_6",

        };
        button  = new int[]{
                R.drawable.baseline_arrow_back_ios_new_24,
                R.drawable.baseline_arrow_back_ios_new_24,
                R.drawable.baseline_arrow_back_ios_new_24,
                R.drawable.baseline_arrow_back_ios_new_24,
                R.drawable.baseline_arrow_back_ios_new_24,
                R.drawable.baseline_arrow_back_ios_new_24,

        };

        for(int i = 0; i<image.length; i++) {
            ItemSearch itemSearch = new ItemSearch(image[i], name[i], describe[i], price[i], button[i] );
            itemSearchArrayList.add(itemSearch);
        }
    }
}