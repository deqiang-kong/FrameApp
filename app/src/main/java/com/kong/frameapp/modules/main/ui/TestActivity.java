package com.kong.frameapp.modules.main.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kong.frameapp.R;
import com.kong.frameapp.widget.ThreePointLoadingView;


/**
 * Created by yt on 2017/7/27.
 */


public class TestActivity extends Activity{

    private ThreePointLoadingView mLoadingView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mLoadingView  = findViewById(R.id.tplView);
//
//
//        mLoadingView.play();
    }
}
//public class TestActivity extends MVPBaseActivity<TestPresenter> implements TestContract.View{
//
//
//    @Override
//    public void setupActivityComponent(AppComponent appComponent) {
//
//
//        DaggerTestComponent.builder()
//                .appComponent(appComponent)
//                .testModule(new TestModule(this))
//                .build()
//                .inject(this);
//
//    }
//
//
//
//
//    public int initView(Bundle savedInstanceState) {
////        setContentView(R.layout.activity_main);
////        mLoadingView  = findViewById(R.id.tplView);
//        return R.layout.activity_main;
//    }
//
//
//    public void initData(Bundle savedInstanceState) {
//        mLoadingView  = findViewById(R.id.tplView);
//
//
//        mLoadingView.play();
//        mPresenter.getData(true);
//    }
//
//
//    @Override
//    public void startLoadMore() {
//
//    }
//
//    @Override
//    public void endLoadMore() {
//
//    }
//
//
//    @Override
//    public void showWaitingView() {
//
//    }
//
//    @Override
//    public void againWaitingView() {
//
//    }
//
//    @Override
//    public void dismissWaitingView() {
//
//    }
//
//    @Override
//    public void showMessage(String message) {
//
//    }
//
//    @Override
//    public void launchActivity(Intent intent) {
//
//    }
//
//    @Override
//    public void killMyself() {
//
//    }
//}
