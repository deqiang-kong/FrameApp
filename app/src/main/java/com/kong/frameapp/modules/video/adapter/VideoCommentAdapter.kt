package com.kong.frameapp.modules.video.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kong.fmklibrary.controls.magicrecyclerView.BaseItem
import com.kong.fmklibrary.controls.magicrecyclerView.BaseRecyclerAdapter
import com.kong.frameapp.R
import com.kong.frameapp.bean.CommentBean
import com.kong.frameapp.util.PictureLoadUtil
import de.hdodenhof.circleimageview.CircleImageView

/**
 * 视频评论列表
 * Created by kaipai on 17/11/1.
 */
class VideoCommentAdapter(var context: Context) : BaseRecyclerAdapter() {

    private val widthPx: Int
    private val heighPx: Int

    init{
        val value = context.getResources().getDimension(R.dimen.comment_userimag_size)
        widthPx = value.toInt()
        heighPx = value.toInt()
    }


    override fun onCreate(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.video_comment_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBind(viewHolder: RecyclerView.ViewHolder?, RealPosition: Int, data: BaseItem<*>) {

        val comment = data.data as CommentBean.ListBean
        val holder = viewHolder as ViewHolder

        viewHolder.mTvLike.setText(comment.likeNum.toString())
        viewHolder.mTvUsername.setText(comment.phoneNumber)
        viewHolder.mTvCommentText.setText(comment.msg)
        viewHolder.mTvTimeText.setText(comment.time)

        val image = comment.userPic
        if (!TextUtils.isEmpty(image)) {
            PictureLoadUtil.getInstance().displayImageView(context, image, holder.mCivUser)
        }
    }


    internal class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mCivUser: CircleImageView
        var mTvLike: TextView
        var mTvCommentText: TextView
        var mTvTimeText: TextView
        var mTvUsername: TextView

       init{

            mCivUser = view.findViewById<CircleImageView>(R.id.civ_user)
            mTvLike = view.findViewById<TextView>(R.id.tv_like)
            mTvCommentText = view.findViewById<TextView>(R.id.tv_comment_text)
            mTvTimeText = view.findViewById<TextView>(R.id.tv_time_text)
            mTvUsername = view.findViewById<TextView>(R.id.tv_username)
        }
    }

}