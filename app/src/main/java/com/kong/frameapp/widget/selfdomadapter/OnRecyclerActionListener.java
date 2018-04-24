package com.kong.frameapp.widget.selfdomadapter;


/**
 * recycleView相关接口
 */
public interface OnRecyclerActionListener {


    void onItemClick(int position, BaseAdapterItem data);


    void onEmptyClick();


    void onLoadMore();
}