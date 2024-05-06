package com.example.sparemate.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.example.sparemate.Others.OrderModel;
import com.example.sparemate.Others.RecyclerData;
import com.example.sparemate.R;
import com.example.sparemate.users.OrderActivity;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<OrderModel> orderList;

    private Context mcontext;
    public OrderAdapter(List<OrderModel> orderList, Context mcontext) {
        this.orderList = orderList;
        this.mcontext = mcontext;

    }

    public OrderAdapter(List<OrderModel> orderList) {
        this.orderList = orderList; // Assign the parameter to the class variable
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_layout, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderModel address = orderList.get(position);

       holder.deliveryDate.setText(address.getDate());
       holder.orderName.setText(address.getProduct_name());
        com.bumptech.glide.Glide.with(mcontext).load(address.image_name).apply(new RequestOptions().placeholder(R.drawable.placeholdererr) // Placeholder image
                        .error(R.drawable.baseline_wallpaper_24)) // Error image in case of loading failure
                .into(holder.orderImage);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        private TextView deliveryDate;
        private TextView orderName;
        private RatingBar orderRating;
        private ImageView orderImage;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            deliveryDate = itemView.findViewById(R.id.delivery_date);
            orderName = itemView.findViewById(R.id.order_name);
            orderRating = itemView.findViewById(R.id.order_rating);
            orderImage = itemView.findViewById(R.id.order_image);
        }

        public void bind(OrderModel order) {
//            deliveryDate.setText(order.getDeliverDate());
//            orderName.setText(order.getOrder_name());
//            orderRating.setRating(order.getRating());
//            orderImage.setImageResource(order.getImageResource());
        }
    }
}
