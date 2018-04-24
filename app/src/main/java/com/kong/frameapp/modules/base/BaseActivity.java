package com.kong.frameapp.modules.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.kong.frameapp.R;


/**
 * BaseActivity
 * Created by kongdq on 2017/3/1.
 * email : 452211588@qq.com
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Activity mContext;

    /**
     * 用户登录状态
     **/
    protected boolean mIsLoaded;

    /**
     * 默认主题
     **/
    public final static int THEME_DEFAULT = 0;
    /**
     * 夜晚主题
     **/
    public final static int THEME_NIGHT = 1;
    /**
     * 节日主题
     **/
    public final static int THEME_HOLIDAY = 4;
    /**
     * 悲伤主题
     **/
    public final static int THEME_SAD = 5;
    /**
     * 当前系统主题Id
     **/
    public static int mThemeCode = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initTheme();

        //根据版本判断变更标题栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        mContext = this;
    }


    /**
     * 初始化主题
     */
    private void initTheme() {
        int mTheme;
        switch (mThemeCode) {
            case THEME_DEFAULT://
                mTheme = R.style.AppTheme_Default;
                break;
            case THEME_NIGHT:
                mTheme = R.style.AppTheme_Night;
                break;
            case THEME_HOLIDAY:
                mTheme = R.style.AppTheme_Holiday;
                break;
            case THEME_SAD:
                mTheme = R.style.AppTheme_Sad;
                break;
            default:
                mTheme = R.style.AppTheme_Default;
                break;
        }
        //设置主题色调
        setTheme(mTheme);
    }


    //添加fragment
    protected void addFragment(int fragmentContentId, Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(fragmentContentId, fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }
    }

    //移除fragment
    protected void removeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }


    /**
     * 通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }


    /**
     * 含有Bundle通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 通过Action跳转界面
     **/
    protected void startActivity(String action) {
        startActivity(action, null);
    }

    /**
     * 含有Bundle通过Action跳转界面
     **/
    protected void startActivity(String action, Bundle bundle) {
        Intent intent = new Intent();
        intent.setAction(action);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            /**
             * 点击空白位置 隐藏软键盘
             */
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        RefWatcher refWatcher = MyApplication.getRefWatcher(this);
//        refWatcher.watch(this);
    }

}
