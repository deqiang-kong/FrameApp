package com.kong.frameapp.modules.video.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.kong.fmklibrary.base.MVPBaseActivity
import com.kong.fmklibrary.controls.magicrecyclerView.BaseItem
import com.kong.fmklibrary.controls.magicrecyclerView.BaseRecyclerAdapter
import com.kong.fmklibrary.di.component.AppComponent
import com.kong.frameapp.R
import com.kong.frameapp.bean.VideoTypeListBean
import com.kong.frameapp.injector.component.DaggerVideoTypeListComponent
import com.kong.frameapp.injector.module.VideoTypeListModule
import com.kong.frameapp.modules.video.adapter.VideoTypeListAdapter
import com.kong.frameapp.modules.video.contract.VideoTypeListContract
import com.kong.frameapp.modules.video.presenter.VideoTypeListPresenter
import kotlinx.android.synthetic.main.video_type_list_layout.*
import java.util.ArrayList


/**
 * Created by kaipai on 17/10/13.
 */
class VideoTypeListActivity : MVPBaseActivity<VideoTypeListPresenter>(), VideoTypeListContract.View , BaseRecyclerAdapter.OnItemClickListener {

    var mMoreTitle: String? = null
    var shareUrl: String = ""
    internal var width: Int = 0
    internal var heigh: Int = 0
    internal var storyId: String = ""
    var loading = false

    var mAdapter: VideoTypeListAdapter?=null

    override fun setupActivityComponent(appComponent: AppComponent?) {
        DaggerVideoTypeListComponent.builder()
                .appComponent(appComponent)
                .videoTypeListModule(VideoTypeListModule(this))
                .build()
                .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        setContentView(R.layout.video_type_list_layout)
        return 0
    }

    override fun initData(savedInstanceState: Bundle?) {
        mMoreTitle = intent.getStringExtra("videoType")

        toolbar.setTitle(mMoreTitle)
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(View.OnClickListener { finish() })
        mrv_video_list.setLayoutManager(LinearLayoutManager(this))
        mrv_video_list.setItemAnimator(DefaultItemAnimator())
        mrv_video_list.getItemAnimator().setChangeDuration(0)
        mrv_video_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!loading && mrv_video_list.loadAble()) {
                    loading = true
                    mPresenter.getData(false)
                }
            }
        })

        mPresenter.getDataType(mMoreTitle)
    }

    override fun refreshData(isRefresh: Boolean) {

    }

    override fun showWaitingView() {
    }

    override fun againWaitingView() {
    }

    override fun dismissWaitingView() {
    }


    override fun showMessage(message: String?) {
    }


    override fun refreshSuc(dataObj: Any) {

        val list = dataObj as ArrayList<BaseItem<*>>
        loading = false
        tv_empty_msg.setVisibility(View.GONE)
        if (mAdapter == null) {
            mAdapter = VideoTypeListAdapter(this)
            mAdapter?.setBaseDatas(list)
            mrv_video_list.setAdapter(mAdapter)
            mrv_video_list.addOnItemClickListener(this)
        } else {
            mAdapter?.addBaseDatas(list)
        }
    }


    override fun refreshFail(errMsg: String) {
    }

    override fun onItemClick(position: Int, data: BaseItem<*>, view: View) {
        val bean = data.data as VideoTypeListBean.ListBean

        val intent = Intent(this, VideoDetailActivity::class.java)
        intent.putExtra("id", bean.dataId)
        intent.putExtra("titleName", bean.title)
        intent.putExtra("img_url", bean.pic)
        startActivity(intent)
    }
}