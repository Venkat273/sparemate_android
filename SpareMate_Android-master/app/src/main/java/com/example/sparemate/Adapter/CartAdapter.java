package com.example.sparemate.Adapter;


import static android.content.Intent.getIntent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.request.RequestOptions;
import com.example.sparemate.Others.CartModel;
import com.example.sparemate.R;
import com.example.sparemate.StartActivity;
import com.example.sparemate.users.DetailsActivity;

import java.util.ArrayList;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private ArrayList<com.example.sparemate.Others.CartModel> cartList;
    private Context mcontext;

    public CartAdapter(ArrayList<com.example.sparemate.Others.CartModel> recyclerDataArrayList, Context mcontext) {
        this.cartList = recyclerDataArrayList;
        this.mcontext = mcontext;

    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_layout, parent, false);


        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartModel cart = cartList.get(position);

        holder.cartTitle.setText(cart.product_name);
        com.bumptech.glide.Glide.with(mcontext).load(cart.image_name).apply(new RequestOptions().placeholder(R.drawable.placeholdererr) // Placeholder image
                        .error(R.drawable.baseline_wallpaper_24)) // Error image in case of loading failure
                .into(holder.cartImageId);
        holder.textView.setText(cart.category);
        holder.textView1.setText(cart.brand);
        holder.textView2.setText(cart.parts);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mcontext, DetailsActivity.class);
                intent.putExtra("id",cart.getId());
                intent.putExtra("brand",cart.getBrand());
                intent.putExtra("parts",cart.getParts());
                intent.putExtra("productname",cart.getProduct_name());
                intent.putExtra("imagename",cart.getImage_name());
                intent.putExtra("price",cart.getPrice());
                mcontext.startActivity(intent);

            }
        });


    }



    @Override
    public int getItemCount() {

        return cartList.size();
    }



    public class CartViewHolder extends RecyclerView.ViewHolder {
        private TextView cartTitle,textView,textView1,textView2;
        private ImageView cartImageId;
        RelativeLayout relativeLayout;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            cartTitle = itemView.findViewById(R.id.cart_title);
            cartImageId = itemView.findViewById(R.id.cart_image);
            textView = itemView.findViewById(R.id.categorytxtview);
            textView1 = itemView.findViewById(R.id.brandtxtview);
            textView2 = itemView.findViewById(R.id.parttxtview);
            relativeLayout = itemView.findViewById(R.id.cartView);


        }

        @SuppressLint("ResourceType")
        public void bind(CartModel cart) {

//            cartTitle.setText("product");
//            cartImageId.setImageResource(1);
        }
    }
}
