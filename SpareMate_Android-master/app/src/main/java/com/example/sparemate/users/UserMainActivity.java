package com.example.sparemate.users;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.sparemate.CartActivity;
import com.example.sparemate.HomeActivity;
import com.example.sparemate.MainActivity;
import com.example.sparemate.ProfileActivity;
import com.example.sparemate.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class UserMainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener, NavigationView.OnNavigationItemSelectedListener  {

    BottomNavigationView bottomNavigationView;
    HomeActivity firstFragment = new HomeActivity();
    CartActivity secondFragment = new CartActivity();
    ProfileActivity thirdFragment = new ProfileActivity();
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private ImageButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home_menu);

        // Correctly reference NavigationView instead of just View
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

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
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.userFrameLayout, firstFragment).commit();
                break;
            case R.id.cart_menu:
            case R.id.nav_mycart:
                getSupportFragmentManager().beginTransaction().replace(R.id.userFrameLayout, secondFragment).commit();
                break;
            case R.id.profile_menu:
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.userFrameLayout, thirdFragment).commit();
                break;
            case R.id.nav_favourites:
                Intent favIntent = new Intent(UserMainActivity.this, FavouritesActivity.class);
                startActivity(favIntent);
                break;
            case R.id.nav_orders:
                Intent orderIntent = new Intent(UserMainActivity.this, OrderActivity.class);
                startActivity(orderIntent);
                break;
            case R.id.nav_logout:
                Intent intent = new Intent(UserMainActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }

        // Close the drawer after handling the click
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
