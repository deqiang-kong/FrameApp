package com.kong.frameapp.modules.wynews.contract

import com.kong.frameapp.modules.base.BaseContract
import com.kong.frameapp.net.ResultCallBack
import java.util.*


/**
 * 新闻列表
 * Created by kongdq on 17/10/12.
 */
interface NewsListContract {

    interface View : BaseContract.View {

        fun loadMoreData()
        fun loadMoreSuc(dataObj: Any)
        fun loadMoreFail(errMsg: String)

    }


    interface Presenter : BaseContract.Presenter {

        fun loadMoreData()
    }


    interface Model : BaseContract.Model {

        fun requestMoreData(params: HashMap<String, String>?, callBack: ResultCallBack<*>?)
    }

}