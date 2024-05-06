package com.example.sparemate.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.annotation.GlideModule;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.sparemate.R;
import com.example.sparemate.users.DetailsActivity;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private ArrayList<com.example.sparemate.Others.RecyclerData> courseDataArrayList;
    private Context mcontext;
    public RecyclerViewAdapter(ArrayList<com.example.sparemate.Others.RecyclerData> recyclerDataArrayList, Context mcontext) {
        this.courseDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;

    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout, parent, false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        com.example.sparemate.Others.RecyclerData recyclerData = courseDataArrayList.get(position);
        holder.courseTV.setText(recyclerData.getProduct_name());
        holder.image.setOnClickListener(view -> {
            // When add to cart button is clicked, navigate to DetailsActivity
            Intent intent = new Intent(mcontext, DetailsActivity.class);
            // Pass any necessary data to DetailsActivity using intent extras
            intent.putExtra("id", recyclerData.getId());
            intent.putExtra("category", recyclerData.getCategory());
            intent.putExtra("brand",recyclerData.getBrand());
            intent.putExtra("imagename",recyclerData.getImage_name());
            intent.putExtra("parts",recyclerData.getParts());
            intent.putExtra("productname",recyclerData.getProduct_name());
            intent.putExtra("price",recyclerData.getPrice());
            mcontext.startActivity(intent);
        });
        String imageURl=recyclerData.getImage_name();
        Log.e("TAG",imageURl);

         com.bumptech.glide.Glide.with(mcontext).load(imageURl).apply(new RequestOptions().placeholder(R.drawable.placeholdererr) // Placeholder image
                        .error(R.drawable.baseline_wallpaper_24)) // Error image in case of loading failure
                .into(holder.image);



        holder.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When add to cart button is clicked, navigate to DetailsActivity
                Intent intent = new Intent(mcontext, DetailsActivity.class);
                // Pass any necessary data to DetailsActivity using intent extras
                intent.putExtra("id", recyclerData.getId());
                intent.putExtra("category", recyclerData.getCategory());
                intent.putExtra("brand",recyclerData.getBrand());
                intent.putExtra("imagename",recyclerData.getImage_name());
                intent.putExtra("parts",recyclerData.getParts());
                intent.putExtra("productname",recyclerData.getProduct_name());
                intent.putExtra("price",recyclerData.getPrice());
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return courseDataArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView courseTV;
        private ImageView image;
        private AppCompatButton addToCartBtn;

        @SuppressLint("WrongViewCast")
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            courseTV = itemView.findViewById(R.id.productContent);
            image = itemView.findViewById(R.id.productImage);
            addToCartBtn = itemView.findViewById(R.id.addTocart);
        }
    }
}

