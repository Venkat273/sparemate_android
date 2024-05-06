package com.example.sparemate.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import com.example.sparemate.R;
import com.example.sparemate.RestClient;
import com.example.sparemate.api.AddProductResponse;
import java.io.File;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddProductActivity extends AppCompatActivity {

    AppCompatButton appCompatButton;
    private static final int PICK_FILE_REQUEST = 1;
    private EditText etFilePath,category,brand,parts,productname,price,discount,supplier;

    String categoryst,brandst,partsst,productnamest,pricest,discountst,supplierst;

    String fileName;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        etFilePath = findViewById(R.id.fileChoose);
        category = findViewById(R.id.category_text);
        brand = findViewById(R.id.brand_text);
        parts = findViewById(R.id.parts_text);
        productname = findViewById(R.id.product_name);
        price = findViewById(R.id.price_text);
        discount = findViewById(R.id.discount_text);
        supplier = findViewById(R.id.supplier_text);
        appCompatButton = findViewById(R.id.start_button);



        etFilePath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // starting activity on below line.
                startActivityForResult(intent, 1);


            }
        });
        appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryst = category.getText().toString();
                brandst = brand.getText().toString();
                partsst = parts.getText().toString();
                productnamest = productname.getText().toString();
                pricest = price.getText().toString();
                discountst = discount.getText().toString();
                supplierst = supplier.getText().toString();
                validation();
                if(body!=null)
                API(body);
            }

            private void validation() {
                if(etFilePath.getText().toString() == null){
                    etFilePath.setError("choose image");
                }
                if(categoryst.isEmpty())
                {
                    category.setError("can't be empty");
                }
                if(brandst.isEmpty()){
                    brand.setError("can't be empty");
                } if(partsst.isEmpty()){
                    parts.setError("can't be empty");
                } if(productnamest.isEmpty()){
                    productname.setError("can't be empty");
                } if(pricest.isEmpty()){
                    price.setError("can't be empty");
                } if(discountst.isEmpty()){
                    discount.setError("can't be empty");
                } if(supplierst.isEmpty()){
                    supplier.setError("can't be empty");
                }


            }
        });

    }


    public void API(MultipartBody.Part body){


        RequestBody categoryb = RequestBody.create(MediaType.parse("text/plain"), categoryst);
        RequestBody brandb = RequestBody.create(MediaType.parse("text/plain"), brandst);
        RequestBody partsb = RequestBody.create(MediaType.parse("text/plain"), partsst);
        RequestBody productnameb = RequestBody.create(MediaType.parse("text/plain"), productnamest);
        RequestBody priceb = RequestBody.create(MediaType.parse("text/plain"), pricest);
        RequestBody discountb = RequestBody.create(MediaType.parse("text/plain"), discountst);
        RequestBody supplierb = RequestBody.create(MediaType.parse("text/plain"), supplierst);



        Call<AddProductResponse> call = RestClient.makeApi().addProduct(categoryb,brandb,
                partsb,productnameb,body,priceb,discountb,supplierb);
        call.enqueue(new Callback<AddProductResponse>() {
            @Override
            public void onResponse(Call<AddProductResponse> call, Response<AddProductResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == "200") {
                        Toast.makeText(AddProductActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        category.setText("");
                        brand.setText("");
                         parts.setText("");
                         productname.setText("");
                        price.setText("");
                        discount.setText("");
                         supplier.setText("");
                        etFilePath.setText("");
//                                progress.setVisibility(View.GONE);
//                                tvImage.setImageBitmap(bitmap);

                    } else {
                        Toast.makeText(AddProductActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddProductActivity.this, "server busy " , Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<AddProductResponse> call, Throwable t) {
                Log.e("TAG", t.getMessage());
                Toast.makeText(AddProductActivity.this, "check your internet connection " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    MultipartBody.Part body;
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null)
        {


            android.net.Uri selectedImage = data.getData();
//            progress.setVisibility(View.VISIBLE);
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

//            Toast.makeText(getContext(), "file size "+filePathColumn.length , Toast.LENGTH_SHORT).show();
            android.database.Cursor cursor = this.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String filePath = cursor.getString(columnIndex);
                File file = new File(filePath);
//                cursor.close();
                RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
//                Log.e(TAG,"path "+file.getPath());
              body = MultipartBody.Part.createFormData("image_data", file.getName(), reqFile);
//                Log.e(TAG,"name "+file.getName());


                fileName = file.getName();
                etFilePath.setText(fileName);

            }
        }
    }





}
