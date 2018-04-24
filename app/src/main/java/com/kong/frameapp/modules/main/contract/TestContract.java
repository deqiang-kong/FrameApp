package com.kong.frameapp.modules.main.contract;


import com.kong.fmklibrary.mvp.IModel;
import com.kong.fmklibrary.mvp.IPresenter;
import com.kong.fmklibrary.mvp.IView;

import java.util.List;




public interface TestContract {


    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
       // void setAdapter(DefaultAdapter adapter);
        void startLoadMore();
        void endLoadMore();

    }


    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Presenter extends IPresenter {

        void getData(boolean isRefresh);
    }



    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {

        void getData(boolean isRefresh);
    }
}
