package com.example.clothshop.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.clothshop.model.CartItem;
import com.example.clothshop.model.Product;
import com.example.clothshop.repostitory.CartItemRepository;
import com.example.clothshop.repostitory.ProductRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    public LiveData<List<Product>> productArrayList;
    public LiveData<List<CartItem>> cartItemsArrayList;
    public MutableLiveData<Integer> total;

    private ProductRepository productRepository;
    private CartItemRepository cartItemRepository;


    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        productRepository = new ProductRepository(application);
        cartItemRepository = new CartItemRepository(application);


        total = new MutableLiveData<>();
        productArrayList = productRepository.getAllProducts();
        cartItemsArrayList = cartItemRepository.getCartItems();

    }

    public List<CartItem> getCartItem() {
        return cartItemsArrayList.getValue();
    }

    //Crud Operation on product
    public void getProducts() {
        productArrayList = productRepository.getAllProducts();
    }


    //Crud operation on cart
    public void getCartItems() {
        cartItemsArrayList = cartItemRepository.getCartItems();
    }

    public void insert(CartItem cartItem) {
        cartItemRepository.insert(cartItem);
        getCartItems();

    }

    public void delete(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
        getCartItems();
    }

    public void update(CartItem cartItem) {
        cartItemRepository.update(cartItem);
        getCartItems();
    }

    //find total of cart
    public void findTotal() {
        if (cartItemsArrayList == null)
            total.setValue(0);

        int temp_total = 0;
        for (CartItem cartItem : cartItemsArrayList.getValue()) {
            temp_total += cartItem.getProduct().getProduct_price() * cartItem.getQuantity();

        }
        total.setValue(temp_total);
    }


}
