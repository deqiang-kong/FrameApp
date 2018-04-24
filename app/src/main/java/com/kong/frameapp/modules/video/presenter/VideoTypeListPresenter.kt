package com.kong.frameapp.modules.video.presenter

import android.app.Application
import com.kong.fmklibrary.controls.magicrecyclerView.BaseItem
import com.kong.fmklibrary.integration.AppManager
import com.kong.fmklibrary.mvp.BasePresenter
import com.kong.frameapp.bean.VideoTypeListBean
import com.kong.frameapp.modules.video.contract.VideoTypeListContract
import com.kong.frameapp.net.ResultCallBack
import com.kong.framesamples.modules.video.VideoConstants
import java.util.*
import javax.inject.Inject


/**
 * 视频分类列表 Presenter
 * Created by kaipai on 17/10/12.
 */
class VideoTypeListPresenter @Inject constructor(model: VideoTypeListContract.Model, rootView: VideoTypeListContract.View, appManager: AppManager, application: Application)
    : BasePresenter<VideoTypeListContract.Model, VideoTypeListContract.View>(model, rootView), VideoTypeListContract.Presenter {


    private var currentPage = 1
    lateinit var id: String

    fun getDataType(title: String?) {
        when (title) {
            VideoConstants.MOVIE_TYPE_HOT -> {
                id = VideoConstants.MOVIE_TYPE_HOT_TYPE
            }
            VideoConstants.MOVIE_TYPE_RECOMMEND -> {
                id = VideoConstants.MOVIE_TYPE_RECOMMEND_TYPE
            }
            VideoConstants.MOVIE_TYPE_HOLLYWOOD -> {
                id = VideoConstants.MOVIE_TYPE_HOLLYWOOD_TYPE
            }
            VideoConstants.MOVIE_TYPE_TOPIC -> {
                id = VideoConstants.MOVIE_TYPE_TOPIC_TYPE
            }
            VideoConstants.MOVIE_TYPE_LIVE -> {
                id = VideoConstants.MOVIE_TYPE_LIVE_TYPE
            }
            VideoConstants.MOVIE_TYPE_LITTLEMOVIE -> {
                id = VideoConstants.MOVIE_TYPE_LITTLEMOVIE_TYPE
            }
            VideoConstants.MOVIE_TYPE_JSKS -> {
                id = VideoConstants.MOVIE_TYPE_JSKS_TYPE
            }
            VideoConstants.MOVIE_TYPE_BIGBRO -> {
                id = VideoConstants.MOVIE_TYPE_BIGBRO_TYPE
            }
            VideoConstants.MOVIE_TYPE_MIDNIGHT -> {
                id = VideoConstants.MOVIE_TYPE_MIDNIGHT_TYPE
            }

            else -> {

                id = VideoConstants.MOVIE_TYPE_HOT_TYPE
            }
        }

        getData(false)
    }


    override fun getData(isRefresh: Boolean) {

        val params = HashMap<String, String>()
        params.put("catalogId", id)
        params.put("pnum", currentPage.toString())

        mModel.requestData(isRefresh, params, object : ResultCallBack<VideoTypeListBean> {
            override fun onSuccess(dataObj: VideoTypeListBean) {
                currentPage = dataObj.pnum + 1
                val listBeen = dataObj.list
                val mBaseItems = ArrayList<BaseItem<VideoTypeListBean.ListBean>>()

                if(listBeen !=null){
                    for (bean in listBeen) {

                        val baseItem = BaseItem<VideoTypeListBean.ListBean>()
                        baseItem.setData(bean)
                        mBaseItems.add(baseItem)
                    }

                    mRootView.refreshSuc(mBaseItems)
                }else{

                    //到底了
                }

                //RxBus.getDefault().postWithCode(RxConstants.LOADED_DATA_CODE, dd.getData());
            }

            override fun onFail(errorCode: Int, errorMsg: String) {
                //mIView.againsWaitingView();
                //mIView.refreshFail(errorMsg);
            }
        })
    }


}