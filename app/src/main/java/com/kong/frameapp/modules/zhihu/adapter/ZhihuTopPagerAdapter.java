package com.kong.frameapp.modules.zhihu.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kong.frameapp.R;
import com.kong.frameapp.bean.ZhiHuTopStory;
import com.kong.frameapp.modules.zhihu.ui.ZhiHuStoryDetailActivity;
import com.kong.frameapp.util.PictureLoadUtil;

import java.util.ArrayList;

/**
 */
public class ZhihuTopPagerAdapter extends PagerAdapter {

    private ArrayList<ZhiHuTopStory> mTopStories;
    private Context mContext;
    private Activity mActivity;

    public ZhihuTopPagerAdapter(Fragment fragment, ArrayList<ZhiHuTopStory> topStories) {
        this.mTopStories = topStories;
        mActivity = fragment.getActivity();
        mContext = fragment.getContext();
    }

    @Override
    public int getCount() {
        return mTopStories != null ? mTopStories.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.zhihutop_item, container, false);
        final ImageView mTopStoryImg = (ImageView) view.findViewById(R.id.top_story_img);
        TextView mTopStoryTitle = (TextView) view.findViewById(R.id.top_story_title);
        mTopStoryTitle.setText(mTopStories.get(position).getTitle());

        PictureLoadUtil.getInstance().displayImageView(mContext,mTopStories.get(position).getImage(),mTopStoryImg);


        mTopStoryImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到其他界面
                Bundle bundle = new Bundle();
                Intent intent = new Intent(mContext, ZhiHuStoryDetailActivity.class);
                bundle.putString("title", mTopStories.get(position).getTitle());
                bundle.putInt("id", mTopStories.get(position).getId());

                intent.putExtras(bundle);
                mContext.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(mActivity).toBundle());
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void resetData(ArrayList<ZhiHuTopStory> topStories) {
        mTopStories.clear();
        mTopStories.addAll(topStories);
        notifyDataSetChanged();
    }
}
