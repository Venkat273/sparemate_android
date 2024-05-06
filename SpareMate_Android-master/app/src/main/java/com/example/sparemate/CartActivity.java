package com.example.sparemate;

import static android.content.Context.MODE_PRIVATE;


import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.Toast;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sparemate.Adapter.AddressAdapter;
import com.example.sparemate.Adapter.CartAdapter;
import com.example.sparemate.Others.AddressModel;
import com.example.sparemate.Others.CartModel;
import com.example.sparemate.Others.OrderModel;
import com.example.sparemate.api.CartResponse;
import com.example.sparemate.api.ServerProductResponse;
import com.example.sparemate.users.ProductActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends Fragment {
    private ArrayList<com.example.sparemate.Others.CartModel> recyclerDataArrayList;
    private RecyclerView recyclerView;
    private CartAdapter adapter;

    public CartActivity(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerView = view.findViewById(R.id.cart_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        SharedPreferences sf = getActivity().getSharedPreferences("sfname", MODE_PRIVATE);
        String idsf = sf.getString("id", null);


        // Add addresses to the list

        Call<CartResponse> cartResponseCall = RestClient.makeApi().cartData(idsf);
        cartResponseCall.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {

                recyclerDataArrayList=new ArrayList<>();
                Log.e("TAG"," "+response.body().getData().size());
                if(response.isSuccessful()) {

                    if(response.body().getStatus()==200)
                    {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            CartResponse.Datum d = response.body().getData().get(i);
                            recyclerDataArrayList.add(new com.example.sparemate.Others.CartModel(d.getId(), d.getCategory(),d.getBrand(), d.getImage_name(),
                                     d.getParts(),d.getProduct_name(), d.getPrice()));
                        }
                        com.example.sparemate.Adapter.CartAdapter adapter=new com.example.sparemate.Adapter.CartAdapter(recyclerDataArrayList, getActivity());
                        recyclerView.setAdapter(adapter);

                    }

                }
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {


            }
        });




        return view;
    }
}

