package com.kong.frameapp.modules.customnews.model


import com.kong.frameapp.bean.CustomNewsChannelBean
import com.kong.frameapp.bean.CustomNewsResponse
import com.kong.frameapp.modules.customnews.CustomNewsApi
import com.kong.frameapp.modules.customnews.contract.NewsChannelContract
import com.kong.frameapp.net.JsonCallback
import com.kong.frameapp.net.ResultCallBack
import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheMode
import com.lzy.okgo.model.HttpHeaders
import com.lzy.okgo.model.Response
import java.util.*
import javax.inject.Inject

/**
 * 新闻频道管理Model
 * Created by kaipai on 17/10/11.
 */
class NewsChannelModel @Inject constructor() : NewsChannelContract.Model {


    override fun requestData(isRefresh: Boolean, params: HashMap<String, String>?, callBack: ResultCallBack<*>?) {
        callBack as ResultCallBack<CustomNewsChannelBean>


        val appcode = "ed1fa735aa8e4ae59213c14fd0bd41f3"
        val headers = HttpHeaders()
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode)
        //val querys = HashMap<String, String>()

        OkGo.get<CustomNewsResponse<CustomNewsChannelBean>>(CustomNewsApi.NEW_CHANNEL_LIST)
                .tag(this)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)      //缓存类型
                .cacheKey(CustomNewsApi.NEW_CHANNEL_LIST)          //缓存Key
                .cacheTime((1000 * 60).toLong())        // 缓存过期时间

                .headers(headers)       // 请求头信息
                .params(params)       // 相关参数
                .execute(NewsResCallBack(callBack))

    }



    private inner class NewsResCallBack internal constructor(val callBack: ResultCallBack<CustomNewsChannelBean>) : JsonCallback<CustomNewsResponse<CustomNewsChannelBean>>() {

        override fun onSuccess(response: Response<CustomNewsResponse<CustomNewsChannelBean>>) {
            val body = response.body()
            val bean=body.showapi_res_body
            callBack.onSuccess(bean)
        }

        override fun onCacheSuccess(response: Response<CustomNewsResponse<CustomNewsChannelBean>>) {
            val body = response.body()
            val bean=body.showapi_res_body
            callBack.onSuccess(bean)
        }

        override fun onError(response: Response<CustomNewsResponse<CustomNewsChannelBean>>) {
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