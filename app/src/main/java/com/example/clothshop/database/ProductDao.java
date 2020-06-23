package com.example.clothshop.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.clothshop.model.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    void insert(Product product);

    @Update
    void update(Product product);

    @Delete
    void delete(Product product);

    @Query("SELECT * FROM product WHERE product_id=:id")
    LiveData<Product> getProductById(int id);

    @Query("SELECT * FROM PRODUCT")
    LiveData<List<Product>> getAllProducts();


}
