package com.cloud.design.holder;

import android.support.annotation.IntDef;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cloud.design.R;

public class ItemHolderFactory {

    public static final int ITEM_LARGE = 0;
    public static final int ITEM_SMALL = 1;
    public static final int ITEM_TITLE_BAR = 2;

    @IntDef(value = {
            ITEM_LARGE,
            ITEM_SMALL,
            ITEM_TITLE_BAR
    })
    @interface ItemType {}

    public static ItemHolder getItemHolder(ViewGroup parent, @ItemType int type) {
        switch (type) {
            default:
            case ITEM_LARGE:
                return new LargeItemHolder(LayoutInflater
                        .from(parent.getContext()).inflate(R.layout.item_large, parent, false));
            case ITEM_SMALL:
                return new SmallItemHolder(LayoutInflater
                        .from(parent.getContext()).inflate(R.layout.item_small, parent, false));
            case ITEM_TITLE_BAR:
                return new TitleBarItemHolder(LayoutInflater
                        .from(parent.getContext()).inflate(R.layout.item_title_bar, parent, false));
        }
    }
}
