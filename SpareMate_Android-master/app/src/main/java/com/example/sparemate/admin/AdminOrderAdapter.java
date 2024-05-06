package com.example.sparemate.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.example.sparemate.R;
import com.example.sparemate.RestClient;
import com.example.sparemate.api.AddProductResponse;
import com.example.sparemate.api.AdminOrderRes;
import com.example.sparemate.api.EmpOrderStatusRes;
import com.example.sparemate.api.EmployeeList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminOrderAdapter extends RecyclerView.Adapter<AdminOrderAdapter.ViewHolder>{
    private final Context mcontext;
    private ArrayList<AdminOrderRes.Datum> dataSet;

    String[] s;
    String string;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView orderid,username,paymentid,amount,productid;
        Button assign;

        Spinner autocompleteTV;



        public ViewHolder(View v) {
            super(v);
           orderid = v.findViewById(R.id.orderId);
            username = v.findViewById(R.id.userName);
            paymentid = v.findViewById(R.id.paymentId);
            amount = v.findViewById(R.id.amount);
            productid = v.findViewById(R.id.productId);
            autocompleteTV = v.findViewById(R.id.autoCompleteTextView);
            assign = v.findViewById(R.id.assign);

//            textView = v.findViewById(R.id.textView);

        }
    }

    public AdminOrderAdapter(ArrayList<AdminOrderRes.Datum> dataSet, Context mcontext) {
        this.dataSet = dataSet;
        this.mcontext = mcontext;
    }

    @Override
    public AdminOrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleradminview_item, parent, false);
        return new AdminOrderAdapter.ViewHolder(v);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        AdminOrderRes.Datum d = dataSet.get(position);
        Toast.makeText(mcontext,d.getPayment_id() , Toast.LENGTH_SHORT).show();
        holder.productid.setText(d.getProduct_id());
        holder.orderid.setText(d.getId());
        holder.paymentid.setText(d.getPayment_id());
        holder.username.setText(d.getUsername());
       holder.amount.setText(d.getAmount());

        Call<EmployeeList> employeeListCall = RestClient.makeApi().empList();
        employeeListCall.enqueue(new Callback<EmployeeList>() {
            @Override
            public void onResponse(Call<EmployeeList> call, Response<EmployeeList> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus()== 200){
                        s=new String[response.body().getData().size()];
                        for(int i=0;i<response.body().getData().size();i++){
                            EmployeeList.Datum d = response.body().getData().get(i);
                            s[i]=d.getName();
                        }
                        try{
                            ArrayAdapter<String> adapterItem = new ArrayAdapter<>(mcontext, R.layout.dropdown_item, s);
                            holder.autocompleteTV.setAdapter(adapterItem);
                            holder.autocompleteTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    string = s[position]; // Update the selected string
                                    Toast.makeText(mcontext, s[position], Toast.LENGTH_SHORT).show(); // Show toast when item selected
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    // Handle case where nothing is selected
                                }
                            });
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<EmployeeList> call, Throwable t) {

            }
        });

        holder.assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<AddProductResponse> addProductResponseCall = RestClient.makeApi().assign(d.getId(),string);
                addProductResponseCall.enqueue(new Callback<AddProductResponse>() {
                    @Override
                    public void onResponse(Call<AddProductResponse> call, Response<AddProductResponse> response) {
                        if (response.isSuccessful()){
                            if(response.body().getStatus().equals("200")){
                                Toast.makeText(mcontext, "employee assigned", Toast.LENGTH_SHORT).show();
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
