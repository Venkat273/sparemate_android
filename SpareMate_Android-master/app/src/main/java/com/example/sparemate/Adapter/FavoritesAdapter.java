package com.example.sparemate.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sparemate.R;
import com.example.sparemate.users.DetailsActivity;

import java.util.ArrayList;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.RecyclerViewHolder> {

    private ArrayList<com.example.sparemate.Others.FavoritesRecycler> courseDataArrayList;
    private Context mcontext;

    public FavoritesAdapter(ArrayList<com.example.sparemate.Others.FavoritesRecycler> recyclerDataArrayList, Context mcontext) {
        this.courseDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        com.example.sparemate.Others.FavoritesRecycler recyclerData = courseDataArrayList.get(position);
        holder.courseTV.setText(recyclerData.getTitle());
        holder.courseIV.setImageResource(recyclerData.getImgid());

        holder.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When add to cart button is clicked, navigate to DetailsActivity
                Intent intent = new Intent(mcontext, DetailsActivity.class);
                // Pass any necessary data to DetailsActivity using intent extras
                intent.putExtra("title", recyclerData.getTitle());
                intent.putExtra("imageId", recyclerData.getImgid());
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
        private ImageView courseIV;
        private AppCompatButton addToCartBtn;

        @SuppressLint("WrongViewCast")
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            courseTV = itemView.findViewById(R.id.productContent_fav);
            courseIV = itemView.findViewById(R.id.productImage_fav);
            addToCartBtn = itemView.findViewById(R.id.addTocart_fav);
        }
    }
}

