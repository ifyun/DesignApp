package com.cloud.design.fragment;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cloud.design.bean.DataBean;
import com.cloud.design.databinding.FragmentRecycleBinding;
import com.cloud.design.model.Item;
import com.cloud.design.MultiListAdapter;
import com.cloud.design.R;
import com.cloud.design.model.ItemTitleBar;
import com.google.gson.Gson;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RecycleFragment extends Fragment {

    private List<Item> mItemList = new ArrayList<>();
    private FragmentRecycleBinding mBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_recycle, container, false);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 6);
        MultiListAdapter adapter = new MultiListAdapter(mItemList);
        adapter.setSpanCount(layoutManager);
        RecyclerView recyclerView = mBinding.recycleView;
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return mBinding.getRoot();
    }

    private void initData() {
        String dataSource = null;
        try {
            InputStream inputStream = getResources().getAssets().open("data_source.json");
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            dataSource = new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Add data
        if (dataSource != null) {
            Gson gson = new Gson();
            DataBean dataBean = gson.fromJson(dataSource, DataBean.class);
            mItemList.add(new ItemTitleBar("Hot New"));
            mItemList.addAll(dataBean.getHotNewList());
            mItemList.add(new ItemTitleBar("Recommended"));
            mItemList.addAll(dataBean.getRecommendedList());
            mItemList.add(new ItemTitleBar("Top Rated"));
            mItemList.addAll(dataBean.getTopRatedList());
        }
    }
}
