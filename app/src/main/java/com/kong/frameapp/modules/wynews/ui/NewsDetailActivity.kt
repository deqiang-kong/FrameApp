package com.kong.frameapp.modules.wynews.ui

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.webkit.WebSettings
import com.kong.fmklibrary.di.component.AppComponent
import com.kong.frameapp.R
import com.kong.frameapp.bean.NewsContent
import com.kong.frameapp.injector.component.DaggerNewsDetailComponent
import com.kong.frameapp.injector.module.NewsDetailModule
import com.kong.frameapp.modules.base.SlideBackActivity
import com.kong.frameapp.modules.wynews.contract.NewsDetailContract
import com.kong.frameapp.modules.wynews.presenter.NewsDetailPresenter
import com.kong.frameapp.util.PictureLoadUtil
import kotlinx.android.synthetic.main.news_detail_info.*
import kotlinx.android.synthetic.main.news_detail_layout.*


/**
 * 新闻详情
 * Created by kaipai on 17/10/13.
 */
class NewsDetailActivity  : SlideBackActivity<NewsDetailPresenter>(), NewsDetailContract.View {


    override fun setupActivityComponent(appComponent: AppComponent?) {
        DaggerNewsDetailComponent.builder()
                .appComponent(appComponent)
                .newsDetailModule(NewsDetailModule(this))
                .build()
                .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.news_detail_layout
    }

    override fun initData(savedInstanceState: Bundle?) {

        val bundle = intent.extras

        val title = bundle.getString("title")
        val id = bundle.getString("id")
        val imgUrl = bundle.getString("imgUrl")
        val url = bundle.getString("url")
        mPresenter.newsId=id

        PictureLoadUtil.getInstance().displayImageView(applicationContext, imgUrl,iv_detail_photo )

        initView()


        refreshData(false)
    }


    fun initView(){
        toolbar_layout.title = getString(R.string.news_detail)
        toolbar_layout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.accent))
        toolbar_layout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.material_white))
        toolbar.setOnClickListener { view ->
            finishAfterTransition()
        }
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
        val newsContent = dataObj as NewsContent

        tv_detail_title.text=newsContent.title
        tv_detail_from.text=newsContent.ptime

        //TODO 根据类型判断，显示图集，可视频播放，


        var htmlData = newsContent.body

        htmlData = htmlData.replace("&amp;".toRegex(), "")
        htmlData = htmlData.replace("quot;".toRegex(), "\"")
        htmlData = htmlData.replace("lt;".toRegex(), "<")
        htmlData = htmlData.replace("gt;".toRegex(), ">")
        //设置图片满屏显示
        htmlData = htmlData.replace("<img", "<img style=\"width:100%;height:auto\"")
        //自适应屏幕
        var dd=webView.title;
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN)
        webView.getSettings().setLoadWithOverviewMode(true)
        webView.loadDataWithBaseURL(null, htmlData, "text/html", "utf-8", null)

    }



    override fun refreshFail(errMsg: String) {
    }

}