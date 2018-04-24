package com.kong.frameapp.modules.zhihu.presenter

import android.app.Application
import com.kong.fmklibrary.integration.AppManager
import com.kong.fmklibrary.mvp.BasePresenter
import com.kong.frameapp.bean.ZhiHuDailyBean
import com.kong.frameapp.modules.zhihu.contract.ZhiHuDailyContract
import com.kong.frameapp.net.ResultCallBack
import javax.inject.Inject


/**知乎列表
 * Created by kaipai on 17/10/12.
 */
class ZhiHuDailyFragPresenter @Inject constructor(model: ZhiHuDailyContract.Model, rootView: ZhiHuDailyContract.View, appManager: AppManager, application: Application)
    : BasePresenter<ZhiHuDailyContract.Model, ZhiHuDailyContract.View>(model, rootView), ZhiHuDailyContract.Presenter {

    //    private var mAppManager: AppManager? = null
    //    private var mApplication: Application? = null
    var date: String? = null


    override fun getData(isRefresh: Boolean) {

        mModel.requestData(isRefresh, null, object : ResultCallBack<ZhiHuDailyBean> {
            override fun onSuccess(dataObj: ZhiHuDailyBean) {
                //mIView.showWaitingView();
                date = dataObj.date
                mRootView.refreshSuc(dataObj)
            }

            override fun onFail(errorCode: Int, errorMsg: String) {
                //mIView.againsWaitingView();
                //mIView.refreshFail(errorMsg);
            }
        })
    }


    override fun loadMoreData() {
        mModel.requestMoreData(date, object : ResultCallBack<ZhiHuDailyBean> {
            override fun onSuccess(dataObj: ZhiHuDailyBean) {
                date = dataObj.date
                mRootView.loadMoreSuc(dataObj)
            }

            override fun onFail(errorCode: Int, errorMsg: String) {
                //mIView.loadMoreFail(errorMsg);
            }
        })
    }

}