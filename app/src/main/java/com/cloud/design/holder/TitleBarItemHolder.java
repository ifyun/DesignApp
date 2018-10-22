package com.cloud.design.holder;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.cloud.design.BR;
import com.cloud.design.databinding.ItemTitleBarBinding;
import com.cloud.design.model.Item;
import com.cloud.design.model.ItemTitleBar;
import com.cloud.design.viewmodel.ItemTitleBarVM;

/**
 * ViewHolder of title bar
 */
public class TitleBarItemHolder extends ItemHolder {

    private ItemTitleBarBinding mBinding;

    public TitleBarItemHolder(View view) {
        super(view);
        mBinding = DataBindingUtil.bind(view);
    }

    @Override
    public void setBinding(Item itemData) {
        ItemTitleBar item = (ItemTitleBar) itemData;
        mBinding.setVariable(BR.itemTitleBar, item);
        mBinding.setVariable(BR.itemTitleBarVM, new ItemTitleBarVM());
        mBinding.executePendingBindings();
    }
}
