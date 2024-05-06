package com.example.sparemate.users;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sparemate.Adapter.AddressAdapter;
import com.example.sparemate.AddressDetails;
import com.example.sparemate.Others.AddressModel;
import com.example.sparemate.R;

import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends AppCompatActivity {

    private Button deliveryBtn;
    private AppCompatButton button;
//    private RecyclerView recyclerView;

    RadioButton radioButton;

    private TextView textView,textView1,textView2,textView3;
//    private AddressAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        textView= findViewById(R.id.name);
        textView1 = findViewById(R.id.address);
        textView2 = findViewById(R.id.cityzip);
        textView3 = findViewById(R.id.contact);


        String productid = getIntent().getStringExtra("productid");
        String brand = getIntent().getStringExtra("brand");
        String parts = getIntent().getStringExtra("part");
        String price = getIntent().getStringExtra("price");
        String imageId = getIntent().getStringExtra("imagename");
        String pname = getIntent().getStringExtra("productname");


        SharedPreferences sf = getSharedPreferences("sfname", MODE_PRIVATE);
                String idsf = sf.getString("id", null);
                String userNamesf = sf.getString("username",null);
                String addresssf = sf.getString("address", null);
                String citysf = sf.getString("city", null);
                String zipsf = sf.getString("zipcode", null);
                String contactsf = sf.getString("contact_no", null);
                SharedPreferences.Editor editor = sf.edit();
                editor.putString("productid",productid );
                editor.putString("brand",brand );
                editor.putString("parts",parts);
                editor.putString("imageid",imageId );
                editor.putString("productname",pname);
                editor.putString("price",price);
                editor.apply();
                String cityzip =citysf+"-"+zipsf;
                textView.setText(userNamesf);
                textView1.setText(addresssf);
                textView2.setText(cityzip);
                textView3.setText(contactsf);

                button = findViewById(R.id.add_address);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressActivity.this, AddressDetails.class);
               startActivity(intent);
            }
        });


        // Find the delivery button
        deliveryBtn = findViewById(R.id.delivery);
        // Set OnClickListener for the delivery button
        deliveryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
                    // Start SummaryActivity when delivery button is clicked
                    Intent intent = new Intent(AddressActivity.this, SummaryActivity.class);

                    startActivity(intent);

            }

            private void validation() {
                if(textView.equals(null)||textView2.equals(null)||textView3.equals(null)||textView1.equals(null)){
                    Toast.makeText(AddressActivity.this,"",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
