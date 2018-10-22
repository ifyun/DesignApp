package com.cloud.design.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cloud.design.bean.DataBean;
import com.cloud.design.databinding.ActivityListBinding;
import com.cloud.design.model.Item;
import com.cloud.design.MultiListAdapter;
import com.cloud.design.R;
import com.cloud.design.model.ItemTitleBar;
import com.google.gson.Gson;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private List<Item> mItemList = new ArrayList<>();
    private ActivityListBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_list);

        initData();

        GridLayoutManager layoutManager = new GridLayoutManager(this, 6);
        MultiListAdapter adapter = new MultiListAdapter(mItemList);
        adapter.setSpanCount(layoutManager);

        RecyclerView recyclerView = mBinding.recycleView;
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
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
        //添加数据
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
