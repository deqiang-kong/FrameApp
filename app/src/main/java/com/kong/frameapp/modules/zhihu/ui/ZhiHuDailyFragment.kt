package com.kong.frameapp.modules.zhihu.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.FrameLayout
import com.kong.fmklibrary.base.MVPBaseFragment
import com.kong.fmklibrary.controls.loopbander.AutoScrollViewPager
import com.kong.fmklibrary.controls.loopbander.ViewGroupIndicator
import com.kong.fmklibrary.controls.magicrecyclerView.BaseItem
import com.kong.fmklibrary.controls.magicrecyclerView.BaseRecyclerAdapter
import com.kong.fmklibrary.controls.magicrecyclerView.MagicRecyclerView
import com.kong.fmklibrary.di.component.AppComponent
import com.kong.frameapp.R
import com.kong.frameapp.bean.ZhiHuDailyBean
import com.kong.frameapp.bean.ZhiHuStoryInfo
import com.kong.frameapp.injector.component.DaggerZhiHuDailyComponent
import com.kong.frameapp.injector.module.ZhiHuDailyModule
import com.kong.frameapp.modules.zhihu.adapter.ZhihuDailyAdapter
import com.kong.frameapp.modules.zhihu.adapter.ZhihuTopPagerAdapter
import com.kong.frameapp.modules.zhihu.contract.ZhiHuDailyContract
import com.kong.frameapp.modules.zhihu.presenter.ZhiHuDailyFragPresenter
import com.kong.frameapp.util.TagAnimationUtils
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.zhihu_daily_fragment.*
import javax.inject.Inject


/**
 * 知乎日报Fragment
 * Created kongdq on 2017/4/29.
 */
class ZhiHuDailyFragment constructor() : MVPBaseFragment<ZhiHuDailyFragPresenter>(), ZhiHuDailyContract.View, BaseRecyclerAdapter.OnItemClickListener, MagicRecyclerView.OnTagChangeListener {


    private var scrollViewPager: AutoScrollViewPager? = null
    private var viewGroupIndicator: ViewGroupIndicator? = null


    internal var loadMoreFlag: Boolean = false
    private var mBaseItems: ArrayList<BaseItem<*>>? = null

    private val mDisposable: Disposable? = null

    @Inject
    lateinit var mTopPagerAdapter: ZhihuTopPagerAdapter
    @Inject
    lateinit var mZhihuDailyAdapter: BaseRecyclerAdapter


    internal var mLinearLayoutManager: LinearLayoutManager? = null


    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerZhiHuDailyComponent
                .builder()
                .appComponent(appComponent)
                .zhiHuDailyModule(ZhiHuDailyModule(this))
                .build().inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.zhihu_daily_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mParent = view
        mActivity = activity

        initView()
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun setData(data: Any?) {
    }


    override fun refreshSuc(dataObj: Any?) {
        val mZhiHuDailyBean = dataObj as ZhiHuDailyBean

        //配置顶部故事
        if (mTopPagerAdapter == null) {
            mTopPagerAdapter = ZhihuTopPagerAdapter(this, mZhiHuDailyBean.top_stories)
        } else {
            mTopPagerAdapter!!.resetData(mZhiHuDailyBean.top_stories)
            mTopPagerAdapter!!.notifyDataSetChanged()
        }
        scrollViewPager!!.adapter = mTopPagerAdapter
        viewGroupIndicator!!.setParent(scrollViewPager)

        setDataSource(mZhiHuDailyBean, 0)
        if (mZhihuDailyAdapter == null) {
            mZhihuDailyAdapter = ZhihuDailyAdapter(this)
            mZhihuDailyAdapter!!.setBaseDatas(mBaseItems)

        } else {
            if (mBaseItems!!.size != 0) {
                mZhihuDailyAdapter!!.setBaseDatas(mBaseItems)
            }
        }
        mRefresh.isRefreshing = false
    }

    override fun refreshFail(errMsg: String?) {
    }

    override fun onItemClick(position: Int, data: BaseItem<*>?, view: View?) {
        //跳转到其他界面
        val story = data?.data as ZhiHuStoryInfo
        val bundle = Bundle()
        val intent = Intent(this.mActivity, ZhiHuStoryDetailActivity::class.java)
        bundle.putString("title", story.title)
        bundle.putInt("id", story.id)
        intent.putExtras(bundle)
        startActivity(intent)
    }


    override fun refreshData(isRefresh: Boolean) {
        mPresenter?.getData(isRefresh)
    }

    override fun showWaitingView() {
    }

    override fun againWaitingView() {
    }

    override fun dismissWaitingView() {
    }


    override fun showMessage(message: String?) {
    }


