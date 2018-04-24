package com.kong.frameapp.modules.main.presenter;

import android.app.Application;


import com.kong.fmklibrary.di.scope.ActivityScope;
import com.kong.fmklibrary.integration.AppManager;
import com.kong.fmklibrary.mvp.BasePresenter;
import com.kong.frameapp.modules.main.contract.TestContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;



@ActivityScope
public class TestPresenter extends BasePresenter<TestContract.Model, TestContract.View> implements TestContract.Presenter{


//    private RxErrorHandler mErrorHandler;
    private AppManager mAppManager;
    private Application mApplication;
//    private List<User> mUsers = new ArrayList<>();
//    private DefaultAdapter mAdapter;
    private int lastUserId = 1;
    private boolean isFirst = true;
    private int preEndIndex;


    @Inject
    public TestPresenter(TestContract.Model model, TestContract.View rootView
            , AppManager appManager, Application application) {
        super(model, rootView);
        this.mApplication = application;
        //this.mErrorHandler = handler;
        this.mAppManager = appManager;
    }



    public void requestUsers(final boolean pullToRefresh) {

        mModel.getData(true);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        this.mAppManager = null;
        this.mApplication = null;
    }

    @Override
    public void getData(boolean b) {
        mModel.getData(b);

    }
}
