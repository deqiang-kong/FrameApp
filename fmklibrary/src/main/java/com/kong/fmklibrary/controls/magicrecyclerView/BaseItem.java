package com.kong.fmklibrary.controls.magicrecyclerView;

import java.io.Serializable;

/**
 * Created by PandaQ on 2016/11/16.
 * email : 767807368@qq.com
 */

public class BaseItem<T> implements Serializable {

    //数据类型
    private int mItemType;
    //实际使用的数据
    private T data;

    public int getItemType() {
        return mItemType ;
    }

    public void setItemType(int itemType) {
        mItemType = itemType;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
