package com.kong.frameapp.modules.video.contract

import com.kong.frameapp.modules.base.BaseContract


/**
 * 视频类型列表
 * Created by kongdq on 17/9/12.
 */
interface VideoTypeListContract {

    interface View : BaseContract.View {

//        fun loadMoreData()
//        fun loadMoreSuc(dataObj: Any)
//        fun loadMoreFail(errMsg: String)
    }


    interface Presenter :  BaseContract.Presenter {

    }


    interface Model :  BaseContract.Model {

    }

}