package com.example.sparemate.users;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sparemate.R;
import com.example.sparemate.RestClient;
import com.example.sparemate.api.AddProductResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {

    AppCompatButton buyNowBtn,addCartbtn;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        addCartbtn = findViewById(R.id.addToCart);

        // Get the title and image from the intent extras
        String productid = getIntent().getStringExtra("id");
        String brand = getIntent().getStringExtra("brand");
        String parts = getIntent().getStringExtra("parts");
        String title = getIntent().getStringExtra("productname");
        String imageId = getIntent().getStringExtra("imagename");
        String price =getIntent().getStringExtra("price");// Default value if not found


        // Find views in the layout
        TextView titleTextView = findViewById(R.id.product_title);
        ImageView imageView = findViewById(R.id.product_image);
        TextView parttext = findViewById(R.id.parttxt);
        TextView brandName = findViewById(R.id.brandname);
        TextView pricetxt =findViewById(R.id.product_prize);

        // Set the title and image in the views
        brandName.setText(brand);
        pricetxt.setText(price);
        parttext.setText(parts);
        titleTextView.setText(title);
        Glide.with(this)
                .load(getIntent().getStringExtra("imagename"))
                .apply(new RequestOptions()
                        .placeholder(R.drawable.placeholdererr) // Placeholder image
                        .error(R.drawable.placeholdererr) // Error image in case of loading failure
                )
                .into(imageView);

        buyNowBtn = findViewById(R.id.buyNow);

        SharedPreferences sf = getSharedPreferences("sfname", MODE_PRIVATE);
        String idsf = sf.getString("id", null);

        addCartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<AddProductResponse> addProductResponseCall = RestClient.makeApi().addToCart(productid,idsf,"1");
                addProductResponseCall.enqueue(new Callback<AddProductResponse>() {
                    @Override
                    public void onResponse(Call<AddProductResponse> call, Response<AddProductResponse> response) {
                        if(response.isSuccessful()){
                            if(response.body().getStatus().equals("200")){
                                Toast.makeText(DetailsActivity.this, "added to cart", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AddProductResponse> call, Throwable t) {
                        Toast.makeText(DetailsActivity.this, "can't add to cart", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        buyNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, AddressActivity.class);
                intent.putExtra("productid",productid);
                intent.putExtra("productname", titleTextView.getText());
                intent.putExtra("brand",brand);
                intent.putExtra("part",parts);
                intent.putExtra("price",price);
                intent.putExtra("imagename",imageId);
                startActivity(intent);
            }
        });
    }
}
