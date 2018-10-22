package com.cloud.design;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.cloud.design.holder.ItemHolder;
import com.cloud.design.holder.ItemHolderFactory;
import com.cloud.design.model.Item;

import java.util.List;

public class MultiListAdapter extends RecyclerView.Adapter<ItemHolder> {

    private List<Item> mDataList;

    public MultiListAdapter(List<Item> dataList) {
        mDataList = dataList;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        //Get ViewHolder by the type of item
        return ItemHolderFactory.getItemHolder(viewGroup, type);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder viewHolder, int i) {
        //Set Holder's Binding
        viewHolder.setBinding(mDataList.get(i));
    }

    @Override
    public int getItemViewType(int position) {
        //Get the type of item
        return mDataList.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void setSpanCount(GridLayoutManager layoutManager) {
        //Set span count of the item by different type
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                int type = getItemViewType(i);
                switch (type) {
                    default:
                    case ItemHolderFactory.ITEM_LARGE:
                        return 3;
                    case ItemHolderFactory.ITEM_SMALL:
                        return 2;
                    case ItemHolderFactory.ITEM_TITLE_BAR:
                        return 6;
                }
            }
        });
    }
}
