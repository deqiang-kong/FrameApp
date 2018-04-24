package com.kong.frameapp.modules.video.contract

import com.kong.frameapp.modules.base.BaseContract


/**
 * 视频评论
 * Created by kongdq on 17/9/12.
 */
interface VideoCommentContract {

    interface View : BaseContract.View {

    }


    interface Presenter : BaseContract.Presenter {

        fun getData(id: String)

    }


    interface Model : BaseContract.Model {

    }

}