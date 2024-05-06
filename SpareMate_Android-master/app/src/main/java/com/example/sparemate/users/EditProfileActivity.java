package com.example.sparemate.users;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.sparemate.ProfileActivity;
import com.example.sparemate.R;
import com.example.sparemate.RestClient;
import com.example.sparemate.api.ProfileResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    AppCompatButton savebtn;

    EditText editText,editText1,editText2;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editText = findViewById(R.id.usename);
         editText1 = findViewById(R.id.email);
         editText2 = findViewById(R.id.phone);

        SharedPreferences sf = getSharedPreferences("sfname",MODE_PRIVATE);
        String user_id = sf.getString("id",null);

            findViewById(R.id.saveBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username =editText.getText().toString();
                    String email=editText1.getText().toString();
                    String contact_no =editText2.getText().toString();
                    Call<ProfileResponse> profileResponseCall = RestClient.makeApi().profileUpdate(user_id,username,email,contact_no);

                    profileResponseCall.enqueue(new Callback<ProfileResponse>() {
                        @Override
                        public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                            if (response.isSuccessful()) {

                                if(response.body().getStatus().equals("200")) {
                                    Toast.makeText(EditProfileActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();

                                    SharedPreferences.Editor editor = sf.edit();
                                    editor.putString("username", username);
                                    editor.putString("email", email);
                                    editor.putString("contact_no", contact_no);
                                    editor.apply();

                                    Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();

                                }

                                if(response.body().getStatus().equals("500")){
                                    Toast.makeText(EditProfileActivity.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ProfileResponse> call, Throwable t) {

                            Toast.makeText(EditProfileActivity.this, "Connection error", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            });

    }
}