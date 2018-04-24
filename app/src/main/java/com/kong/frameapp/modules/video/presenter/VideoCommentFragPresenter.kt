package com.kong.frameapp.modules.video.presenter

import android.app.Application
import com.kong.fmklibrary.controls.magicrecyclerView.BaseItem
import com.kong.fmklibrary.integration.AppManager
import com.kong.fmklibrary.mvp.BasePresenter
import com.kong.frameapp.bean.CommentBean
import com.kong.frameapp.bean.VideoTypeListBean
import com.kong.frameapp.modules.video.contract.VideoCommentContract
import com.kong.frameapp.net.ResultCallBack
import java.util.*
import javax.inject.Inject


/**
 * 视频评论 Presenter
 * Created by kaipai on 17/10/12.
 */
class VideoCommentFragPresenter @Inject constructor(model: VideoCommentContract.Model, rootView: VideoCommentContract.View, appManager: AppManager, application: Application)
    : BasePresenter<VideoCommentContract.Model, VideoCommentContract.View>(model, rootView), VideoCommentContract.Presenter {

    private var currentPage = 0


    override fun getData(id: String) {
        val params = HashMap<String, String>()
        params.put("mediaId", id)
        params.put("pnum", (currentPage + 1).toString())

        mModel.requestData(false, params, object : ResultCallBack<CommentBean> {
            override fun onSuccess(dataObj: CommentBean) {

                currentPage = dataObj.pnum
                val totalPum = dataObj.totalPnum
                if (currentPage == totalPum) { //加载完所有的评论后
                    // mCommentFrag.noMore()
                }

                val mBaseItems = ArrayList<BaseItem<CommentBean.ListBean>>()
                val listBeen = dataObj.list
                if(listBeen !=null){
                    for (bean in listBeen) {

                        val baseItem = BaseItem<CommentBean.ListBean>()
                        baseItem.setData(bean)
                        mBaseItems.add(baseItem)
                    }

                    mRootView.refreshSuc(mBaseItems)
                }else{

                    //到底了
                }


               // mRootView.refreshSuc(dataObj)
            }

            override fun onFail(errorCode: Int, errorMsg: String) {

            }
        })
    }


    override fun getData(isRefresh: Boolean) {
    }


}