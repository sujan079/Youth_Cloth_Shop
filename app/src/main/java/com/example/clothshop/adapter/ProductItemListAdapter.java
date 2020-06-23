package com.example.clothshop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothshop.R;
import com.example.clothshop.action.ProductActionListener;
import com.example.clothshop.databinding.ProductItemListBinding;
import com.example.clothshop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductItemListAdapter extends RecyclerView.Adapter<ProductItemListAdapter.ProductItemListViewBindingHolder> {

    private List<Product> productArrayList;
    private ProductActionListener productActionListener;


    public ProductItemListAdapter(ProductActionListener productActionListener) {
        productArrayList = new ArrayList<>();
        this.productActionListener = productActionListener;
    }

    public void setProductArrayList(List<Product> productArrayList) {
        this.productArrayList = productArrayList;
    }

    public void removeItemAt(int position) {
        if (productArrayList.isEmpty())
            return;
        productArrayList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, productArrayList.size());

    }

    @NonNull
    @Override
    public ProductItemListViewBindingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductItemListBinding productItemListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.product_item_list,
                parent, false);
        return new ProductItemListViewBindingHolder(productItemListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductItemListViewBindingHolder holder, int position) {

        holder.productItemListBinding.setProduct(productArrayList.get(position));

        for (int i = 0; i < productArrayList.size(); i++) {
            if (position == 0)
                holder.productItemListBinding.overlay.setVisibility(View.INVISIBLE);
            else
                holder.productItemListBinding.overlay.setVisibility(View.VISIBLE);

        }

    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    class ProductItemListViewBindingHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ProductItemListBinding productItemListBinding;

        public ProductItemListViewBindingHolder(@NonNull ProductItemListBinding itemView) {
            super(itemView.getRoot());
            productItemListBinding = itemView;
            productItemListBinding.btnAddToBag.setOnClickListener(this);
            productItemListBinding.btnDontLike.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_add_to_bag:
                    productActionListener.onProductClick(productArrayList.get(getAdapterPosition()));
                    break;
                case R.id.btn_dont_like:
                    productActionListener.onDislikeClick(getAdapterPosition());
                    break;
            }
        }
    }
}
