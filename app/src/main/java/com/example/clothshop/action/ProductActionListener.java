package com.example.clothshop.action;

import com.example.clothshop.model.Product;

public interface ProductActionListener {
    void onProductClick(Product product);
    void onDislikeClick(int position);
}
