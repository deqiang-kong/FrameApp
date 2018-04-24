package com.kong.frameapp.modules.video.model


import com.kong.frameapp.bean.CommentBean
import com.kong.frameapp.bean.MovieInfo
import com.kong.frameapp.bean.MovieResponse
import com.kong.frameapp.bean.VideoTypeListBean
import com.kong.frameapp.modules.video.MovieApi
import com.kong.frameapp.modules.video.contract.VideoCommentContract
import com.kong.frameapp.modules.video.contract.VideoInfoContract
import com.kong.frameapp.modules.video.contract.VideoTypeListContract
import com.kong.frameapp.net.JsonCallback
import com.kong.frameapp.net.ResultCallBack
import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheMode
import com.lzy.okgo.model.Response
import java.util.*
import javax.inject.Inject

/**
 * 视频评论Model
 * Created by kaipai on 17/10/11.
 */
class VideoCommentModel @Inject constructor() : VideoCommentContract.Model {


    override fun requestData(isRefresh: Boolean, params: HashMap<String, String>?, callBack: ResultCallBack<*>?) {
        callBack as ResultCallBack<CommentBean>

        OkGo.get<MovieResponse<CommentBean>>(MovieApi.VIDEO_COMMENT)
                .tag(this)
                .tag(this)
                .cacheMode(CacheMode.NO_CACHE)      //缓存类型

                .params(params)       // 相关参数
                .execute(ResponseCallBack(callBack))
    }


    //网络应答回调
    private inner class ResponseCallBack internal constructor(internal var callBack: ResultCallBack<CommentBean>) : JsonCallback<MovieResponse<CommentBean>>() {

        override fun onSuccess(response: Response<MovieResponse<CommentBean>>) {

            val bean = response.body().ret
            callBack.onSuccess(bean)
        }

        override fun onCacheSuccess(response: Response<MovieResponse<CommentBean>>) {

        }

        override fun onError(response: Response<MovieResponse<CommentBean>>) {

//            callBack.onFail(1, "")
        }
    }


    override fun onDestroy() {

    }
}