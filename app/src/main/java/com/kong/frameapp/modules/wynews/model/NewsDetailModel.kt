package com.kong.frameapp.modules.wynews.model


import com.kong.frameapp.bean.NewsContent
import com.kong.frameapp.bean.NewsListBean
import com.kong.frameapp.modules.wynews.JsonUtils
import com.kong.frameapp.modules.wynews.contract.NewsDetailContract
import com.kong.frameapp.net.JsonCallback
import com.kong.frameapp.net.ResultCallBack
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import java.io.IOException
import java.util.*
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * 新闻详情Model
 * Created by kaipai on 17/10/11.
 */
class NewsDetailModel @Inject constructor() : NewsDetailContract.Model {

    var newsId:String?=null

    override fun requestData(isRefresh: Boolean, params: HashMap<String, String>?, callBack: ResultCallBack<*>?) {
        callBack as ResultCallBack<NewsContent>
        newsId=params?.get("newsId")
        var urlnew = "http://c.m.163.com/nc/article/"+newsId+"/full.html"
        //urlnew="http://c.m.163.com/nc/article/D2QEEEP30001899O/full.html"
        OkGo.get<String>(urlnew)
                .tag(this)

                //.headers(getRequestHead())       // 请求头信息
                .params(params)       // 相关参数
                .execute(NewsResCallBack(callBack))

    }



    private inner class NewsResCallBack internal constructor(val callBack: ResultCallBack<NewsContent>) : JsonCallback<String>() {

        override fun onSuccess(response: Response<String>) {
            val body = response.body()
             var mNewsContent: NewsContent  //
            try {
                mNewsContent = JsonUtils.readJsonNewsContent(body, newsId)
                callBack.onSuccess(mNewsContent)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        override fun onError(response: Response<String>) {
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
