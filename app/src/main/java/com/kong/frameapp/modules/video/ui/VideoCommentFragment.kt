package com.kong.frameapp.modules.video.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kong.fmklibrary.base.MVPBaseFragment
import com.kong.fmklibrary.controls.magicrecyclerView.BaseItem
import com.kong.fmklibrary.controls.magicrecyclerView.BaseRecyclerAdapter
import com.kong.fmklibrary.di.component.AppComponent
import com.kong.frameapp.R
import com.kong.frameapp.bean.CommentBean
import com.kong.frameapp.injector.component.DaggerVideoCommentComponent
import com.kong.frameapp.injector.module.VideoCommentModule
import com.kong.frameapp.modules.video.adapter.VideoCommentAdapter
import com.kong.frameapp.modules.video.contract.VideoCommentContract
import com.kong.frameapp.modules.video.presenter.VideoCommentFragPresenter
import kotlinx.android.synthetic.main.video_description_fragment.*
import java.util.ArrayList


class VideoCommentFragment constructor() : MVPBaseFragment<VideoCommentFragPresenter>(), VideoCommentContract.View, BaseRecyclerAdapter.OnItemClickListener {

    private var currentId = ""

    //本次加载数据是否是刷新
    private var refresh = true
    //是否正在加载
    private var loading = false
    //是否还有评论需要加载
    private var loadAble = true

    private var mAdapter: VideoCommentAdapter? = null

    override fun setupFragmentComponent(appComponent: AppComponent?) {
        DaggerVideoCommentComponent.builder()
                .appComponent(appComponent)
                .videoCommentModule(VideoCommentModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.video_description_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mParent = view
        mActivity = activity

        initView()
    }

    fun initView(){
        currentId = arguments.getString("dataId")

        mrv_recommend.setLayoutManager(LinearLayoutManager(this.context))
        //屏蔽掉默认的动画，防止刷新时图片闪烁
        mrv_recommend.getItemAnimator().setChangeDuration(0)

//        mSrlRefresh.setProgressBackgroundColorSchemeColor(resources.getColor(R.color.white_FFFFFF))
//        mSrlRefresh.setOnRefreshListener(this)
//        mSrlRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
//                android.R.color.holo_green_light,
//                android.R.color.holo_orange_light,
//                android.R.color.holo_red_light)
        mrv_recommend.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (mrv_recommend.loadAble() && !loading && loadAble) {
                    refreshData(false)
                }
            }
        })

        refreshData(false)
    }

    override fun refreshData(isRefresh: Boolean) {

        mPresenter?.getData(currentId)
    }



    override fun refreshSuc(dataObj: Any?) {

        tv_empty_msg.visibility = View.GONE
//        lastId = currentId
//        refresing = false
        loading = false
//        mSrlRefresh.setRefreshing(false)
       var items= dataObj as ArrayList<BaseItem<*>>
        if (mAdapter != null) {
            if (refresh) {
                mAdapter?.setBaseDatas(items)
            } else {
                mAdapter?.addBaseDatas(items)
            }
        } else {
            mAdapter = VideoCommentAdapter(this.context)
            mAdapter?.setBaseDatas(items)
            mrv_recommend.setAdapter(mAdapter)
        }
    }

    override fun refreshFail(errMsg: String?) {
    }

    override fun showWaitingView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun againWaitingView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissWaitingView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMessage(message: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun setData(data: Any?) {
    }

    override fun onItemClick(position: Int, data: BaseItem<*>?, view: View?) {
    }



    override fun onResume() {
        super.onResume()

    }


    override fun onDestroyView() {
        super.onDestroyView()

    }
}