package com.kong.frameapp.bean;

import com.google.gson.annotations.SerializedName;
import com.kong.framesamples.modules.wynews.NewsConstants;

import java.util.ArrayList;


public class TopNewsList {


    public ArrayList<NewsBean> getNewsList() {
        return newsList;
    }

    public void setNewsList(ArrayList<NewsBean> newsList) {
        this.newsList = newsList;
    }
    //T1348647909107 T1348647909107
    @SerializedName("T1348647909107")
    private ArrayList<NewsBean> newsList;





}
