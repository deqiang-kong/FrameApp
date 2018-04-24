package com.kong.frameapp.modules.wynews.presenter

import android.app.Application
import com.kong.fmklibrary.integration.AppManager
import com.kong.fmklibrary.mvp.BasePresenter
import com.kong.frameapp.bean.NewsBean
import com.kong.frameapp.bean.ZhiHuDailyBean
import com.kong.frameapp.modules.wynews.contract.NewsChannelContract
import com.kong.frameapp.net.ResultCallBack
import javax.inject.Inject


/**
 * 新闻频道管理Presenter
 * Created by kaipai on 17/10/12.
 */
class NewsChannelPresenter @Inject constructor(model: NewsChannelContract.Model, rootView: NewsChannelContract.View, appManager: AppManager, application: Application)
    : BasePresenter<NewsChannelContract.Model, NewsChannelContract.View>(model, rootView), NewsChannelContract.Presenter {


    //    private var mAppManager: AppManager? = null
    //    private var mApplication: Application? = null
    var date: String? = null


    override fun getData(isRefresh: Boolean) {

        mModel.requestData(isRefresh, null, object : ResultCallBack<ArrayList<NewsBean>> {
            override fun onSuccess(dataObj: ArrayList<NewsBean>) {
               // date = dataObj.date
                mRootView.refreshSuc(dataObj)
            }

            override fun onFail(errorCode: Int, errorMsg: String) {
                //mIView.againsWaitingView();
                //mIView.refreshFail(errorMsg);
            }
        })
    }


}