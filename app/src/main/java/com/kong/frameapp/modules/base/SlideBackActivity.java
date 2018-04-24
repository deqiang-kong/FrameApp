package com.kong.frameapp.modules.base;


import android.os.Bundle;

import com.kong.fmklibrary.base.MVPBaseActivity;
import com.kong.fmklibrary.controls.slideback.SlideBackHelper;
import com.kong.fmklibrary.controls.slideback.SlideConfig;
import com.kong.fmklibrary.controls.slideback.widget.SlideBackLayout;
import com.kong.fmklibrary.mvp.IPresenter;
import com.kong.frameapp.util.MyApplication;

/**
 * 侧滑activity
 * 所有侧滑返回的activity的父类
 * Created by kongdq on 17/11/9.
 */
public abstract class SlideBackActivity<P extends IPresenter> extends MVPBaseActivity<P> {


    protected SlideBackLayout mSlideBackLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //添加标志位判断侧滑类型

        // 默认开启侧滑，默认是整个页码侧滑
        mSlideBackLayout = SlideBackHelper.attach(this, MyApplication.getActivityHelper(), new SlideConfig.Builder()
                // 侧滑类型：边缘侧滑：true，整体滑动侧滑:false
                .edgeOnly(false)
                // 是否会屏幕旋转
                .rotateScreen(false)
                // 是否禁止侧滑
                .lock(false)
                // 侧滑的响应阈值，0~1，对应屏幕宽度*percent
                .edgePercent(0.1f)
                // 关闭页面的阈值，0~1，对应屏幕宽度*percent
                .slideOutPercent(0.35f).create(), null);


    }

}
