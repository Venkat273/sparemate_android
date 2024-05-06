package com.example.sparemate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sparemate.api.ServerAddressResponse;
import com.example.sparemate.users.AddressActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressDetails extends AppCompatActivity {

    AppCompatButton addAddress;
    EditText addresstxt,state,contact,city,zipcode;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_address_details);
        addAddress= findViewById(R.id.addAdressBtn);
        addresstxt = findViewById(R.id.addresstxt);
        state = findViewById(R.id.statetxt);
        contact = findViewById(R.id.contact_notxt);
        city = findViewById(R.id.city);
        zipcode = findViewById(R.id.zip_codetxt);
        SharedPreferences sf = getSharedPreferences("sfname", MODE_PRIVATE);
        String id = sf.getString("id", null);
        String userName = sf.getString("username",null);


        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ServerAddressResponse> serverAddressResponseCall = new RestClient().makeApi().addressPutData(id,addresstxt.getText().toString(),
                        city.getText().toString(),state.getText().toString(),zipcode.getText().toString(),contact.getText().toString());
                serverAddressResponseCall.enqueue(new Callback<ServerAddressResponse>() {
                    @Override
                    public void onResponse(Call<ServerAddressResponse> call, Response<ServerAddressResponse> response) {




                        if(response.isSuccessful()){

                                SharedPreferences sf = getSharedPreferences("sfname", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sf.edit();
                                editor.putString("id", id);
                                editor.putString("name",userName);
                                editor.putString("address",addresstxt.getText().toString());
                                editor.putString("city",city.getText().toString());
                                editor.putString("state",state.getText().toString());
                                editor.putString("zipcode",zipcode.getText().toString());
                                editor.putString("contact",contact.getText().toString());
                                editor.apply();

                                Intent intent = new Intent(AddressDetails.this, AddressActivity.class);
                                startActivity(intent);
                                Toast.makeText(AddressDetails.this,"Address Changed",Toast.LENGTH_SHORT).show();


                        }else {
                            Toast.makeText(AddressDetails.this,"server error",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ServerAddressResponse> call, Throwable t) {
                        Toast.makeText(AddressDetails.this,"failed",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}