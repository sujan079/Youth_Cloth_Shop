package com.example.clothshop.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothshop.R;
import com.example.clothshop.ViewModel.MainActivityViewModel;
import com.example.clothshop.action.ProductActionListener;
import com.example.clothshop.adapter.ProductItemListAdapter;
import com.example.clothshop.databinding.FragmentHomeBinding;
import com.example.clothshop.model.CartItem;
import com.example.clothshop.model.Product;

import java.util.List;


public class HomeFragment extends Fragment implements ProductActionListener {

    private ProductItemListAdapter productItemListAdapter;
    private RecyclerView.LayoutManager productLayoutManager;

    private FragmentHomeBinding fragmentHomeBinding;

    private MainActivityViewModel mainActivityViewModel;


    public HomeFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return fragmentHomeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainActivityViewModel = new ViewModelProvider(getActivity(),
                new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()))
                .get(MainActivityViewModel.class);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initProductView();
    }

    private void initProductView() {


        productItemListAdapter = new ProductItemListAdapter(this);

        productLayoutManager = new LinearLayoutManager(getContext());

        fragmentHomeBinding.rvProduct.setAdapter(productItemListAdapter);
        fragmentHomeBinding.rvProduct.setLayoutManager(productLayoutManager);
        fragmentHomeBinding.rvProduct.setHasFixedSize(true);
        fragmentHomeBinding.rvProduct.setNestedScrollingEnabled(false);

        fragmentHomeBinding.rvProduct.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        mainActivityViewModel.productArrayList.observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                productItemListAdapter.setProductArrayList(products);
                productItemListAdapter.notifyDataSetChanged();
            }
        });

        mainActivityViewModel.cartItemsArrayList.observe(getActivity(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                fragmentHomeBinding.tvNumberOfItems.setText(String.valueOf(cartItems.size()) + " Items");


            }
        });

        //next button

        fragmentHomeBinding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });
    }


    private void next() {
        productItemListAdapter.removeItemAt(0);
    }

    @Override
    public void onProductClick(Product product) {
        mainActivityViewModel.insert(new CartItem(product, 1));
    }

    @Override
    public void onDislikeClick(int position) {
        next();
    }


}