package com.example.sparemate.users;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sparemate.Adapter.NotificationAdapter;
import com.example.sparemate.Others.NotificationModel;
import com.example.sparemate.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NotificationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        recyclerView = findViewById(R.id.notification_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<NotificationModel> notificationList = new ArrayList<>(); // Corrected typo

        notificationList.add(new NotificationModel("Your MT visor is ready to ship and packed.... ", "4 days ago", R.drawable.one)); // Corrected typo
        notificationList.add(new NotificationModel("Your v3 exhauster is ready to ship and packed.... ", "8 days ago", R.drawable.two)); // Corrected typo

        // Create adapter
        adapter = new NotificationAdapter(notificationList); // Corrected typo
        // Set adapter to RecyclerView
        recyclerView.setAdapter(adapter);
    }
}
