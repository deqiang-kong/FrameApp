package com.kong.frameapp.net;

import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;


/**
 *
 */
public abstract class ResponseCallback<T> extends JsonCallback<T> {

    public ResponseCallback() {
        super();

    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {

        super.onStart(request);
        // 主要用于在所有请求之前添加公共的请求头或请求参数
        // 例如登录授权的 token
        // 使用的设备信息
        // 可以随意添加,也可以什么都不传
        // 还可以在这里对所有的参数进行加密，均在这里实现
        request.headers(NetUtils.getInstance().getRequestHead());

    }


    @Override
    public void onError(Response<T> response) {
        super.onError(response);

        okhttp3.Response rawResponse = response.getRawResponse();
        //错误类型判读
        if (rawResponse != null) {
            int code = rawResponse.code();
            String message = rawResponse.message();

            // TODO: 17/9/21 错误类型解析

        } else {

            //网络异常
        }
    }


    @Override
    public void onFinish() {

    }
}
