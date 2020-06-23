package com.example.clothshop.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "product")
public class Product {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "product_id")
    private int id;

    private String product_name;
    private double product_price;
    private String product_img_url;
    private String size;
    private String product_detail;


    public Product() {
    }

    @Ignore
    public Product(String product_name, double product_price, String product_img_url) {
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_img_url = product_img_url;
        this.product_detail = "";
        this.size = "";

    }

    @Ignore
    public Product(String product_name, double product_price, String product_img_url, String size, String product_detail) {
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_img_url = product_img_url;
        this.size = size;
        this.product_detail = product_detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    public String getProduct_img_url() {
        return product_img_url;
    }

    public void setProduct_img_url(String product_img_url) {
        this.product_img_url = product_img_url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getProduct_detail() {
        return product_detail;
    }

    public void setProduct_detail(String product_detail) {
        this.product_detail = product_detail;
    }


    public String getPriceInString(Double price) {
        return "$" + String.valueOf(price);
    }


}
