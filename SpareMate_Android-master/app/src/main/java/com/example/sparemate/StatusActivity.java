package com.example.sparemate;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.Toast;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sparemate.Adapter.OrderAdapter;
import com.example.sparemate.Others.OrderModel;
import com.example.sparemate.api.EmpOrderStatusRes;
import com.example.sparemate.api.OrderDetailsRes;
import com.example.sparemate.users.OrderActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatusActivity extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<EmpOrderStatusRes.Datum> data = new ArrayList<>();

    public StatusActivity(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_status_activity, container, false);
        recyclerView = view.findViewById(R.id.emprecyclerviewstatus);

        SharedPreferences sf = getActivity().getSharedPreferences("empsf", Context.MODE_PRIVATE);
        String idsf=sf.getString("empname",null);

        Call<EmpOrderStatusRes> orderDetailsResCall = RestClient.makeApi().empStatus(idsf);
        orderDetailsResCall.enqueue(new Callback<EmpOrderStatusRes>() {
            @Override
            public void onResponse(Call<EmpOrderStatusRes> call, Response<EmpOrderStatusRes> response) {


                if(response.isSuccessful()) {
                    if(response.body().getStatus()==200)
                    {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            EmpOrderStatusRes.Datum d = response.body().getData().get(i);
                            data.add(new EmpOrderStatusRes.Datum(d.getId(),d.getDate(), d.getCategory(),d.getBrand(), d.getImage_name(),
                                    d.getParts(),d.getProduct_name(),d.getUsername(),d.getStatus(),d.getContact_no(),d.getProduct_id(), d.getAmount()));
                        }
                        recyclerView.setHasFixedSize(true);

                        layoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);

                        adapter = new EmployeeAdapter(data,getContext());
                        recyclerView.setAdapter(adapter);
                    }

                }
            }

            @Override
            public void onFailure(Call<EmpOrderStatusRes> call, Throwable t) {


            }
        });




        return view;
    }

}