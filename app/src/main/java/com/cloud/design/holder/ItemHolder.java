package com.cloud.design.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cloud.design.model.Item;

public abstract class ItemHolder extends RecyclerView.ViewHolder {
    ItemHolder(View item) {
        super(item);
    }

    public abstract void setBinding(Item itemData);
}
