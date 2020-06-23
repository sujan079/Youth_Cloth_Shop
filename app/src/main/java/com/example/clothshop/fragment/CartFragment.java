package com.example.clothshop.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothshop.R;
import com.example.clothshop.ViewModel.MainActivityViewModel;
import com.example.clothshop.adapter.CartItemListAdapter;
import com.example.clothshop.databinding.FragmentCartBinding;
import com.example.clothshop.model.CartItem;

import java.util.List;


public class CartFragment extends Fragment {

    private CartItemListAdapter cartItemListAdapter;
    private RecyclerView.LayoutManager cartLayoutManager;

    private FragmentCartBinding fragmentCartBinding;

    private MainActivityViewModel mainActivityViewModel;


    public CartFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentCartBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false);
        return fragmentCartBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainActivityViewModel = new ViewModelProvider(getActivity(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(MainActivityViewModel.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initCartView();
    }

    private void initCartView() {

        cartItemListAdapter = new CartItemListAdapter();
        cartLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        fragmentCartBinding.rvCartItems.setAdapter(cartItemListAdapter);
        fragmentCartBinding.rvCartItems.setLayoutManager(cartLayoutManager);
        fragmentCartBinding.rvCartItems.setHasFixedSize(true);


        mainActivityViewModel.cartItemsArrayList.observe(getActivity(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                fragmentCartBinding.tvNumberOfItems.setText(String.valueOf(cartItems.size()) + " Items");

                cartItemListAdapter.setCartItems(cartItems);
                mainActivityViewModel.findTotal();

                cartItemListAdapter.notifyDataSetChanged();
            }
        });

        ItemTouchHelper deleteTouchHelper = new ItemTouchHelper(deleteCallback);
        deleteTouchHelper.attachToRecyclerView(fragmentCartBinding.rvCartItems);

        initTotal();

    }

    private void initTotal() {

        mainActivityViewModel.total.observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                fragmentCartBinding.tvTotal.setText("$" + String.valueOf(integer));
            }
        });
    }


    private ItemTouchHelper.SimpleCallback deleteCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.UP) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            cartItemListAdapter.notifyItemChanged(position);
            List<CartItem> cartItems = mainActivityViewModel.getCartItem();

            CartItem cartItem = cartItems.get(position);
            mainActivityViewModel.delete(cartItem);
        }
    };
}