package com.kong.frameapp.modules.wynews.ui

import android.content.Intent
import android.os.Bundle
import com.kong.fmklibrary.base.MVPBaseActivity
import com.kong.fmklibrary.di.component.AppComponent
import com.kong.frameapp.R
import com.kong.frameapp.injector.component.DaggerNewsChannelComponent
import com.kong.frameapp.injector.module.NewsChannelModule
import com.kong.frameapp.modules.wynews.contract.NewsChannelContract
import com.kong.frameapp.modules.wynews.presenter.NewsChannelPresenter


/**
 * 新闻频道管理
 * Created by kaipai on 17/10/13.
 */
class NewsChannelActivity : MVPBaseActivity<NewsChannelPresenter>(), NewsChannelContract.View {

    var shareUrl: String = ""
    internal var width: Int = 0
    internal var heigh: Int = 0
    internal var storyId: String = ""


    override fun setupActivityComponent(appComponent: AppComponent?) {
        DaggerNewsChannelComponent.builder()
                .appComponent(appComponent)
                .newsChannelModule(NewsChannelModule(this))
                .build()
                .inject(this)
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

    }


    override fun refreshFail(errMsg: String) {
    }

}