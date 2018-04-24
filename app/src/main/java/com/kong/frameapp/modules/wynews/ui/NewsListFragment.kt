package com.kong.frameapp.modules.wynews.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kong.fmklibrary.base.MVPBaseFragment
import com.kong.fmklibrary.di.component.AppComponent
import com.kong.frameapp.R
import com.kong.frameapp.bean.NewsBean
import com.kong.frameapp.injector.component.DaggerNewsListComponent
import com.kong.frameapp.injector.module.NewsListModule
import com.kong.frameapp.modules.wynews.adapter.NewsListAdapter
import com.kong.frameapp.modules.wynews.contract.NewsListContract
import com.kong.frameapp.modules.wynews.presenter.NewsListFragPresenter
import com.kong.frameapp.widget.selfdomadapter.BaseAdapterItem
import com.kong.frameapp.widget.selfdomadapter.OnRecyclerActionListener
import com.kong.frameapp.widget.selfdomadapter.SpacesItemDecoration
import kotlinx.android.synthetic.main.news_list_layout.*


/**
 * 新闻列表Fragment
 * Created kongdq on 2017/7/29.
 */
class NewsListFragment constructor() : MVPBaseFragment<NewsListFragPresenter>(), NewsListContract.View, OnRecyclerActionListener {


    var channelName: String? = null
    var channelId: String? = null

    private var mBaseItems: ArrayList<BaseAdapterItem<*>>? = null
    var listAdapter: NewsListAdapter? = null

    //internal var mLinearLayoutManager: LinearLayoutManager? = null


    fun newInstance(bundle: Bundle): NewsListFragment {
        val myFragment = NewsListFragment()
        myFragment.setArguments(bundle)
        return myFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            channelId = arguments.getString("channelId")
            channelName = arguments.getString("channelName")
            mParent = view
            mActivity = activity
        }
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerNewsListComponent.builder().appComponent(appComponent).newsListModule(NewsListModule(this)).build().inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.news_list_layout, container, false)
    }

    override fun initData(savedInstanceState: Bundle?) {

        initView()
        mPresenter.channelId = channelId!!
        refreshData(false)
    }

    override fun setData(data: Any?) {

    }

    fun initView() {
        refresh_layout.setRefreshListener {
            refreshData(true)
            Handler().postDelayed({
                if (refresh_layout != null) refresh_layout.refreshFinish()
            }, 4000)

        }

        showWaitingView()
        mBaseItems = ArrayList<BaseAdapterItem<*>>()
        listAdapter = NewsListAdapter(this.context, mBaseItems)
        listAdapter!!.setRecyclerActionListener(this)

        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        recycler_view.setLayoutManager(linearLayoutManager)
        recycler_view.addItemDecoration(SpacesItemDecoration(4))
        recycler_view.setItemAnimator(DefaultItemAnimator())
        recycler_view.getItemAnimator().setAddDuration(250)
        recycler_view.getItemAnimator().setMoveDuration(250)
        recycler_view.getItemAnimator().setChangeDuration(250)
        recycler_view.getItemAnimator().setRemoveDuration(250)
        recycler_view.setAdapter(listAdapter)
    }


    override fun refreshSuc(dataObj: Any?) {
        val newsList = dataObj as ArrayList<NewsBean>

        setDataSource(newsList, true)

        if (listAdapter == null) {
            listAdapter = NewsListAdapter(this.context, mBaseItems)
            recycler_view.setAdapter(listAdapter)
        } else {
            if (mBaseItems!!.size != 0) {
                listAdapter!!.setData(mBaseItems)
            }
        }
        refresh_layout.refreshFinish()
        listAdapter!!.enableLoadMore(true)
        listAdapter!!.mMoreItemCount = mBaseItems!!.size
        dismissWaitingView()
    }

    override fun refreshFail(errMsg: String?) {
        refresh_layout.refreshFinish()
        listAdapter!!.enableLoadMore(false)
        listAdapter!!.showEmptyView(true, errMsg!!)
        listAdapter!!.notifyDataSetChanged()
        dismissWaitingView()
    }


    override fun loadMoreSuc(dataObj: Any) {
        val newsList = dataObj as ArrayList<NewsBean>

        setDataSource(newsList, false)
        listAdapter!!.setData(mBaseItems)
        listAdapter!!.loadMoreSuccess()
        //通知
        listAdapter!!.mMoreItemCount = mBaseItems!!.size
        //判断数据是否加载到尽头
        if (newsList == null || newsList.size == 0) {
            listAdapter!!.enableLoadMore(null)
            //提示所有数据加载完成
            //toast("全部加载完毕")
        }
    }

    override fun loadMoreFail(errMsg: String) {
        listAdapter!!.loadMoreFailed(errMsg)
    }

    private fun setDataSource(list: ArrayList<NewsBean>, isRefres: Boolean) {
        if (isRefres) mBaseItems!!.clear()

        //配置底部列表故事
        for (bean in list) {

            if (bean.url == null) continue

            val baseItem = BaseAdapterItem<NewsBean>()
            baseItem.data = bean
            mBaseItems!!.add(baseItem)
        }
    }


    override fun onItemClick(position: Int, data: BaseAdapterItem<*>?) {
        val bean = data?.data as NewsBean
        val bundle = Bundle()
        val intent = Intent(this.mActivity, NewsDetailActivity::class.java)
        bundle.putString("title", bean.title)
        bundle.putString("id", bean.docid)
        bundle.putString("imgUrl", bean.imgsrc)
        bundle.putString("url", bean.url)
        //D2QEEEP30001899O
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun onLoadMore() {
        loadMoreData()

    }

    override fun onEmptyClick() {
        //空数据，刷新
        showWaitingView()
        refreshData(true)
        listAdapter!!.showEmptyView(false, "")
    }


    override fun refreshData(isRefresh: Boolean) {
        mPresenter?.getData(isRefresh)
    }


    override fun loadMoreData() {
        mPresenter?.loadMoreData()
    }


    override fun showWaitingView() {
        tpl_view.play()
    }

    override fun againWaitingView() {
    }

    override fun dismissWaitingView() {
        tpl_view.stop()
    }


    override fun showMessage(message: String?) {
    }


}
