package com.example.sparemate.admin;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sparemate.R;
import com.example.sparemate.RestClient;
import com.example.sparemate.api.AdminOrderRes;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<AdminOrderRes.Datum> data = new ArrayList<>();
    private  ArrayList<String> arrayList = new ArrayList<>();

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order);
        Call<AdminOrderRes> orderDetailsResCall = RestClient.makeApi().adminOrder();
        orderDetailsResCall.enqueue(new Callback<AdminOrderRes>() {
            @Override
            public void onResponse(Call<AdminOrderRes> call, Response<AdminOrderRes> response) {


                if(response.isSuccessful()) {
                    if(response.body().getStatus()==200)
                    {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            AdminOrderRes.Datum d =response.body().getData().get(i);
                           data.add(new AdminOrderRes.Datum(d.getId(),d.getDate(),d.getImage_name(),d.getParts(),d.getProduct_name(),d.getUsername(),
                           d.getStatus(),d.getContact_no(),d.getPayment_id(),d.getProduct_id(),d.getAmount(),d.getAssign()));
                        }
                        recyclerView = findViewById(R.id.recyclerAdminOrder);
                        recyclerView.setHasFixedSize(true);

                        layoutManager = new LinearLayoutManager(OrderActivity.this);
                        recyclerView.setLayoutManager(layoutManager);

                        adapter = new AdminOrderAdapter(data,OrderActivity.this);
                        recyclerView.setAdapter(adapter);
                    }

                }
            }

            @Override
            public void onFailure(Call<AdminOrderRes> call, Throwable t) {


            }
        });
    }
}
