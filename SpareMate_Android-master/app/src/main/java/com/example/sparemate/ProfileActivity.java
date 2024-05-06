package com.example.sparemate;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.TextView;

import java.io.*;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.sparemate.api.ServerResponse;
import com.example.sparemate.users.BrandsActivity;
import com.example.sparemate.users.EditProfileActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends Fragment {


    TextView textView,textView1,textView2;
    public ProfileActivity(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        textView=view.findViewById(R.id.usename);
        textView1=view.findViewById(R.id.email);
        textView2=view.findViewById(R.id.phone);

        SharedPreferences sf = getActivity().getSharedPreferences("sfname", MODE_PRIVATE);
        String usernamesf=sf.getString("username",null);
        String passwordsf=sf.getString("password",null);
        String emailsf=sf.getString("email",null);
        String contactsf=sf.getString("contact_no",null);


        Call<ServerResponse> sr = RestClient.makeApi().UserProfile(emailsf, passwordsf, "Android");
        sr.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response.isSuccessful()) {
                    if(response.body().getStatus() == 200) {

                        textView.setText(response.body().getUsername());
                        textView1.setText(response.body().getEmail());
                        textView2.setText(response.body().getContact_no());
                    }
                }

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });






        AppCompatButton editButton = view.findViewById(R.id.edit_button);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the BrandsActivity when the Cars button is clicked
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
