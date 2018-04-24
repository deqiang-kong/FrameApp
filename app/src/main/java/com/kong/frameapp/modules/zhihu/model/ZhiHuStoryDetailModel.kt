package com.kong.frameapp.modules.zhihu.model


import com.kong.fmklibrary.mvp.BaseModel
import com.kong.frameapp.bean.ZhiHuStoryDetailBean
import com.kong.frameapp.modules.zhihu.ZhiHuApi
import com.kong.frameapp.modules.zhihu.contract.ZhiHuStoryDetailContract
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
class ZhiHuStoryDetailModel @Inject constructor() : BaseModel(), ZhiHuStoryDetailContract.Model {


    override fun requestData(id: String, callBack: ResultCallBack<*>) {
        var url = ZhiHuApi.ZHIHU_STORY_DETAIL + id
        callBack as ResultCallBack<ZhiHuStoryDetailBean>

        OkGo.get<ZhiHuStoryDetailBean>(url)
                .tag(this)
                .cacheMode(CacheMode.NO_CACHE)      //缓存类型
                //.cacheKey(url)          //缓存Key
                //.cacheTime((1000 * 60).toLong())        // 缓存过期时间

                //.headers(getRequestHead())       // 请求头信息
                //.params(null)       // 相关参数
                .execute(ZhiHuResCallBack(callBack))


    }


    override fun requestData(isRefresh: Boolean, params: HashMap<String, String>?, callBack: ResultCallBack<*>?) {

    }

    override fun onDestroy() {
    }


    private inner class ZhiHuResCallBack internal constructor(val callBack: ResultCallBack<ZhiHuStoryDetailBean>) : JsonCallback<ZhiHuStoryDetailBean>() {

        override fun onSuccess(response: Response<ZhiHuStoryDetailBean>) {
            val body = response.body()
            callBack.onSuccess(body)
        }

        override fun onCacheSuccess(response: Response<ZhiHuStoryDetailBean>?) {

        }

        override fun onError(response: Response<ZhiHuStoryDetailBean>) {
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


}