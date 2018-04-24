package com.kong.frameapp.modules.video.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.kong.fmklibrary.controls.magicrecyclerView.BaseItem
import com.kong.fmklibrary.controls.magicrecyclerView.BaseRecyclerAdapter
import com.kong.frameapp.R
import com.kong.frameapp.bean.MovieInfo
import com.kong.frameapp.bean.VideoTypeListBean
import com.kong.frameapp.util.PictureLoadUtil

/**
 * 视频信息
 * Created by kaipai on 17/11/1.
 */
class VideoInfoAdapter(var context: Context) : BaseRecyclerAdapter() {

    private var image_width: Int
    private var image_height: Int

    init{
        val value = context.getResources().getDimension(R.dimen.video_type_card_height)
        image_width = value.toInt()
        image_height = value.toInt() * 5 / 3
    }


    override fun onCreate(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.video_type_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBind(viewHolder: RecyclerView.ViewHolder?, RealPosition: Int, data: BaseItem<*>) {

        val listBean = data.data as MovieInfo.ChildListBean
        val holder = viewHolder as ViewHolder

        val params = holder.mRlParent.layoutParams as FrameLayout.LayoutParams
        params.height = image_height
        holder.mRlParent.layoutParams = params
        val pic = listBean.pic
        if (!TextUtils.isEmpty(pic)) {
            PictureLoadUtil.getInstance().displayImageView(context, pic, holder.mIvVideoType)
        }
        holder.mTvVideoType.setText(listBean.title)
    }


    internal class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mRlParent: RelativeLayout
        var mIvVideoType: ImageView
        var mTvVideoType: TextView

        init {
            mRlParent = view.findViewById<RelativeLayout>(R.id.rl_parent)
            mIvVideoType = view.findViewById<ImageView>(R.id.iv_video_type)
            mTvVideoType = view.findViewById<TextView>(R.id.tv_video_type)

            //获取到的是 SP 转换成 PX 后的值因此设置大小时要指定单位为 PX
            mTvVideoType.setTextSize(TypedValue.COMPLEX_UNIT_PX, view.getResources().getDimension(R.dimen.text_size_min))
        }
    }

}