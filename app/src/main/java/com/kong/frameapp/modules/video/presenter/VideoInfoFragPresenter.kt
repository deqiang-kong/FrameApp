package com.kong.frameapp.modules.video.presenter

import android.app.Application
import com.kong.fmklibrary.integration.AppManager
import com.kong.fmklibrary.mvp.BasePresenter
import com.kong.frameapp.bean.MovieInfo
import com.kong.frameapp.modules.video.contract.VideoInfoContract
import com.kong.frameapp.net.ResultCallBack
import com.kong.frameapp.rxbus.RxBus
import com.kong.frameapp.rxbus.RxConstants
import java.util.*
import javax.inject.Inject


/**
 * 视频信息 Presenter
 * Created by kaipai on 17/10/12.
 */
class VideoInfoFragPresenter @Inject constructor(model: VideoInfoContract.Model, rootView: VideoInfoContract.View, appManager: AppManager, application: Application)
    : BasePresenter<VideoInfoContract.Model, VideoInfoContract.View>(model, rootView), VideoInfoContract.Presenter {



    override fun getData(id: String) {
        val params = HashMap<String, String>()
        params.put("mediaId", id)

        mModel.requestData(false, params, object : ResultCallBack<MovieInfo> {
            override fun onSuccess(dataObj: MovieInfo) {

                mRootView.refreshSuc(dataObj)
                RxBus.getDefault().postWithCode(RxConstants.LOADED_DATA_CODE, dataObj)
            }

            override fun onFail(errorCode: Int, errorMsg: String) {

            }
        })
    }


    override fun getData(isRefresh: Boolean) {
    }


}