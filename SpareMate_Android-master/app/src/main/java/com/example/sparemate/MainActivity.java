package com.example.sparemate;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

//import com.example.sparemate.employee.EmployeeLoginActivity;
import com.example.sparemate.admin.AdminMainActivity;
import com.example.sparemate.employee.EmployeeMainActivity;
//import com.example.sparemate.employee.RecentTaskActivity;

public class MainActivity extends AppCompatActivity {

    Button btn_toUser, btn_toEmployee,btn_toAdmin;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        btn_toUser = findViewById(R.id.user_button);
        btn_toEmployee = findViewById(R.id.employee_button);
        btn_toAdmin = findViewById(R.id.admin_button);

        btn_toUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra("loginBy", "user");
                startActivity(intent);
            }
        });

        btn_toEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra("loginBy", "emp");

                startActivity(intent);
            }
        });

        btn_toAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra("loginBy", "admin");
                startActivity(intent);
            }
        });


    }
}