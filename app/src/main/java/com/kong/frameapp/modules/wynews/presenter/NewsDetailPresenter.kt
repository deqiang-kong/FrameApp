package com.kong.frameapp.modules.wynews.presenter

import android.app.Application
import com.kong.fmklibrary.integration.AppManager
import com.kong.fmklibrary.mvp.BasePresenter
import com.kong.frameapp.bean.NewsBean
import com.kong.frameapp.bean.NewsContent
import com.kong.frameapp.bean.NewsListBean
import com.kong.frameapp.modules.wynews.contract.NewsDetailContract
import com.kong.frameapp.modules.wynews.contract.NewsListContract
import com.kong.frameapp.net.ResultCallBack
import java.util.*
import javax.inject.Inject


/**
 * 新闻详情Presenter
 * Created by kaipai on 17/10/12.
 */
class NewsDetailPresenter @Inject constructor(model: NewsDetailContract.Model, rootView: NewsDetailContract.View, appManager: AppManager, application: Application) : BasePresenter<NewsDetailContract.Model, NewsDetailContract.View>(model, rootView), NewsDetailContract.Presenter {


    var newsId: String = ""
    private var currentPage = 0

    override fun getData(isRefresh: Boolean) {
        val params = HashMap<String, String>()
        params.put("newsId", newsId)
        //params.put("pnum", currentPage.toString())

        mModel.requestData(isRefresh, params, object : ResultCallBack<NewsContent> {
            override fun onSuccess(dataObj: NewsContent) {

                mRootView.refreshSuc(dataObj)
            }

            override fun onFail(errorCode: Int, errorMsg: String) {
                //mIView.againsWaitingView();
                //mIView.refreshFail(errorMsg);
            }
        })
    }


}