    protected fun initView() {
        mRefresh.setOnRefreshListener {
            // 刷新，数据请求
            refreshData(true)
            Handler().postDelayed({ mRefresh.isRefreshing = false }, 4000)
        }

        mBaseItems = ArrayList<BaseItem<*>>()
        mMagicRecyclerView.itemAnimator = DefaultItemAnimator()
        mMagicRecyclerView.itemAnimator.changeDuration = 0

        mLinearLayoutManager = LinearLayoutManager(this.getContext())
        mMagicRecyclerView.layoutManager = mLinearLayoutManager
        mMagicRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (mMagicRecyclerView.refreshAble()) {
                    mRefresh.isEnabled = true
                }
                if (mMagicRecyclerView.loadAble()) {
                    loadMoreData()
                }
                if (mMagicRecyclerView.tagGone() && tv_tag.visibility == View.VISIBLE) {
                    hideTagAnim(tv_tag);
                    tv_tag.setVisibility(View.GONE);
                }

            }
        })

        val headerView = mMagicRecyclerView.headerView as FrameLayout
        scrollViewPager = headerView.findViewById(R.id.scroll_pager)
        viewGroupIndicator = headerView.findViewById(R.id.scroll_pager_indicator)

        mMagicRecyclerView.addOnTagChangeListener(this)
        mMagicRecyclerView.setAdapter(mZhihuDailyAdapter)
        //实质是是对 adapter 设置点击事件所以需要在设置 adapter 之后调用
        mMagicRecyclerView.addOnItemClickListener(this)

        refreshData(false)
    }


    private fun setDataSource(mZhiHuDailyBean: ZhiHuDailyBean, type: Int) {
        if (type == 0)
            mBaseItems!!.clear()

        //配置底部列表故事
        for (story in mZhiHuDailyBean.stories) {

            val baseItem = BaseItem<ZhiHuStoryInfo>()
            baseItem.setData(story)
            mBaseItems!!.add(baseItem)
        }
    }


    override fun loadMoreData() {
        if (!loadMoreFlag) {
            loadMoreFlag = true
            mPresenter.loadMoreData()
            // Toast.makeText(mActivity, "加载更多数据", Toast.LENGTH_SHORT).show();
        }
    }

    override fun loadMoreSuc(dataObj: Any) {
        loadMoreFlag = false
        val mZhiHuDailyBean = dataObj as ZhiHuDailyBean
        setDataSource(mZhiHuDailyBean, 1)
        mZhihuDailyAdapter!!.setBaseDatas(mBaseItems)
    }

    override fun loadMoreFail(errMsg: String) {
        loadMoreFlag = false
    }


    //    @Override
    //    public void onHiddenChanged(boolean hidden) {
    //        if (hidden && mRefresh.isRefreshing()) { // 隐藏的时候停止 SwipeRefreshLayout 转动
    //            mRefresh.setRefreshing(false);
    //        }
    //        if (!hidden) {
    //            RxBus.getDefault()
    //                    .toObservableWithCode(RxConstants.BACK_PRESSED_CODE, String.class)
    //                    .subscribeWith(new Observer<String>() {
    //                        @Override
    //                        public void onSubscribe(Disposable d) {
    //                            mDisposable = d;
    //                        }
    //
    //                        @Override
    //                        public void onNext(String value) {
    //                            if (value.equals(RxConstants.BACK_PRESSED_DATA) && mMagicRecyclerView != null) {
    //                                //滚动到顶部
    //                                mLinearLayoutManager.smoothScrollToPosition(mMagicRecyclerView, null, 0);
    //                            }
    //                        }
    //
    //                        @Override
    //                        public void onError(Throwable e) {
    //
    //                        }
    //
    //                        @Override
    //                        public void onComplete() {
    //
    //                        }
    //                    });
    //        } else {
    //            if (mDisposable != null && !mDisposable.isDisposed()) {
    //                mDisposable.dispose();
    //            }
    //        }
    //    }

    override fun onChange(newTag: String) {
        if (tv_tag.visibility == View.GONE) {
            tv_tag.visibility = View.VISIBLE
            showTagAnim(tv_tag)
        }
//        val year = Integer.parseInt(newTag.substring(0, 4))
//        val mon = Integer.parseInt(newTag.substring(4, 6))
//        val day = Integer.parseInt(newTag.substring(6, 8))
//        val calendar = Calendar.getInstance()
//        calendar.set(year, mon - 1, day)
//        val df = SimpleDateFormat.getDateInstance()
//        tv_tag.text = df.format(calendar.time)
    }

    private fun hideTagAnim(view: View) {
        val animation = TagAnimationUtils.moveToViewTop()
        view.animation = animation
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                view.visibility = View.GONE
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
    }

    private fun showTagAnim(view: View) {
        val animation = TagAnimationUtils.moveToViewLocation()
        view.animation = animation
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                view.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animation) {

            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
    }

}
