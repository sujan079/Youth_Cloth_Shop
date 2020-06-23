package com.example.clothshop.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothshop.R;
import com.example.clothshop.databinding.CartItemListBinding;
import com.example.clothshop.model.CartItem;

import java.util.ArrayList;
import java.util.List;

public class CartItemListAdapter extends RecyclerView.Adapter<CartItemListAdapter.CartItemViewHolderBinding> {


    private List<CartItem> cartItems;

    public CartItemListAdapter() {
        cartItems = new ArrayList<>();
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartItemViewHolderBinding onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CartItemListBinding cartItemListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.cart_item_list, parent, false);
        return new CartItemViewHolderBinding(cartItemListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolderBinding holder, int position) {
        holder.cartItemViewHolderBinding.setCartItem(cartItems.get(position));
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    class CartItemViewHolderBinding extends RecyclerView.ViewHolder {

        private CartItemListBinding cartItemViewHolderBinding;

        public CartItemViewHolderBinding(@NonNull CartItemListBinding itemView) {
            super(itemView.getRoot());
            cartItemViewHolderBinding = itemView;
        }
    }
}
