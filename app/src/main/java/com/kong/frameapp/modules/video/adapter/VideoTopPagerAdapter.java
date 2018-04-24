package com.kong.frameapp.modules.video.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kong.frameapp.R;
import com.kong.frameapp.bean.MovieInfoBean;
import com.kong.frameapp.modules.video.ui.VideoDetailActivity;
import com.kong.frameapp.util.PictureLoadUtil;

import java.util.List;


public class VideoTopPagerAdapter extends PagerAdapter {

    private List<MovieInfoBean.MovieDescription> mBanders;
    private Context mContext;
    private Activity mActivity;

    public VideoTopPagerAdapter(Fragment fragment, List<MovieInfoBean.MovieDescription> banders) {
        mContext = fragment.getContext();
        mActivity = fragment.getActivity();
        mBanders = banders;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.zhihutop_item, container, false);
        final ImageView mTopStoryImg = (ImageView) view.findViewById(R.id.top_story_img);
        String image = mBanders.get(position).getPic();
        if (!TextUtils.isEmpty(image)) {

            PictureLoadUtil.getInstance().displayImageView(mContext, image, mTopStoryImg);
        }
        mTopStoryImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到其他界面
                //Bundle bundle = new Bundle();
                Intent intent = new Intent(mContext, VideoDetailActivity.class);
                intent.putExtra("id", mBanders.get(position).getDataId());
                intent.putExtra("titleName", mBanders.get(position).getTitle());
                intent.putExtra("img_url", mBanders.get(position).getPic());

                mContext.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(mActivity).toBundle());

            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return mBanders != null ? mBanders.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        notifyDataSetChanged();
    }

    public void resetData(List<MovieInfoBean.MovieDescription> banders) {
        mBanders.clear();
        mBanders.addAll(banders);
        notifyDataSetChanged();
    }
}
