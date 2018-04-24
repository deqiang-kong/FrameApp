package com.kong.frameapp.modules.video.presenter;

import android.app.Application;

import com.kong.fmklibrary.di.scope.FragmentScope;
import com.kong.fmklibrary.integration.AppManager;
import com.kong.fmklibrary.mvp.BasePresenter;
import com.kong.frameapp.bean.MovieInfoBean;
import com.kong.frameapp.bean.VideoMainBean;
import com.kong.frameapp.modules.video.contract.VideoMainContract;
import com.kong.frameapp.net.ResultCallBack;

import java.util.ArrayList;

import javax.inject.Inject;


@FragmentScope
public class VideoMainFragPresenter extends BasePresenter<VideoMainContract.Model, VideoMainContract.View> implements VideoMainContract.Presenter {


    private AppManager mAppManager;
    private Application mApplication;
    private int lastUserId = 1;
    private boolean isFirst = true;
    private int preEndIndex;


    @Inject
    public VideoMainFragPresenter(VideoMainContract.Model model, VideoMainContract.View rootView
            , AppManager appManager, Application application) {

        super(model, rootView);
        this.mApplication = application;
        this.mAppManager = appManager;
        //this.mErrorHandler = handler;
    }


    @Override
    public void getData(boolean isRefresh) {

        this.mModel.requestData(isRefresh, null, new ResultCallBack<VideoMainBean>() {
            @Override
            public void onSuccess(VideoMainBean bean) {

                ArrayList<MovieInfoBean> list = new ArrayList<>();
                list.addAll(bean.getList());

                mRootView.refreshSuc(list);
            }

            @Override
            public void onFail(int failCode, String failInfo) {
                mRootView.refreshFail(failInfo);
            }
        });

    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        this.mAppManager = null;
        this.mApplication = null;
    }

}
