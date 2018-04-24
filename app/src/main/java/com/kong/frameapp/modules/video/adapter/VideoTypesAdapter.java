package com.kong.frameapp.modules.video.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kong.fmklibrary.controls.magicrecyclerView.BaseItem;
import com.kong.fmklibrary.controls.magicrecyclerView.BaseRecyclerAdapter;
import com.kong.frameapp.R;
import com.kong.frameapp.bean.MovieInfoBean;
import com.kong.frameapp.util.PictureLoadUtil;


public class VideoTypesAdapter extends BaseRecyclerAdapter {

    private Context mContext;
    private int image_width;
    private int image_height;

    public VideoTypesAdapter(Fragment fragment) {
        mContext = fragment.getContext();
        float value = fragment.getResources().getDimension(R.dimen.video_type_card_height);
        image_height = (int) value;
        image_width = (int) value * 4 / 3;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.video_type_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, BaseItem data) {
        ViewHolder holder = (ViewHolder) viewHolder;
        MovieInfoBean listBean = (MovieInfoBean) data.getData();
        String pic = listBean.getChildList().get(0).getPic();
        if (!TextUtils.isEmpty(pic)) {

            PictureLoadUtil.getInstance().displayImageView(mContext, pic, holder.mIvVideoType);
        }
        holder.mTvVideoType.setText(listBean.getTitle());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvVideoType;
        TextView mTvVideoType;

        ViewHolder(View view) {
            super(view);
            mIvVideoType=view.findViewById(R.id.iv_video_type);
            mTvVideoType=view.findViewById(R.id.tv_video_type);
        }
    }
}
