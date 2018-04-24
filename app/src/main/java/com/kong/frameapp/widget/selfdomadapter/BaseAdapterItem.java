package com.kong.frameapp.widget.selfdomadapter;

import java.io.Serializable;

/**
 *
 * Created by kongdq on 17/11/12.
 */
public class BaseAdapterItem<T> implements Serializable {

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

