package com.cloud.design.model;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.cloud.design.holder.ItemHolderFactory;
import com.google.gson.annotations.SerializedName;

import java.net.URL;

public class ItemSmall implements Item {


    @SerializedName("image")
    private String mImageUrl;

    @SerializedName("title")
    private String mTitle;

    public ItemSmall(String imageUrl, String title) {
        mImageUrl = imageUrl;
        mTitle = title;
    }

    @BindingAdapter("app:url")
    public static void setImageUrl(ImageView imageView, String imageUrl) {
        new Thread(() -> {
            try {
                URL url = new URL(imageUrl);
                Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
                imageView.post(() -> imageView.setImageBitmap(bitmap));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    @Override
    public int getType() {
        return ItemHolderFactory.ITEM_SMALL;
    }
}
