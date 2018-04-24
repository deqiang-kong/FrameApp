package com.kong.frameapp.modules.video.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kong.fmklibrary.base.MVPBaseFragment
import com.kong.fmklibrary.controls.FoldableTextView
import com.kong.fmklibrary.controls.magicrecyclerView.BaseItem
import com.kong.fmklibrary.controls.magicrecyclerView.BaseRecyclerAdapter
import com.kong.fmklibrary.controls.magicrecyclerView.SpaceDecoration
import com.kong.fmklibrary.di.component.AppComponent
import com.kong.frameapp.R
import com.kong.frameapp.bean.MovieInfo
import com.kong.frameapp.injector.component.DaggerVideoInfoComponent
import com.kong.frameapp.injector.module.VideoInfoModule
import com.kong.frameapp.modules.video.adapter.VideoInfoAdapter
import com.kong.frameapp.modules.video.contract.VideoInfoContract
import com.kong.frameapp.modules.video.presenter.VideoInfoFragPresenter
import com.kong.frameapp.rxbus.RxBus
import com.kong.frameapp.rxbus.RxConstants
import com.kong.frameapp.util.DensityUtil
import kotlinx.android.synthetic.main.video_description_fragment.*
import java.util.*


class VideoInfoFragment constructor() : MVPBaseFragment<VideoInfoFragPresenter>(), VideoInfoContract.View, BaseRecyclerAdapter.OnItemClickListener {

    private var currentId = ""

    private var mFoldableTextView: FoldableTextView? = null

    private var mBaseItems: ArrayList<BaseItem<*>>? = null
    private var mAdapter: VideoInfoAdapter? = null


    override fun setupFragmentComponent(appComponent: AppComponent?) {
        DaggerVideoInfoComponent.builder()
                .appComponent(appComponent)
                .videoInfoModule(VideoInfoModule(this))
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

    fun initView() {

        currentId = arguments.getString("dataId")

        mBaseItems = ArrayList<BaseItem<*>>()
        mFoldableTextView = mrv_recommend.headerView.findViewById<View>(R.id.ftv_content_text) as FoldableTextView
        mrv_recommend.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        mrv_recommend.itemAnimator = DefaultItemAnimator()
        mrv_recommend.itemAnimator.changeDuration = 0
        val itemDecoration = SpaceDecoration(DensityUtil.dip2px(context, 8F))
        itemDecoration.setPaddingEdgeSide(true)
        itemDecoration.setPaddingStart(true)
        itemDecoration.setPaddingHeaderFooter(false)
        mrv_recommend.addItemDecoration(itemDecoration)


        refreshData(false)
    }

    override fun refreshData(isRefresh: Boolean) {

        mPresenter?.getData(currentId)
    }

    override fun refreshSuc(dataObj: Any?) {
        val movieInfo = dataObj as MovieInfo
        tv_empty_msg.visibility = View.GONE
        val sb = "上映时间：" + movieInfo.airTime + "\n" +
                "导演：" + movieInfo.director + "\n" +
                "主演：" + movieInfo.actors + "\n" +
                "剧情简介：" + movieInfo.description

        mFoldableTextView?.setText(sb)
        mBaseItems?.clear()
        val listBeen = movieInfo.list
        for (bean in listBeen?.get(listBeen?.size - 1)?.childList!!) {
            val baseItem = BaseItem<MovieInfo.ChildListBean>()
            baseItem.setData(bean)
            mBaseItems?.add(baseItem)
        }
        if (mAdapter == null) {
            mAdapter = VideoInfoAdapter(this.context)
            mAdapter?.setBaseDatas(mBaseItems)
            mrv_recommend.setAdapter(mAdapter)
            mrv_recommend.addOnItemClickListener(this)
        } else {
            mAdapter?.setBaseDatas(mBaseItems)
        }
    }

    override fun refreshFail(errMsg: String?) {
    }

    override fun showWaitingView() {
    }

    override fun againWaitingView() {
    }

    override fun dismissWaitingView() {
    }

    override fun showMessage(message: String?) {
    }


    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun setData(data: Any?) {
    }

    override fun onItemClick(position: Int, data: BaseItem<*>?, view: View?) {
        var childListBean = data?.data as MovieInfo.ChildListBean
        currentId = childListBean.dataId!!
        refreshData(false)
        // 通知Activity 修改播放器图片
        RxBus.getDefault().postWithCode(RxConstants.LOADED_VIDEO_PIC_CODE, childListBean.pic)
        //通知评论Fragment Id已变更
        RxBus.getDefault().postWithCode(RxConstants.ACCEPT_VIDEO_DATAID, childListBean.dataId)
    }



    override fun onResume() {
        super.onResume()

    }


    override fun onDestroyView() {
        super.onDestroyView()

    }
}