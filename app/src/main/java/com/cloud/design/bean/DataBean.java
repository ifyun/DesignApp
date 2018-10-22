package com.cloud.design.bean;

import com.cloud.design.model.ItemLarge;
import com.cloud.design.model.ItemSmall;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataBean {

    @SerializedName("hot_new")
    private List<ItemLarge> mHotNewList;

    @SerializedName("top_rated")
    private List<ItemLarge> mTopRatedList;

    @SerializedName("recommended")
    private List<ItemSmall> mRecommendedList;

    public List<ItemLarge> getHotNewList() {
        return mHotNewList;
    }

    public void setHotNewList(List<ItemLarge> hotNewList) {
        mHotNewList = hotNewList;
    }

    public List<ItemLarge> getTopRatedList() {
        return mTopRatedList;
    }

    public void setTopRatedList(List<ItemLarge> topRatedList) {
        mTopRatedList = topRatedList;
    }

    public List<ItemSmall> getRecommendedList() {
        return mRecommendedList;
    }

    public void setRecommendedList(List<ItemSmall> recommendedList) {
        mRecommendedList = recommendedList;
    }
}
