package com.kong.frameapp.modules.video.model;


import com.kong.frameapp.bean.MovieResponse;
import com.kong.frameapp.bean.VideoMainBean;
import com.kong.frameapp.modules.video.MovieApi;
import com.kong.frameapp.modules.video.contract.VideoMainContract;
import com.kong.frameapp.net.JsonCallback;
import com.kong.frameapp.net.ResultCallBack;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;

import java.util.HashMap;

import javax.inject.Inject;

/**
 * 视频主页 Model
 * Created kongdq on 2017/6/29.
 */
public class VideoMainModel implements VideoMainContract.Model {

    @Inject
    public VideoMainModel() {
    }


    @Override
    public void requestData(boolean isRefresh, HashMap<String, String> params, final ResultCallBack callBack) {

        if (isRefresh) {
            OkGo.<MovieResponse<VideoMainBean>>get(MovieApi.HOME_PAGE)
                    .tag(this)
                    .cacheMode(CacheMode.NO_CACHE) // 缓存类型
                    .cacheKey(MovieApi.HOME_PAGE)          // 缓存Key值
                    .cacheTime(1000 * 60)   // 缓存过期时间
                    //.headers(getRequestHead())
                    .params(params)
                    .execute(new ResponseCallBack(callBack));
        } else {
            OkGo.<MovieResponse<VideoMainBean>>get(MovieApi.HOME_PAGE)
                    .tag(this)
                    .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST) // 缓存类型
                    .cacheKey(MovieApi.HOME_PAGE)          // 缓存Key值
                    .cacheTime(1000 * 60)   // 缓存过期时间
                    //.headers(getRequestHead())
                    .params(params)
                    .execute(new ResponseCallBack(callBack));
        }

    }


    //网络应答回调
    private class ResponseCallBack extends JsonCallback<MovieResponse<VideoMainBean>> {
        ResultCallBack callBack;

        ResponseCallBack(final ResultCallBack callBack) {
            this.callBack = callBack;
        }

        @Override
        public void onSuccess(Response<MovieResponse<VideoMainBean>> response) {

            VideoMainBean bean = response.body().getRet();
            callBack.onSuccess(bean);
        }

        @Override
        public void onCacheSuccess(Response<MovieResponse<VideoMainBean>> response) {

            VideoMainBean bean = response.body().getRet();
            //数据为缓存类型
            //bean.setDataType(1);
            callBack.onSuccess(bean);
        }

        @Override
        public void onError(Response<MovieResponse<VideoMainBean>> response) {

            callBack.onFail(1, "");
        }
    }


    @Override
    public void onDestroy() {

    }

}
