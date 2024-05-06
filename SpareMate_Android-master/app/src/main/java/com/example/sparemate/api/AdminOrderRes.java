package com.example.sparemate.api;

import java.util.ArrayList;

public class AdminOrderRes {
    public static class Datum{
        public Datum(String id, String date, String image_name, String parts, String product_name, String username,
                     String status, String contact_no, String payment_id, String product_id, String amount, String assign) {
            this.id = id;
            this.date = date;
            this.image_name = image_name;
            this.parts = parts;
            this.product_name = product_name;
            this.username = username;
            this.status = status;
            this.contact_no = contact_no;
            this.payment_id = payment_id;
            this.product_id = product_id;
            this.amount = amount;
            this.assign = assign;
        }

        public String id;
        public String date;



        public String category;
        public String brand;
        public String image_name;
        public String parts;
        public String product_name;
        public String username;
        public String status;

        public String contact_no;

        public String getPayment_id() {
            return payment_id;
        }

        public void setPayment_id(String payment_id) {
            this.payment_id = payment_id;
        }

        public String payment_id;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getImage_name() {
            return image_name;
        }

        public void setImage_name(String image_name) {
            this.image_name = image_name;
        }

        public String getParts() {
            return parts;
        }

        public void setParts(String parts) {
            this.parts = parts;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getContact_no() {
            return contact_no;
        }

        public void setContact_no(String contact_no) {
            this.contact_no = contact_no;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String product_id;
        public String amount;

        public String getAssign() {
            return assign;
        }

        public void setAssign(String assign) {
            this.assign = assign;
        }

        public String assign;


    }

    public int status;
    public String message;
    public ArrayList<AdminOrderRes.Datum> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<AdminOrderRes.Datum> getData() {
        return data;
    }

    public void setData(ArrayList<AdminOrderRes.Datum> data) {
        this.data = data;
    }
}
