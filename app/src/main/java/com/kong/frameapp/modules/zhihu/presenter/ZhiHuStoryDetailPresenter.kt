package com.kong.frameapp.modules.zhihu.presenter

import android.app.Application
import com.kong.fmklibrary.integration.AppManager
import com.kong.fmklibrary.mvp.BasePresenter
import com.kong.frameapp.bean.ZhiHuStoryDetailBean
import com.kong.frameapp.modules.zhihu.contract.ZhiHuStoryDetailContract
import com.kong.frameapp.net.ResultCallBack
import javax.inject.Inject


/**知乎列表
 * Created by kaipai on 17/10/12.
 */
class ZhiHuStoryDetailPresenter @Inject constructor(model: ZhiHuStoryDetailContract.Model, rootView: ZhiHuStoryDetailContract.View, appManager: AppManager, application: Application)
    : BasePresenter<ZhiHuStoryDetailContract.Model, ZhiHuStoryDetailContract.View>(model, rootView), ZhiHuStoryDetailContract.Presenter {


    override fun getData(id: String) {

        mModel.requestData(id, object : ResultCallBack<ZhiHuStoryDetailBean> {
            override fun onSuccess(dataObj: ZhiHuStoryDetailBean) {
                //mIView.showWaitingView();
                mRootView.refreshSuc(dataObj)
            }

            override fun onFail(errorCode: Int, errorMsg: String) {
                //mIView.againsWaitingView();
                //mIView.refreshFail(errorMsg);
            }
        })

    }


    override fun getData(isRefresh: Boolean) {


    }



}