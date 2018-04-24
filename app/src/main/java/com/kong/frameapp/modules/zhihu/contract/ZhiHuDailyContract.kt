package com.kong.frameapp.modules.zhihu.contract

import com.kong.frameapp.modules.base.BaseContract
import com.kong.frameapp.net.ResultCallBack


/**
 * 知乎日报列表
 * Created by kongdq on 17/10/12.
 */
interface ZhiHuDailyContract {

    interface View : BaseContract.View {

        fun loadMoreData()
        fun loadMoreSuc(dataObj: Any)
        fun loadMoreFail(errMsg: String)

    }


    interface Presenter : BaseContract.Presenter {

        fun loadMoreData()
    }


    interface Model : BaseContract.Model {

        fun requestMoreData(date: String?, callBack: ResultCallBack<*>?)

    }

}