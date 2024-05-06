package com.example.sparemate.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.sparemate.MainActivity;
import com.example.sparemate.R;
import com.example.sparemate.users.OrderActivity;
import com.google.android.material.navigation.NavigationView;

public class AdminMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ImageButton toggleButton;
    NavigationView navigationView;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        drawerLayout = findViewById(R.id.admin_drawer_layout);
        toggleButton = findViewById(R.id.toggleButton);
        navigationView = findViewById(R.id.admin_nav_menu); // Corrected

        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        if (toggleButton != null) {
            toggleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        drawerLayout.closeDrawer(GravityCompat.START);
                    } else if (drawerLayout != null) {
                        drawerLayout.openDrawer(GravityCompat.START);
                    }
                }
            });
        }

         // Rest of your onCreate() method remains the same...
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = null; // Corrected

        switch (item.getItemId()) {
            case R.id.nav_add_project_admin:
                intent = new Intent(AdminMainActivity.this, AddProductActivity.class);
                break;
            case R.id.nav_add_emp_admin:
                intent = new Intent(AdminMainActivity.this, AddEmployeeActivity.class);
                break;
            case R.id.nav_emp_details_admin:
                intent = new Intent(AdminMainActivity.this,com.example.sparemate.admin.DetailsActivity.class);
                break;
            case R.id.nav_order_admin:
                intent = new Intent(AdminMainActivity.this, com.example.sparemate.admin.OrderActivity.class);
                break;
            case R.id.nav_logout_admin:
                intent = new Intent(AdminMainActivity.this, MainActivity.class);
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }

        if (drawerLayout != null) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
