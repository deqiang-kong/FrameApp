package com.kong.frameapp.modules.sortslide;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;


import com.kong.frameapp.R;


/**
 * 标签卡View
 */
public class LabelCardView extends LinearLayout {


    private int labelValue;

    private int startLocation;
    private int lastLocation;
    private int currentLocation;

    //标签当前状态
    private int currentStatus;
    //标签上一次状态
    private int lastStatus;


    private int color;

    private int screenWidth;
    private int paddingValue = 0;


    public LabelCardView(Context context) {
        super(context);
        initView(context);
    }

    public LabelCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }


    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.view_label_card, this, true);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;

//        llNotice = findViewById(R.id.llNotice);
//        txtTrumpet = findViewById(R.id.txtTrumpet);
//        //txtTrumpet1 = findViewById(R.id.txtTrumpet1);
//        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
//        rlBroadcastView = findViewById(R.id.rlBroadcastView);
//        viewQueue = new LinkedList<>();
//        widthUnit = getResources().getDimension(R.dimen.font_text_content1);
//
//        txtTrumpet.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });
    }


    /**
     * 展开框
     */
    private void extendFrame() {
//        txtTrumpet.setVisibility(View.VISIBLE);
//        //ObjectAnimator anim1 = ObjectAnimator.ofFloat(txtTrumpet, "alpha", 1f, 0f);
//        ObjectAnimator anim2 = ObjectAnimator.ofFloat(llNotice, "alpha", 0f, 1f);
//        ObjectAnimator anim3 = ObjectAnimator.ofFloat(llNotice, "scaleX", scale, 1f);
//        llNotice.setPivotX(0);
//
//        AnimatorSet animSet2 = new AnimatorSet();
//        //animSet2.play(anim1).with(anim2).with(anim3);
//        animSet2.play(anim2).with(anim3);
//        animSet2.setDuration(240);
//        animSet2.start();
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                txtTrumpet.setBackground(null);
//            }
//        }, 160);

    }

    /**
     * 收缩框
     */
    private void shrinkFrame() {
//        txtTrumpet.setVisibility(View.VISIBLE);
//        ObjectAnimator anim2 = ObjectAnimator.ofFloat(llNotice, "scaleX", 1f, scale);
//        //ObjectAnimator anim3 = ObjectAnimator.ofFloat(txtTrumpet, "alpha", 0f, 1f);
//        ObjectAnimator anim4 = ObjectAnimator.ofFloat(llNotice, "alpha", 1f, 0f);
//        llNotice.setPivotX(0);
//
//        AnimatorSet animSet1 = new AnimatorSet();
//        animSet1.play(anim2).with(anim4);
//        animSet1.setDuration(240);
//        animSet1.start();
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                txtTrumpet.setBackgroundResource(R.drawable.bg_broadcast);
//            }
//        }, 160);
    }


    TranslateAnimation mAnimation = null;

    private void setSlideAnimation(View view, int len) {
//        mAnimation = new TranslateAnimation(screenWidth / 3 * 2, -len, 0, 0);
//        mAnimation.setDuration(time);
//        mAnimation.setFillAfter(true);
//        view.startAnimation(mAnimation);

    }



}
