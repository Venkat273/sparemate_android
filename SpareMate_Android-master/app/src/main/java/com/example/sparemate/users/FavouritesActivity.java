package com.example.sparemate.users;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sparemate.Adapter.RecyclerViewAdapter;
import com.example.sparemate.R;

import java.util.ArrayList;

public class FavouritesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<com.example.sparemate.Others.FavoritesRecycler> recyclerDataArrayList;

    private TextView textView;

    private ImageView imageView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        recyclerView=findViewById(R.id.favorites);

        // created new array list..
        recyclerDataArrayList=new ArrayList<>();

        // added data to array list
        recyclerDataArrayList.add(new com.example.sparemate.Others.FavoritesRecycler("JK Tyre 165/80 R14 Taximax Tubeless Car Tyre",R.drawable.tyreone));
        recyclerDataArrayList.add(new com.example.sparemate.Others.FavoritesRecycler("JK ELANZO SUPRA 245/75 R16 111 Tubeless Car Tyre",R.drawable.tyretwo));
//        recyclerDataArrayList.add(new com.example.sparemate.Others.RecyclerData("JK Tyre 195/55 R16 UX Royale Car...",R.drawable.tyrefour));
//        recyclerDataArrayList.add(new com.example.sparemate.Others.RecyclerData("Apollo Alnac 4G 185/70 R14 88H Tubeless Car...",R.drawable.tyrefive));
//        recyclerDataArrayList.add(new com.example.sparemate.Others.RecyclerData("Bridgestone Dueler D684 TL R17 112T Tubeless....",R.drawable.tyresix));
//        recyclerDataArrayList.add(new com.example.sparemate.Others.RecyclerData("Goodyear DP-V1 185/60 R15 Tubeless Car Tyre...",R.drawable.tyreone));

        // added data from arraylist to adapter class.
        com.example.sparemate.Adapter.FavoritesAdapter adapter=new com.example.sparemate.Adapter.FavoritesAdapter(recyclerDataArrayList,this);

        // setting grid layout manager to implement grid view.
        // in this method '2' represents number of columns to be displayed in grid view.
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);

        // at last set adapter to recycler view.
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}

