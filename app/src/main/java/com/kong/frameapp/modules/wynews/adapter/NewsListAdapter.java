package com.kong.frameapp.modules.wynews.adapter;

import android.content.Context;

import com.kong.frameapp.R;
import com.kong.frameapp.bean.NewsBean;
import com.kong.frameapp.util.PictureLoadUtil;
import com.kong.frameapp.widget.selfdomadapter.BaseAdapterItem;
import com.kong.frameapp.widget.selfdomadapter.BaseRecyclerAdapter;
import com.kong.frameapp.widget.selfdomadapter.BaseRecyclerViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/**
 *
 * Created by kongdq on 17/11/12.
 */

public class NewsListAdapter extends BaseRecyclerAdapter{


    public NewsListAdapter(Context context, ArrayList<BaseAdapterItem> data) {
        super(context, data);
    }



    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.news_item;
    }


    @Override
    public void bindData(BaseRecyclerViewHolder holder, int position, BaseAdapterItem item) {

        NewsBean mNewsBean=(NewsBean)item.getData();

        PictureLoadUtil.getInstance().displayImageView(mContext, mNewsBean.getImgsrc(), holder.getImageView(R.id.news_image));

        holder.getTextView(R.id.tv_news_title).setText(mNewsBean.getTitle());
        holder.getTextView(R.id.tv_news_content).setText(mNewsBean.getDigest());
        holder.getTextView(R.id.tv_news_source).setText(mNewsBean.getPtime());
    }

}
