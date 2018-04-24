package com.kong.frameapp.net;

/**
 * 网络请求结果回调
 * Created kongdq on 2017/4/29.
 */
public interface ResultCallBack<T> {

    /*
    * 联网 解析成功后回调的方法,可以传递任何形式的数据给调用者
    */
    void onSuccess(T t);

    /*
     * 联网或解析失败回调的方法
     */
    void onFail(int failCode, String failInfo);
}
