package com.example.sparemate.users;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sparemate.Adapter.OrderAdapter;
import com.example.sparemate.Others.OrderModel;
import com.example.sparemate.R;
import com.example.sparemate.RestClient;
import com.example.sparemate.api.OrderDetailsRes;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter; // Correct variable type

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.orders_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create a list of orders
        SharedPreferences sf = getSharedPreferences("sfname", MODE_PRIVATE);
        String idsf = sf.getString("id", null);

        Call<OrderDetailsRes> orderDetailsResCall = RestClient.makeApi().orderData(idsf);
        orderDetailsResCall.enqueue(new Callback<OrderDetailsRes>() {
            @Override
            public void onResponse(Call<OrderDetailsRes> call, Response<OrderDetailsRes> response) {

                List<OrderModel> orders = new ArrayList<>();
                if(response.isSuccessful()) {
                    if(response.body().getStatus()==200)
                    {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            OrderDetailsRes.Datum d = response.body().getData().get(i);
                            orders.add(new OrderModel(d.getId(),d.getDate(), d.getCategory(),d.getBrand(), d.getImage_name(),
                                    d.getParts(),d.getProduct_name(), d.getAmount()));
                        }
                        OrderAdapter adapter=new OrderAdapter(orders,OrderActivity.this);
                        recyclerView.setAdapter(adapter);

                    }

                }
            }

            @Override
            public void onFailure(Call<OrderDetailsRes> call, Throwable t) {


            }
        });


    }
}
