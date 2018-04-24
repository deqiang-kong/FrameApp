package com.kong.frameapp.modules.video.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.kong.fmklibrary.controls.magicrecyclerView.BaseItem
import com.kong.fmklibrary.controls.magicrecyclerView.BaseRecyclerAdapter
import com.kong.frameapp.R
import com.kong.frameapp.bean.VideoTypeListBean
import com.kong.frameapp.util.PictureLoadUtil

/**
 * 视频列表
 * Created by kaipai on 17/11/1.
 */
class VideoTypeListAdapter(var context: Context) : BaseRecyclerAdapter() {

    private val image_width: Int
    private val image_height: Int

    init{
        val value = context.getResources().getDimension(R.dimen.typed_video_item_height)
        image_width = value.toInt() * 3 / 4
        image_height = value.toInt()
    }


    override fun onCreate(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.video_type_list_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBind(viewHolder: RecyclerView.ViewHolder?, RealPosition: Int, data: BaseItem<*>) {

        val listBean = data.data as VideoTypeListBean.ListBean
        val holder = viewHolder as ViewHolder
        holder.mTvVideoName.text = listBean.title
        holder.mTvAirTime.text = "上映时间" + listBean.airTime + ""
        holder.mTvDuration.text = "时长" + listBean.duration + ""
        holder.mRbVideoStarts.rating = listBean.score / 2
        holder.mTvVideoRate.text = listBean.score.toString()
        val image = listBean.pic
        if (!TextUtils.isEmpty(image)) {

            PictureLoadUtil.getInstance().displayImageView(context, image, holder.mIvVideo)
        }
    }


    internal class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mIvVideo: ImageView
        var mTvVideoName: TextView
        var mRbVideoStarts: RatingBar
        var mTvVideoRate: TextView
        var mTvAirTime: TextView
        var mTvDuration: TextView

        init {

            mIvVideo = view.findViewById(R.id.iv_video)
            mTvVideoName = view.findViewById(R.id.tv_video_name)
            mRbVideoStarts = view.findViewById(R.id.rb_video_starts)
            mTvVideoRate = view.findViewById(R.id.tv_video_rate)
            mTvAirTime = view.findViewById(R.id.tv_airTime)
            mTvDuration = view.findViewById(R.id.tv_duration)
        }
    }

}