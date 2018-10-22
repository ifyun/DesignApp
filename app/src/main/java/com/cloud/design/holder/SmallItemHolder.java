package com.cloud.design.holder;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.cloud.design.BR;
import com.cloud.design.databinding.ItemSmallBinding;
import com.cloud.design.model.Item;
import com.cloud.design.model.ItemSmall;

public class SmallItemHolder extends ItemHolder {

    private ItemSmallBinding mBinding;

    public SmallItemHolder(View item) {
        super(item);
        mBinding = DataBindingUtil.bind(item);
    }

    @Override
    public void setBinding(Item itemData) {
        ItemSmall item = (ItemSmall) itemData;
        mBinding.setVariable(BR.itemSmall, item);
        mBinding.executePendingBindings();
    }
}
