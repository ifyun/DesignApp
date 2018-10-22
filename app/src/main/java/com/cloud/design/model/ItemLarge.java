package com.cloud.design.model;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.cloud.design.holder.ItemHolderFactory;
import com.google.gson.annotations.SerializedName;

import java.net.URL;

public class ItemLarge implements Item {

    @SerializedName("image")
    private String mImageUrl;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("sub_title")
    private String mSubTitle;

    public ItemLarge(String imageUrl, String title, String subTitle) {
        mImageUrl = imageUrl;
        mTitle = title;
        mSubTitle = subTitle;
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

    public String getSubTitle() {
        return mSubTitle;
    }

    @Override
    public int getType() {
        return ItemHolderFactory.ITEM_LARGE;
    }
}
