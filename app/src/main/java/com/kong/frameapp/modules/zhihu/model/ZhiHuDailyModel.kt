package com.kong.frameapp.modules.zhihu.model


import com.kong.frameapp.bean.ZhiHuDailyBean
import com.kong.frameapp.modules.zhihu.ZhiHuApi
import com.kong.frameapp.modules.zhihu.contract.ZhiHuDailyContract
import com.kong.frameapp.net.JsonCallback
import com.kong.frameapp.net.ResultCallBack
import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheMode
import com.lzy.okgo.model.Response
import java.util.*
import javax.inject.Inject

/**
 * 知乎日报Model
 * Created by kaipai on 17/10/11.
 */
class ZhiHuDailyModel @Inject constructor() : ZhiHuDailyContract.Model {


    override fun requestData(isRefresh: Boolean, params: HashMap<String, String>?, callBack: ResultCallBack<*>?) {
        callBack as ResultCallBack<ZhiHuDailyBean>

        OkGo.get<ZhiHuDailyBean>(ZhiHuApi.HOME_PAGE)
                .tag(this)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)      //缓存类型
                .cacheKey(ZhiHuApi.HOME_PAGE)          //缓存Key
                .cacheTime((1000 * 60).toLong())        // 缓存过期时间

                //.headers(getRequestHead())       // 请求头信息
                .params(params)       // 相关参数
                .execute(ZhiHuResCallBack(callBack))

    }


    override fun requestMoreData(date: String?, callBack: ResultCallBack<*>?) {
        callBack as ResultCallBack<ZhiHuDailyBean>
        var url = ZhiHuApi.HOME_PAGE_MORE + date

        OkGo.get<ZhiHuDailyBean>(url)
                .tag(this)
                .cacheMode(CacheMode.NO_CACHE)      //缓存类型

                .execute(ZhiHuResCallBack(callBack))
    }


    private inner class ZhiHuResCallBack internal constructor(val callBack: ResultCallBack<ZhiHuDailyBean>) : JsonCallback<ZhiHuDailyBean>() {

        override fun onSuccess(response: Response<ZhiHuDailyBean>) {
            val body = response.body()
            callBack.onSuccess(body)
        }

        override fun onCacheSuccess(response: Response<ZhiHuDailyBean>) {
            val body = response.body()
            callBack.onSuccess(body)
        }

        override fun onError(response: Response<ZhiHuDailyBean>) {
            val rawResponse = response.rawResponse
            if (rawResponse != null) {
                val code = rawResponse.code()
                val message = rawResponse.message()

                callBack.onFail(code, message)
            } else {

                //网络异常
            }

        }
    }


    override fun onDestroy() {

    }
}