package com.kong.frameapp.modules.wynews.presenter

import android.app.Application
import com.kong.fmklibrary.integration.AppManager
import com.kong.fmklibrary.mvp.BasePresenter
import com.kong.frameapp.bean.NewsBean
import com.kong.frameapp.bean.NewsListBean
import com.kong.frameapp.modules.wynews.contract.NewsListContract
import com.kong.frameapp.net.ResultCallBack
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject


/**
 * 新闻列表Presenter
 * Created by kongdq on 17/10/12.
 */
class NewsListFragPresenter @Inject constructor(model: NewsListContract.Model, rootView: NewsListContract.View, appManager: AppManager, application: Application) : BasePresenter<NewsListContract.Model, NewsListContract.View>(model, rootView), NewsListContract.Presenter {


    var channelId: String = ""
    var currentPage = 0
    val value = 20

    override fun getData(isRefresh: Boolean) {
        currentPage = 0
        val params = HashMap<String, String>()
        params.put("channelId", channelId)
        params.put("num", (value * currentPage).toString())

        mModel.requestData(isRefresh, params, object : ResultCallBack<NewsListBean> {
            override fun onSuccess(dataObj: NewsListBean) {
                var newsList = dataObj.getNewsList(channelId);
                currentPage++
                //mRootView.refreshSuc(newsList)
                //接收的数据按时间排序
                Observable.fromIterable<NewsBean>(newsList)
                        .toSortedList { o1, o2 -> o2.ptime.compareTo(o1.ptime)  }
                        .subscribe { list ->
                    mRootView.refreshSuc(list)
                }

            }

            override fun onFail(errorCode: Int, errorMsg: String) {
                mRootView.refreshFail(errorMsg);
            }
        })
    }


    override fun loadMoreData() {
        val params = HashMap<String, String>()
        params.put("channelId", channelId)
        params.put("num", (value * currentPage).toString())

        mModel.requestMoreData(params, object : ResultCallBack<NewsListBean> {
            override fun onSuccess(dataObj: NewsListBean) {

                currentPage++;
                var newsList = dataObj.getNewsList(channelId);
                mRootView.loadMoreSuc(newsList!!)
            }

            override fun onFail(errorCode: Int, errorMsg: String) {
                mRootView.loadMoreFail(errorMsg);
            }
        })
    }


}