package com.kong.frameapp.modules.wynews.model


import com.kong.frameapp.bean.NewsListBean
import com.kong.frameapp.modules.wynews.NewsApi
import com.kong.frameapp.modules.wynews.contract.NewsListContract
import com.kong.frameapp.net.ConnectStatus
import com.kong.frameapp.net.JsonCallback
import com.kong.frameapp.net.NetUtils
import com.kong.frameapp.net.ResultCallBack
import com.kong.frameapp.util.MyApplication
import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheMode
import com.lzy.okgo.model.Response
import java.util.*
import javax.inject.Inject

/**
 * 新闻列表Model
 * Created by kaipai on 17/10/11.
 */
class NewsListModel @Inject constructor() : NewsListContract.Model {


    override fun requestData(isRefresh: Boolean, params: HashMap<String, String>?, callBack: ResultCallBack<*>?) {
        callBack as ResultCallBack<NewsListBean>
        var channelId = params?.get("channelId")
        var num = params?.get("num")
        var urlnew = NewsApi.NEWS_TYPE_LIST + channelId + "/" + num + "-20.html"


        //刷新，请求网络失败后，读取缓存
        if (isRefresh) {
            OkGo.get<NewsListBean>(urlnew).tag(this).cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)      //缓存类型
                    .cacheKey(urlnew)          //缓存Key
                    .cacheTime((1000 * 60 * 10).toLong())        // 缓存过期时间

                    //.headers(getRequestHead())       // 请求头信息
                    //.params(params)       // 相关参数
                    .execute(NewsResCallBack(callBack))
        }
        //非刷新模式，读取缓存，缓存数据过期，请求网络失败后
        else {
            OkGo.get<NewsListBean>(urlnew).tag(this).cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)      //缓存类型
                    .cacheKey(urlnew)          //缓存Key
                    .cacheTime((1000 * 60 * 10).toLong())        // 缓存过期时间

                    //.headers(getRequestHead())       // 请求头信息
                    //.params(params)       // 相关参数
                    .execute(NewsResCallBack(callBack))
        }
    }


    override fun requestMoreData(params: HashMap<String, String>?, callBack: ResultCallBack<*>?) {
        callBack as ResultCallBack<NewsListBean>
        var channelId = params?.get("channelId")
        var num = params?.get("num")
        var newsUrl = NewsApi.NEWS_TYPE_LIST + channelId + "/" + num + "-20.html"


        OkGo.get<NewsListBean>(newsUrl).tag(this).execute(NewsResCallBack(callBack))
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
                callBack.onFail(rawResponse.code(), rawResponse.message())
            } else {
                //网络异常
                if (!NetUtils.isNetworkConnected(MyApplication.AppContext)) {
                    callBack.onFail(ConnectStatus.FLAG_FAIL_CONNECT, "亲！无可用网络〒_〒")
                }
            }
        }
    }


    override fun onDestroy() {

    }
}
