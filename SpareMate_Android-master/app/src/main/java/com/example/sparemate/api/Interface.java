package com.example.sparemate.api;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Interface {

    @FormUrlEncoded
    @POST("login.php")
    Call<ServerResponse> UserProfile(@Field("email")String email, @Field("password")String password,
                                     @Field("platform")String platform);

    @FormUrlEncoded
    @POST("signup.php")
    Call<ServerUserDataSend> userPutData(@Field("username")String username, @Field("email")String email,
                                         @Field("password")String password,@Field("confirmpassword")String confirmpassword);

    @FormUrlEncoded
    @POST("bikespare.php")
    Call<ServerProductResponse> productPutData(@Field("category")String category, @Field("brand")String brand,
                                               @Field("parts")String type);

    @FormUrlEncoded
    @POST("address.php")
    Call<ServerAddressResponse> addressPutData(@Field("id")String id,@Field("address")String address,@Field("city")String city,
                                               @Field("state")String state,@Field("zipcode")String zipcode,@Field("contact_no")String contact_no);

    @FormUrlEncoded
    @POST("cart.php")
    Call<CartResponse> cartData(@Field("cartUser_id")String cartUser_id);


    @FormUrlEncoded
    @POST("profile.php")
    Call<ProfileResponse> profileUpdate(@Field("id")String id, @Field("username")String username,
                                        @Field("email")String email, @Field("contact_no")String contact_no);

    @FormUrlEncoded
    @POST("emp_register.php")
    Call<AddEmployeeResponse> addEmployee(@Field("name")String name,@Field("email")String email,
                                          @Field("password")String password,@Field("contact")String contact,@Field("type")String type);

    @Multipart
    @POST("addproduct.php")
    Call<AddProductResponse> addProduct(@Part("category") RequestBody category, @Part("brand")RequestBody brand,
                                        @Part("parts")RequestBody parts, @Part("product_name")RequestBody product_name,
                                        @Part MultipartBody.Part image_data, @Part("price")RequestBody price,
                                        @Part("discount")RequestBody discount, @Part("supplier")RequestBody supplier);

    @FormUrlEncoded
    @POST("login.php")
    Call<EmpLoginRes> employeePro(@Field("email")String email, @Field("password")String password,
                                     @Field("platform")String platform);

    @FormUrlEncoded
    @POST("order.php")
    Call<OrderResponse> orderSend(@Field("user_id")String user_id, @Field("payment_id")String payment_id,
                                         @Field("amount")String amount,@Field("product_id")String product_id,
                                  @Field("date")String date,@Field("time")String time,
                                  @Field("status")String status);

    @FormUrlEncoded
    @POST("order_details.php")
    Call<OrderDetailsRes> orderData(@Field("orderUser_id")String orderUser_id);

    @FormUrlEncoded
    @POST("add_to_cart.php")
    Call<AddProductResponse> addToCart(@Field("product_id")String product_id, @Field("user_id")String user_id,
                                         @Field("status")String status);

    @FormUrlEncoded
    @POST("employee_status.php")
    Call<EmpOrderStatusRes> empStatus(@Field("emp_name")String emp_name);

    @FormUrlEncoded
    @POST("employee_statusUp.php")
    Call<AddProductResponse> emStatusUp(@Field("id")String id);

    @POST("adminorders.php")
    Call<AdminOrderRes> adminOrder();

    @POST("employeelist.php")
    Call<EmployeeList> empList();

    @FormUrlEncoded
    @POST("assign.php")
    Call<AddProductResponse> assign(@Field("id")String id,@Field("assign")String assign);


}
