package com.example.sparemate.users;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.sparemate.R;

import java.util.ArrayList;

public class BrandsActivity extends AppCompatActivity {

    ArrayList<Button> alb;
    TextView titleBrand;

    AppCompatButton btnOne,btnTwo,btnThree,btnFour,btnFive;


    private String category;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brands);

        // Retrieve the value of "homeClicked" extra passed from HomeActivity
        category = getIntent().getStringExtra("category");

        // Find buttons
        btnOne = findViewById(R.id.brand_one);
        btnTwo = findViewById(R.id.brand_two);
        btnThree = findViewById(R.id.brand_three);
        btnFour = findViewById(R.id.brand_four);
        btnFive = findViewById(R.id.brand_five);

        titleBrand = findViewById(R.id.brands);

        // Set button text based on homeClicked value
        if ("Car".equals(category)) {
            btnOne.setText("Suzuki");
            btnTwo.setText("Mahindra");
            btnThree.setText("Volkswagen");
            btnFour.setText("Honda");
            btnFive.setText("Skoda");
            titleBrand.setText("Car Brands");


        } else if ("Bike".equals(category)) {
            btnOne.setText("Yamaha");
            btnTwo.setText("Bajaj");
            btnThree.setText("TVS");
            btnFour.setText("Honda");
            btnFive.setText("Skoda");
            titleBrand.setText("Bike Brands");
        }


        // Set click listeners for all buttons
        setButtonClickListeners();
    }

    private void setButtonClickListeners() {
        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 intent = new Intent(BrandsActivity.this, TypesActivity.class);
                intent.putExtra("brand", btnOne.getText());
                navigateToTypesActivity();
            }
        });

        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 intent = new Intent(BrandsActivity.this, TypesActivity.class);
                intent.putExtra("brand", btnTwo.getText());
                navigateToTypesActivity();
            }
        });

        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 intent = new Intent(BrandsActivity.this, TypesActivity.class);
                intent.putExtra("brand", btnThree.getText());
                navigateToTypesActivity();
            }
        });

        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 intent = new Intent(BrandsActivity.this, TypesActivity.class);
                intent.putExtra("brand", btnFour.getText());
                navigateToTypesActivity();
            }
        });

        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 intent = new Intent(BrandsActivity.this, TypesActivity.class);
                intent.putExtra("brand",  btnFive.getText());
                navigateToTypesActivity();
            }
        });
    }
    Intent intent;

    private void navigateToTypesActivity() {
        intent.putExtra("category", category);
        startActivity(intent);
    }
}
