package com.example.sparemate.employee;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sparemate.DashboardActivity;
import com.example.sparemate.MainActivity;
import com.example.sparemate.R;
import com.example.sparemate.StatusActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EmployeeMainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    DashboardActivity firstFragment = new DashboardActivity();
    StatusActivity secondFragment = new StatusActivity();
    // EmployeeLoginActivity thirdFragment = new EmployeeLoginActivity(); // Remove this line

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_main);

        bottomNavigationView = findViewById(R.id.bottom_nav_emp);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.dashboard_menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.dashboard_menu:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.employeeFrameLayout, firstFragment)
                        .commit();
                return true;

            case R.id.status_menu:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.employeeFrameLayout, secondFragment)
                        .commit();
                return true;

            case R.id.logout_menu:
                // Navigate to login activity for the third fragment
                Intent intent = new Intent(EmployeeMainActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }
}
