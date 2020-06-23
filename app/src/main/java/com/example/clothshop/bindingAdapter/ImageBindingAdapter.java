package com.example.clothshop.bindingAdapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.clothshop.R;

public class ImageBindingAdapter {

    @BindingAdapter("imageUrl")
    public static void setImage(ImageView view, String imgUrl) {
        Context context = view.getContext();

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);

        Glide.with(context)
                .setDefaultRequestOptions(options)
                .load(imgUrl)
                
                .into(view);
    }
}
