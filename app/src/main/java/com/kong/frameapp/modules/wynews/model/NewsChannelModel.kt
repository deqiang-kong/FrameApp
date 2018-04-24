package com.kong.frameapp.modules.wynews.model


import com.kong.frameapp.bean.NewsListBean
import com.kong.frameapp.bean.TopNewsList
import com.kong.frameapp.modules.wynews.contract.NewsChannelContract
import com.kong.frameapp.net.JsonCallback
import com.kong.frameapp.net.ResultCallBack
import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheMode
import com.lzy.okgo.model.Response
import java.util.*
import javax.inject.Inject

/**
 * 新闻频道管理Model
 * Created by kaipai on 17/10/11.
 */
class NewsChannelModel @Inject constructor() : NewsChannelContract.Model {


    override fun requestData(isRefresh: Boolean, params: HashMap<String, String>?, callBack: ResultCallBack<*>?) {
        callBack as ResultCallBack<NewsListBean>
        var urlnew = "http://c.m.163.com/nc/article/list/T1348647909107/0-20.html"

        OkGo.get<NewsListBean>(urlnew)
                .tag(this)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)      //缓存类型
                .cacheKey(urlnew)          //缓存Key
                .cacheTime((1000 * 60).toLong())        // 缓存过期时间

                //.headers(getRequestHead())       // 请求头信息
                .params(params)       // 相关参数
                .execute(NewsResCallBack(callBack))

    }



    private inner class NewsResCallBack internal constructor(val callBack: ResultCallBack<NewsListBean>) : JsonCallback<NewsListBean>() {

        override fun onSuccess(response: Response<NewsListBean>) {
            val body = response.body()
            callBack.onSuccess(body)
        }

        override fun onCacheSuccess(response: Response<NewsListBean>) {
            val body = response.body()
            callBack.onSuccess(body)
        }

        override fun onError(response: Response<NewsListBean>) {
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