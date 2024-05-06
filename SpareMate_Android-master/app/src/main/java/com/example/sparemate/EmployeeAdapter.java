package com.example.sparemate;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.example.sparemate.api.AddProductResponse;
import com.example.sparemate.api.EmpOrderStatusRes;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {
    private final Context mcontext;
    private ArrayList<EmpOrderStatusRes.Datum> dataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView clientName,contact,productname,partname,status;
        ImageView imageView;
        Button comBtn;


        public ViewHolder(View v) {
            super(v);
            clientName = v.findViewById(R.id.clientName);
            contact = v.findViewById(R.id.userContact);
            productname = v.findViewById(R.id.productName);
            partname = v.findViewById(R.id.partName);
            status = v.findViewById(R.id.status);
            comBtn = v.findViewById(R.id.cmplbtn);
            imageView = v.findViewById(R.id.order_image);
//            textView = v.findViewById(R.id.textView);

        }
    }

    public EmployeeAdapter(ArrayList<EmpOrderStatusRes.Datum> dataSet, Context mcontext) {
        this.dataSet = dataSet;
        this.mcontext = mcontext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.emprecyclerview_item, parent, false);
        return new ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       EmpOrderStatusRes.Datum d = dataSet.get(position);
        holder.productname.setText(d.getProduct_name());
        holder.clientName.setText(d.getUsername());
        holder.contact.setText(d.getContact_no());
        holder.status.setText(d.getStatus());
        holder.partname.setText(d.getParts());

        com.bumptech.glide.Glide.with(mcontext).load(d.image_name).apply(new RequestOptions().placeholder(R.drawable.placeholdererr) // Placeholder image
                        .error(R.drawable.baseline_wallpaper_24)) // Error image in case of loading failure
                .into(holder.imageView);

        holder.comBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<AddProductResponse> addProductResponseCall = RestClient.makeApi().emStatusUp(d.getId());
                addProductResponseCall.enqueue(new Callback<AddProductResponse>() {
                    @Override
                    public void onResponse(Call<AddProductResponse> call, Response<AddProductResponse> response) {
                        if(response.isSuccessful()){
                            if(response.body().getStatus().equals("200")){
                                Log.e("TAG", "onResponse: "+d.getId() );
                                Toast.makeText(mcontext, "order completed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AddProductResponse> call, Throwable t) {

                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
