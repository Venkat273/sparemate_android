package com.example.sparemate.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sparemate.Others.AddressModel;
import com.example.sparemate.R;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder> {

    private List<AddressModel> addressList;

    public AddressAdapter(List<AddressModel> addressList) {
        this.addressList = addressList;
    }

    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_layout, parent, false);
        return new AddressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder holder, int position) {
        AddressModel address = addressList.get(position);
        holder.bind(address);
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    public class AddressViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView fullAddressTextView;
        private TextView numberTextView;
        private RadioButton radioButton;

        public AddressViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name);
            fullAddressTextView = itemView.findViewById(R.id.fulladress);
            numberTextView = itemView.findViewById(R.id.number);
            radioButton = itemView.findViewById(R.id.address_radiobutton);
        }

        public void bind(AddressModel address) {
            nameTextView.setText(address.getName());
            fullAddressTextView.setText(address.getFullAddress());
            numberTextView.setText(address.getNumber());
            radioButton.setChecked(address.isSelected());
        }
    }
}
