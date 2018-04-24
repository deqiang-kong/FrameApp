package com.kong.frameapp.modules.customnews.ui

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import android.widget.Toast
import com.kong.fmklibrary.base.MVPBaseActivity
import com.kong.fmklibrary.di.component.AppComponent
import com.kong.frameapp.R
import com.kong.frameapp.bean.MyNewsChannelBean
import com.kong.frameapp.injector.component.DaggerCustomNewsChannelComponent
import com.kong.frameapp.injector.module.CustomNewsChannelModule
import com.kong.frameapp.modules.customnews.contract.NewsChannelContract
import com.kong.frameapp.modules.customnews.presenter.NewsChannelPresenter
import com.kong.frameapp.widget.channelview.ChannelAdapter
import com.kong.frameapp.widget.channelview.helper.ItemDragHelperCallback
import kotlinx.android.synthetic.main.news_channel_layout.*


/**
 * 新闻频道管理
 * Created by kaipai on 17/10/13.
 */
class CustomNewsChannelActivity : MVPBaseActivity<NewsChannelPresenter>(), NewsChannelContract.View {

    var shareUrl: String = ""
    internal var width: Int = 0
    internal var heigh: Int = 0
    internal var storyId: String = ""


    override fun setupActivityComponent(appComponent: AppComponent?) {
        DaggerCustomNewsChannelComponent.builder().appComponent(appComponent).customNewsChannelModule(CustomNewsChannelModule(this)).build().inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        setContentView(R.layout.news_channel_layout)
        return 0
    }

    override fun initData(savedInstanceState: Bundle?) {


//        fab.setOnClickListener { view ->
//            if (!TextUtils.isEmpty(shareUrl)) {
//                showShare(shareUrl, toolbar_title.getText().toString())
//            }
//        }
//
//        toolbar.setOnClickListener { view ->
//            finishAfterTransition()
//        }

        refreshData(false)
    }

    override fun refreshData(isRefresh: Boolean) {

        mPresenter.getData(isRefresh)
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
        var myNewsChannelBean = dataObj as MyNewsChannelBean

        initData(myNewsChannelBean)
    }


    override fun refreshFail(errMsg: String) {
    }


    private fun initData(bean: MyNewsChannelBean) {

        val manager = GridLayoutManager(this, 4)
        recyclerChannels.setLayoutManager(manager)

        val callback = ItemDragHelperCallback()
        val helper = ItemTouchHelper(callback)
        helper.attachToRecyclerView(recyclerChannels)

        val adapter = ChannelAdapter(this, helper, bean.myChannelList, bean.otherChannelList)
        recyclerChannels.setAdapter(adapter)


        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val viewType = adapter.getItemViewType(position)
                return if (viewType == ChannelAdapter.TYPE_MY || viewType == ChannelAdapter.TYPE_OTHER) 1 else 4
            }
        }

        adapter.setOnMyChannelItemClickListener { v, position ->
           // Toast.makeText(this@ChannelActivity, myItems.get(position).name, Toast.LENGTH_SHORT).show()

        }

    }
}