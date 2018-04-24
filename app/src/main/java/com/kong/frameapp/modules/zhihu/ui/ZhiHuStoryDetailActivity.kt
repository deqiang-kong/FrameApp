package com.kong.frameapp.modules.zhihu.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import com.kong.fmklibrary.base.MVPBaseActivity
import com.kong.fmklibrary.di.component.AppComponent
import com.kong.frameapp.R
import com.kong.frameapp.bean.ZhiHuStoryDetailBean
import com.kong.frameapp.injector.component.DaggerZhiHuStoryDetailComponent
import com.kong.frameapp.injector.module.ZhiHuStoryDetailModule
import com.kong.frameapp.modules.zhihu.contract.ZhiHuStoryDetailContract
import com.kong.frameapp.modules.zhihu.presenter.ZhiHuStoryDetailPresenter
import com.kong.frameapp.util.ColorUtils
import com.kong.frameapp.util.PictureLoadUtil
import com.kong.frameapp.util.WebUtils
import kotlinx.android.synthetic.main.zhihu_story_detail.*
import kotlinx.android.synthetic.main.zhihu_story_info.*


/**
 * Created by kaipai on 17/10/13.
 */
class ZhiHuStoryDetailActivity : MVPBaseActivity<ZhiHuStoryDetailPresenter>(), ZhiHuStoryDetailContract.View {

    var shareUrl: String = ""
    internal var width: Int = 0
    internal var heigh: Int = 0
    internal var storyId: String = ""


    override fun setupActivityComponent(appComponent: AppComponent?) {

        DaggerZhiHuStoryDetailComponent.builder()
                .appComponent(appComponent)
                .zhiHuStoryDetailModule(ZhiHuStoryDetailModule(this))
                .build()
                .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        setContentView(R.layout.zhihu_story_detail)
        return 0
    }

    override fun initData(savedInstanceState: Bundle?) {

        val bundle = intent.extras
        storyId = bundle!!.getInt("id").toString()
        val title = bundle.getString("title")

        refreshData(true)
        toolbar_title.setText(title)
        toolbar_title.setSelected(true)

        val dm = resources.displayMetrics
        width = dm.widthPixels
        heigh = width * 3 / 5;


        fab.setOnClickListener { view ->
            if (!TextUtils.isEmpty(shareUrl)) {
                showShare(shareUrl, toolbar_title.getText().toString())
            }
        }

        toolbar.setOnClickListener { view ->
            finishAfterTransition()
        }

    }

    override fun refreshData(isRefresh: Boolean) {

        mPresenter.getData(storyId)
    }

    override fun showWaitingView() {
    }

    override fun againWaitingView() {
    }

    override fun dismissWaitingView() {
    }


    override fun showMessage(message: String?) {
    }



    fun showShare(url: String, shareTitle: String) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, url)
        shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(Intent.createChooser(shareIntent, shareTitle))
    }


    //字符串检查，为空赋默认值
    fun strSafe(s: String?): String {
        if (s == null) {
            return ""
        }
        s?.toUpperCase()//等同于下面的语句 类调的实例调用方法 加上? 则不是空则正常调用 是空值则返回空
        if (s != null) s.toUpperCase() else null

        return s
    }

    override fun refreshSuc(dataObj: Any) {
        val mZhihuStoryDetailBean = dataObj as ZhiHuStoryDetailBean

        shareUrl = strSafe(mZhihuStoryDetailBean.share_url)
        var statusBarColor = getWindow().getStatusBarColor()
        toolbar.setBackgroundColor(ColorUtils.modifyAlpha(statusBarColor, 0.5f))

        val image = mZhihuStoryDetailBean.image

        PictureLoadUtil.getInstance().displayImageView(applicationContext, image, story_img)

        startPostponedEnterTransition()

        val url = mZhihuStoryDetailBean.share_url
        val isEmpty = TextUtils.isEmpty(mZhihuStoryDetailBean.body)
        val mBody = mZhihuStoryDetailBean.body
        val scc = mZhihuStoryDetailBean.css

        //如果返回的html body为空则直接 load url
        if (isEmpty) {
            zhihudaily_webview.loadUrl(url)
        } else {
            val data = WebUtils.buildHtmlWithCss(mBody, scc, false)
            zhihudaily_webview.loadDataWithBaseURL(WebUtils.BASE_URL, data, WebUtils.MIME_TYPE, WebUtils.ENCODING, WebUtils.FAIL_URL)
        }

    }


    override fun refreshFail(errMsg: String) {
    }

}