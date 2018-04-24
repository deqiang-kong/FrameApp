package com.kong.frameapp.modules.wynews.contract

import com.kong.frameapp.modules.base.BaseContract
import com.kong.frameapp.net.ResultCallBack


/**
 * 新闻频道管理
 * Created by kongdq on 17/10/12.
 */
interface NewsChannelContract {

    interface View : BaseContract.View {

//        fun loadMoreData()
//        fun loadMoreSuc(dataObj: Any)
//        fun loadMoreFail(errMsg: String)

    }


    interface Presenter : BaseContract.Presenter {

//        fun loadMoreData()
    }


    interface Model : BaseContract.Model {

//        fun requestMoreData(date: String?, callBack: ResultCallBack<*>?)

    }

}