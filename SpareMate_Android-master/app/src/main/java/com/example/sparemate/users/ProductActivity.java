package com.example.sparemate.users;



//import static androidx.core.app.NotificationCompatJellybean.TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sparemate.R;
import com.example.sparemate.RestClient;
import com.example.sparemate.api.ServerProductResponse;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<com.example.sparemate.Others.RecyclerData> recyclerDataArrayList;
    private TextView textView;
    private ImageView imageView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        recyclerView=findViewById(R.id.products);

        String category=getIntent().getStringExtra("category");
        String brand=getIntent().getStringExtra("brand");
        String type=getIntent().getStringExtra("type");

        Call<ServerProductResponse> serverProductResponseCall = RestClient.makeApi().productPutData(category,brand,type);
        serverProductResponseCall.enqueue(new Callback<ServerProductResponse>() {
            @Override
            public void onResponse(Call<ServerProductResponse> call, Response<ServerProductResponse> response) {

                if(response.isSuccessful()){

                    recyclerDataArrayList=new ArrayList<>();
                    for(int i=0;i<response.body().getData().size();i++){

                        ServerProductResponse.Datum d=response.body().getData().get(i);
                    // added data to array list
                        Log.e("TAG",d.getId()+" "+d.getCategory()+" "+
                                d.getBrand()+" "+d.getImage_name()+" "+d.getParts()+" "+d.getProduct_name());
                    recyclerDataArrayList.add(new com.example.sparemate.Others.RecyclerData(d.getId(),d.getCategory(),
                            d.getBrand(),d.getImage_name(),d.getParts(),d.getProduct_name(),d.getPrice()));

                    }
                    // added data from arraylist to adapter class.
                    com.example.sparemate.Adapter.RecyclerViewAdapter adapter=new com.example.sparemate.Adapter.RecyclerViewAdapter(recyclerDataArrayList,ProductActivity.this);

                    // setting grid layout manager to implement grid view.
                    // in this method '2' represents number of columns to be displayed in grid view.
                    GridLayoutManager layoutManager=new GridLayoutManager(ProductActivity.this,2);

                    // at last set adapter to recycler view.
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);

                }
            }
            @Override
            public void onFailure(Call<ServerProductResponse> call, Throwable t) {
                Toast.makeText(ProductActivity.this,"database error",Toast.LENGTH_SHORT).show();
            }
        });


        textView = findViewById(R.id.brands);

        String buttonClicked = getIntent().getStringExtra("type");

        textView.setText(buttonClicked);

    }
}

