package com.example.sparemate;


import com.example.sparemate.Adapter.ViewPageAdapter;
import com.example.sparemate.R.id;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.example.sparemate.users.BrandsActivity;
import com.example.sparemate.users.NotificationActivity;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;



public class HomeActivity extends Fragment {

    private ViewPager viewPager;
    private int currentPage = 0;

    private ImageButton t;
    private Timer timer;
    private final long DELAY_MS = 500; // Delay in milliseconds before task is to be executed
    private final long PERIOD_MS = 3000; // Time in milliseconds between successive task executions.

    TextView textView,textView1;


    public HomeActivity(){
        // require an empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Find the Cars button
        RelativeLayout carsButton = view.findViewById(id.cars);


        // Set a click listener for the Cars button
        carsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the BrandsActivity when the Cars button is clicked
                Intent intent = new Intent(getActivity(), BrandsActivity.class);
                intent.putExtra("category", "Car");
                startActivity(intent);
            }
        });

        // Find the Bikes button
        RelativeLayout bikesButton = view.findViewById(R.id.bikes);
        // Set a click listener for the Bikes button
        bikesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the BrandsActivity when the Bikes button is clicked
                Intent intent = new Intent(getActivity(), BrandsActivity.class);
                intent.putExtra("category", "Bike");
                startActivity(intent);

            }
        });

        ImageButton notification = view.findViewById(id.notification);

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the BrandsActivity when the Bikes button is clicked
                Intent intent = new Intent(getActivity(), NotificationActivity.class);
                startActivity(intent);
            }
        });

        // Initialize ViewPager
        viewPager = view.findViewById(R.id.viewPager);

        // Create an ArrayList to hold the image resources
        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.img1);
        images.add(R.drawable.img2);
        images.add(R.drawable.img3);

        // Create ViewPageAdapter with the ArrayList of images
        ViewPageAdapter adapter = new ViewPageAdapter(getActivity(), images);
        viewPager.setAdapter(adapter);

        // Auto start for the first time
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == adapter.getCount()) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

        return view;
    }


}