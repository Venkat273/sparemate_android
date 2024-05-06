package com.example.sparemate.Others;

public class CartModel {
    public String id;
    public String category;
    public String brand;
    public String image_name;
    public String parts;
    public String product_name;
    public String price;

    public CartModel(String id, String category, String brand, String image_name, String parts, String product_name, String price) {
        this.id = id;
        this.category = category;
        this.brand = brand;
        this.image_name = image_name;
        this.parts = parts;
        this.product_name = product_name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
