package com.kong.frameapp.modules.zhihu.contract

import com.kong.frameapp.modules.base.BaseContract
import com.kong.frameapp.net.ResultCallBack

/**
 * 知乎日报详情
 * Created by kongdq on 17/10/12.
 */
interface ZhiHuStoryDetailContract {


    interface View : BaseContract.View {


    }


    interface Presenter : BaseContract.Presenter {

        fun getData(id: String)

    }


    interface Model : BaseContract.Model {

        fun requestData(id: String, callBack: ResultCallBack<*>)

    }
}
