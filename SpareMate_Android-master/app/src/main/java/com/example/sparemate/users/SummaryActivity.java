package com.example.sparemate.users;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sparemate.R;
import com.example.sparemate.RestClient;
import com.example.sparemate.api.OrderResponse;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import com.example.sparemate.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SummaryActivity extends AppCompatActivity implements PaymentResultListener {

    private AppCompatButton payBtn;

    private TextView name;
    private TextView address;
    private TextView number;
    private TextView pname;
    private TextView price,price1,total;
    private ImageView pImage;
    int amount;
    String formattedDate;
    String formattedTime;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint({"MissingInflatedId", "DefaultLocale"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        name = findViewById(R.id.ad_name);
        address = findViewById(R.id.ad_fulladress);
        number = findViewById(R.id.ad_number);
        pname = findViewById(R.id.summary_title);
        pImage = findViewById(R.id.summary_image);
        price = findViewById(R.id.price);
        price1 = findViewById(R.id.price1);
        total = findViewById(R.id.total);

        // Get current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Extract date components
        int year = currentDateTime.getYear();
        int month = currentDateTime.getMonthValue();
        int day = currentDateTime.getDayOfMonth();

        // Extract time components
        int hour = currentDateTime.getHour();
        int minute = currentDateTime.getMinute();
        int second = currentDateTime.getSecond();

        // Format date and time components separately
        formattedDate = String.format("%04d-%02d-%02d", year, month, day+3);
        formattedTime = String.format("%02d:%02d:%02d", hour, minute, second);



        findViewById(R.id.addresschangebutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SummaryActivity.this,AddressActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences sf = getSharedPreferences("sfname", MODE_PRIVATE);
        String idsf = sf.getString("id", null);
        String userNamesf = sf.getString("username",null);
        String addresssf = sf.getString("address", null);
        String citysf = sf.getString("city", null);
        String zipsf = sf.getString("zipcode", null);
        String contactsf = sf.getString("contact_no", null);
        String productid = sf.getString("productid", null);
        String brand = sf.getString("brand", null);
        String parts = sf.getString("parts", null);
        String imageId = sf.getString("imageid", null);
        String productname = sf.getString("productname", null);
        String pricesf = sf.getString("price", null);

        int i = Integer.parseInt(pricesf) + 99;
        String string = String.valueOf(i);

        String fulladdress =addresssf+"\n"+citysf+"-"+zipsf;
        name.setText(userNamesf);
        address.setText(fulladdress);
        number.setText(contactsf);
        pname.setText(productname);
        price.setText("₹"+pricesf);
        price1.setText("₹"+pricesf);
        total.setText("₹"+string);

//        Toast.makeText(SummaryActivity.this,,Toast.LENGTH_SHORT).show();

        Glide.with(this)
                .load(imageId)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.placeholdererr) // Placeholder image
                        .error(R.drawable.placeholdererr) // Error image in case of loading failure
                )
                .into(pImage);

//         initializing all our variables.
//        amountEdt = findViewById(R.id.idEdtAmount);
        payBtn = findViewById(R.id.continues);

        // adding on click listener to our button.
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting


                // rounding off the amount.
                 amount = Math.round(i * 100);

                // initialize Razorpay account.
                Checkout checkout = new Checkout();

                // set your id as below
                checkout.setKeyID("rzp_test_L2he0XY7YpfpJo");

                // set image
                checkout.setImage(R.drawable.logo);

                // initialize json object
                JSONObject object = new JSONObject();
                try {
                    // to put name
                    object.put("name", pname.getText()+" "+parts);

                    // put description
                    object.put("description", "Test payment");

                    // to set theme color
                    object.put("theme.color", "#BC9BBD");

                    // put the currency
                    object.put("currency", "INR");

                    // put amount
                    object.put("amount", amount);

                    // put mobile number
                    object.put("prefill.contact", "7708421972"); //

                    // put email
                    object.put("prefill.email", "vivek.sse@saveetha.com");

                    // open razorpay to checkout activity
                    checkout.open(SummaryActivity.this, object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        // this method is called on payment success.

        SharedPreferences sf = getSharedPreferences("sfname", MODE_PRIVATE);
        String idsf = sf.getString("id", null);
        String productid = sf.getString("productid", null);


        Call<OrderResponse> orderResponseCall = RestClient.makeApi().orderSend(idsf,s, String.valueOf(amount),productid,formattedDate,formattedTime,"pending");
        orderResponseCall.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus().equals("200")){
                        Toast.makeText(SummaryActivity.this, "Payment is successful : " + s, Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                Toast.makeText(SummaryActivity.this, " response failed ", Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public void onPaymentError(int i, String s) {
        // on payment failed.
        Toast.makeText(this, "Payment Failed due to error : " + s, Toast.LENGTH_SHORT).show();
    }
}