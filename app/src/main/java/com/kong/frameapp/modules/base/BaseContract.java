package com.kong.frameapp.modules.base;


import com.kong.fmklibrary.mvp.IModel;
import com.kong.fmklibrary.mvp.IPresenter;
import com.kong.fmklibrary.mvp.IView;
import com.kong.frameapp.net.ResultCallBack;

import java.util.HashMap;


public interface BaseContract {


    /**
     * V视图层
     */
    interface View extends IView {
        // void setAdapter(DefaultAdapter adapter);

        //refresh
        void refreshData(boolean isRefresh);

        //refresh succeed
        void refreshSuc(Object dataObj);

        //refresh fail
        void refreshFail(String errMsg);

    }


    /**
     * P视图与逻辑处理的连接层
     */
    interface Presenter extends IPresenter {

        void getData(boolean isRefresh);

    }


    /**
     * M逻辑(业务)处理层
     */
    interface Model extends IModel {

        void requestData(boolean isRefresh, HashMap<String, String> params, ResultCallBack callBack);

    }
}
