package com.example.sparemate.users;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.sparemate.LoginActivity;
import com.example.sparemate.R;
import com.example.sparemate.RestClient;
import com.example.sparemate.api.ServerResponse;
import com.example.sparemate.api.ServerUserDataSend;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSignupActivity extends AppCompatActivity {

    AppCompatButton signupBtn;
    EditText editText,editText1,editText2,editText3;


    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupBtn = findViewById(R.id.signup_btn);
        editText=findViewById(R.id.username_edittext);
        editText1=findViewById(R.id.email_edittext);
        editText2=findViewById(R.id.password_edittext);
        editText3=findViewById(R.id.confirm_password_edittext);

        final String[] email = new String[1];
        final String[] user = new String[1];
        final String[] password = new String[1];
        final String[] confirmPass = new String[1];


        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email[0] =editText1.getText().toString();
                user[0] =editText.getText().toString();
                password[0] =editText2.getText().toString();
                confirmPass[0] =editText3.getText().toString();
                    validation();
                     Call<ServerUserDataSend> serverUserDataSendCall = RestClient.makeApi().userPutData(user[0], email[0], password[0], confirmPass[0]);
                serverUserDataSendCall.enqueue(new Callback<ServerUserDataSend>() {
                    @Override
                    public void onResponse(Call<ServerUserDataSend> call, Response<ServerUserDataSend> response) {

                        if(response.isSuccessful()){
                            if (response.body().getEmail()== email[0]){
                                Toast.makeText(UserSignupActivity.this,"User already exist",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(UserSignupActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }else {

                               Toast.makeText(UserSignupActivity.this,"user data added",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(UserSignupActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        } else {

                            Toast.makeText(UserSignupActivity.this,"user already exist",Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ServerUserDataSend> call, Throwable t) {
                        Toast.makeText(UserSignupActivity.this,"database error"+t.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

            }

            private void validation() {

                if(!user[0].isEmpty())
                {
                    editText.setError("can't be empty",getResources().getDrawable(R.drawable.sharp_error_24));
                }

                if(!email[0].matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"))
                {
                    editText1.setError("enter valid email",getResources().getDrawable(R.drawable.sharp_error_24));
                }

                if(!password[0].equals(confirmPass[0]))
                {
                    editText3.setError("mismatch",getResources().getDrawable(R.drawable.sharp_error_24));
                }

            }
        });


    }
}
