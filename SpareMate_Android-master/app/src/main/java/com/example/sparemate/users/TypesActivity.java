package com.example.sparemate.users;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.sparemate.R;

public class TypesActivity extends AppCompatActivity {

    AppCompatButton btnOne,btnTwo,btnThree,btnFour,btnFive;
    TextView titleTypes;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_types);

        String category = getIntent().getStringExtra("category");

        btnOne = findViewById(R.id.type_one);
        btnTwo = findViewById(R.id.type_two);
        btnThree = findViewById(R.id.type_three);
        btnFour = findViewById(R.id.type_four);
        btnFive = findViewById(R.id.type_five);
        titleTypes = findViewById(R.id.types);

        if ("Car".equals(category)) {
            // Set button text for Car related options
            btnOne.setText("Tyres");
            btnTwo.setText("Break system");
            btnThree.setText("Engine system");
            btnFour.setText("Steering system");
            btnFive.setText("Gear system");
            titleTypes.setText("Car Parts");
        } else if ("Bike".equals(category)) {
            // Set button text for Bike related options
            btnOne.setText("tyre");
            btnTwo.setText("Exhaust");
            btnThree.setText("Handle bar");
            btnFour.setText("Visor");
            btnFive.setText("Foot rost");
        }

        setButtonClickListeners();
    }

    private void setButtonClickListeners() {

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = getIntent().getStringExtra("category");
                String brand = getIntent().getStringExtra("brand");
                Intent intent = new Intent(TypesActivity.this, ProductActivity.class);
                intent.putExtra("category",category);
                intent.putExtra("brand",brand);
                intent.putExtra("type", btnOne.getText());
                startActivity(intent);
            }
        });

        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = getIntent().getStringExtra("category");
                String brand = getIntent().getStringExtra("brand");
                Intent intent = new Intent(TypesActivity.this, ProductActivity.class);
                intent.putExtra("category",category);
                intent.putExtra("brand",brand);
                intent.putExtra("type", btnTwo.getText());
                startActivity(intent);
            }
        });

        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = getIntent().getStringExtra("category");
                String brand = getIntent().getStringExtra("brand");
                Intent intent = new Intent(TypesActivity.this, ProductActivity.class);
                intent.putExtra("category",category);
                intent.putExtra("brand",brand);
                intent.putExtra("type", btnThree.getText());
                startActivity(intent);
            }
        });

        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = getIntent().getStringExtra("category");
                String brand = getIntent().getStringExtra("brand");
                Intent intent = new Intent(TypesActivity.this, ProductActivity.class);
                intent.putExtra("category",category);
                intent.putExtra("brand",brand);
                intent.putExtra("type", btnFour.getText());
                startActivity(intent);
            }
        });

        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = getIntent().getStringExtra("category");
                String brand = getIntent().getStringExtra("brand");
                Intent intent = new Intent(TypesActivity.this, ProductActivity.class);
                intent.putExtra("category",category);
                intent.putExtra("brand",brand);
                intent.putExtra("type", btnFive.getText());
                startActivity(intent);
            }
        });
    }
}
