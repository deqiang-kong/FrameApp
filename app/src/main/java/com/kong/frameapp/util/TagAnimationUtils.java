package com.kong.frameapp.util;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * 标题栏显示，隐藏动画
 */

public class TagAnimationUtils {
    private static final String TAG = TagAnimationUtils.class.getSimpleName();

    /**
     * 从控件顶部移动到控件开始的位置
     */
    public static TranslateAnimation moveToViewLocation() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(500);
        return mHiddenAction;
    }

    /**
     * 从控件开始的位置移动到空间顶部
     */
    public static TranslateAnimation moveToViewTop() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, -1.0f);
        mHiddenAction.setDuration(500);
        return mHiddenAction;
    }

}
