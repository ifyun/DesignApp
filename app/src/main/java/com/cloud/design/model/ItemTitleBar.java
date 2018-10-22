package com.cloud.design.model;

import android.support.annotation.NonNull;

import com.cloud.design.holder.ItemHolderFactory;

public class ItemTitleBar implements Item {

    private String mTitle;

    public ItemTitleBar(@NonNull String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    @Override
    public int getType() {
        return ItemHolderFactory.ITEM_TITLE_BAR;
    }
}
