package com.example.sparemate.admin;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.sparemate.R;
import com.example.sparemate.RestClient;
import com.example.sparemate.api.AddEmployeeResponse;
import com.example.sparemate.api.ServerResponse;
import com.example.sparemate.users.EditProfileActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEmployeeActivity extends AppCompatActivity {

    EditText editText,editText1,editText2,editText3,editText4;

    AppCompatButton addBtn;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        editText = findViewById(R.id.nameed);
        editText1 = findViewById(R.id.emailed);
        editText2 = findViewById(R.id.contacted);
        editText3 = findViewById(R.id.passworded);
        editText4 = findViewById(R.id.typeed);
        addBtn = findViewById(R.id.start_button);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<AddEmployeeResponse> sr = RestClient.makeApi().addEmployee(editText.getText().toString(),editText1.getText().toString(),
                        editText2.getText().toString(),editText3.getText().toString(),editText4.getText().toString());
                sr.enqueue(new Callback<AddEmployeeResponse>() {
                    @Override
                    public void onResponse(Call<AddEmployeeResponse> call, Response<AddEmployeeResponse> response) {

                        if(response.isSuccessful()){
                            if(response.body().getStatus().equals("200")){
                                Toast.makeText(AddEmployeeActivity.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            if(response.body().getStatus().equals("500")){
                                Toast.makeText(AddEmployeeActivity.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<AddEmployeeResponse> call, Throwable t) {

                    }
                });

            }
        });

    }
}
