package com.example.sparemate;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import com.example.sparemate.admin.AdminMainActivity;
import com.example.sparemate.api.EmpLoginRes;
import com.example.sparemate.api.ServerResponse;
import com.example.sparemate.employee.EmployeeMainActivity;
import com.example.sparemate.users.UserMainActivity;
import com.example.sparemate.users.UserSignupActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {


    EditText emailETxet,passwordEtext;

    AppCompatButton loginBtn;
    AppCompatButton signup_Btn;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.login_button);
        signup_Btn = findViewById(R.id.signupRedirectText);
        emailETxet = findViewById(R.id.user_edittext);
        passwordEtext =findViewById(R.id.password_edittext);

        // Retrieve the loginBy extra from the intent
        Intent intent = getIntent();

        final String loginBy = intent.getStringExtra("loginBy");
        if(loginBy.equals("user")){
        signup_Btn.setVisibility(View.VISIBLE);

        }
        signup_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch signup activity
                Intent intent = new Intent(LoginActivity.this, UserSignupActivity.class);
                startActivity(intent);
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email,password;

                email=emailETxet.getText().toString();
                password =passwordEtext.getText().toString();

                validation();

                if(email!=null &&  password !=null) {
                    //     server respone from api

                    Call<EmpLoginRes> employee = RestClient.makeApi().employeePro(email,password,"Android");
                    employee.enqueue(new Callback<EmpLoginRes>() {
                        @Override
                        public void onResponse(Call<EmpLoginRes> call, Response<EmpLoginRes> response) {

                            EmpLoginRes empLoginRes = response.body();
                            if (response.isSuccessful() && empLoginRes.getStatus().equals("200")){
                                if (loginBy.equals("emp")) {
                                    // Launch employee main activity
                                    id = Integer.parseInt(response.body().getEmployee_id());
                                    signup_Btn.setVisibility(View.GONE);
                                    Toast.makeText(LoginActivity.this, "employee loged in", Toast.LENGTH_SHORT).show();
                                    SharedPreferences sf = getSharedPreferences("empsf", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sf.edit();
                                    editor.putString("empid",response.body().getEmployee_id());
                                    editor.putString("empname",response.body().getEmployee_name());
                                    editor.putString("empcontact",response.body().getContact());
                                    editor.putString("emptype",response.body().getType());
                                    editor.apply();
                                    Intent intent = new Intent(LoginActivity.this, EmployeeMainActivity.class);
                                    startActivity(intent);

                                }

                            }

                        }

                        @Override
                        public void onFailure(Call<EmpLoginRes> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "Failed to connect", Toast.LENGTH_SHORT).show();

                        }
                    });
                    Call<ServerResponse> sr = RestClient.makeApi().UserProfile(email, password, "Android");
                    sr.enqueue(new Callback<ServerResponse>() {
                        @Override
                        public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                            if (response.isSuccessful()) {

                                if (response.body().getStatus() == 200) {
                                    SharedPreferences sf = getSharedPreferences("sfname", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sf.edit();
                                     editor.putString("id",response.body().getUser_id() );
                                     editor.putString("username",response.body().getUsername());
                                     editor.putString("email",response.body().getEmail());
                                     editor.putString("password",password);
                                    editor.putString("address",response.body().getAddress() );
                                    editor.putString("city",response.body().getCity() );
                                    editor.putString("state",response.body().getState() );
                                    editor.putString("zipcode",response.body().getZipcode() );
                                    editor.putString("contact_no",response.body().getContact_no() );
                                    editor.putString("product_id",response.body().getProduct_id());
                                     editor.apply();
                                    // Check the loginBy value and launch the appropriate activity
                                    if (loginBy != null) {

                                        if (loginBy.equals("user")) {
                                            // Launch user main activity
                                            id = Integer.parseInt(response.body().getId());
                                            Toast.makeText(LoginActivity.this, "User loged in", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(LoginActivity.this, UserMainActivity.class);
                                            startActivity(intent);

                                        }  else if (loginBy.equals("admin")) {
                                            if(response.body().getMessage().equals("Admin Login Successfully")){
                                            // Launch admin main activity
                                            Toast.makeText(LoginActivity.this, "admin loged in", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(LoginActivity.this, AdminMainActivity.class);
                                            startActivity(intent);
                                            }
                                        }
                                    }
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<ServerResponse> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "check connection", Toast.LENGTH_SHORT).show();

                        }
                    });

                }

            }

            private void validation() {
                String email,password;
                email=emailETxet.getText().toString();
                password=passwordEtext.getText().toString();
                if(email.isEmpty() && password.isEmpty())
                {
                    emailETxet.setError("can't be empty");
                            passwordEtext.setError("can't be empty");
                } else {
                    if (!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
                        emailETxet.setError("enter valid email");
                    }

                    if (password.isEmpty()) {
                        passwordEtext.setError("required");
                    }
                }
            }
        });


    }
}
