package com.cloud.design.holder;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.cloud.design.BR;
import com.cloud.design.databinding.ItemLargeBinding;
import com.cloud.design.model.Item;
import com.cloud.design.model.ItemLarge;
import com.cloud.design.viewmodel.ItemLargeVM;

public class LargeItemHolder extends ItemHolder {

    private ItemLargeBinding mBinding;

    public LargeItemHolder(View item) {
        super(item);
        mBinding = DataBindingUtil.bind(item);
    }

    @Override
    public void setBinding(Item itemData) {
        ItemLarge item = (ItemLarge) itemData;
        mBinding.setVariable(BR.itemLarge, item);
        mBinding.setVariable(BR.itemLargeVM, new ItemLargeVM());
        mBinding.executePendingBindings();
    }
}
