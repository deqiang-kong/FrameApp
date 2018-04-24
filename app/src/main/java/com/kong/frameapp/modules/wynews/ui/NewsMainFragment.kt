package com.kong.frameapp.modules.wynews.ui

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.kong.frameapp.R
import com.kong.frameapp.bean.NewsChannelBean
import com.kong.frameapp.modules.base.BaseFragment
import com.kong.frameapp.modules.base.BaseFragmentAdapter
import com.kong.framesamples.modules.wynews.NewsConstants
import kotlinx.android.synthetic.main.news_content.*
import kotlinx.android.synthetic.main.news_main_layout.*


/**
 * 网易新闻Fragment
 * Created kongdq on 2017/7/29.
 */
class NewsMainFragment constructor() : BaseFragment() {


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setHasOptionsMenu(true);
//    }


    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.news_main_layout, container, false)

        return view
    }

    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mActivity
        initData()
    }


//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//        menu.add("Menu 1a").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
//        menu.add("Menu 1b").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
//        //getMenuInflater().inflate(mMenuId, menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//       // Toast.makeText(activity, "index is" + getShownIndex() + " && menu text is " + item.title, 1000).show()
//        return super.onOptionsItemSelected(item)
//    }


    override fun initData() {


        toolbar.title="新闻"
       // mActivity.getMenuInflater().inflate(R.menu.menu_news, menu)
        initFragment()
    }


    fun initFragment() {
        val labels = ArrayList<String>()
        labels.add(NewsConstants.NEWS_TYPE_HEADLINE)
        labels.add( NewsConstants.NEWS_TYPE_TEC)
        labels.add( NewsConstants.NEWS_TYPE_SPORT)
        labels.add( NewsConstants.NEWS_TYPE_HEALTH)
        labels.add( NewsConstants.NEWS_TYPE_DADA)
        labels.add( NewsConstants.NEWS_TYPE_MILITARY)
        labels.add( NewsConstants.NEWS_TYPE_TRAVEL)

        var channelList = ArrayList<NewsChannelBean.ChannelInfo>()
        var fragmentList = ArrayList<Fragment>()

        for (item in labels) {
            val channelObj = NewsChannelBean.ChannelInfo(channelName = item)
            channelObj.channelId = getNewsTypeCode(item)

            channelList.add(channelObj)
        }


        for (item in channelList) {
            val arg = Bundle()
            arg.putString("channelId", item.channelId)
            arg.putString("channelName", item.channelName)

            val fragment = NewsListFragment().newInstance(arg)
            //fragment.setArguments(arg)

            fragmentList.add(fragment)
        }


        if (viewpager.adapter == null) {
            // 初始化ViewPager
            val adapter = BaseFragmentAdapter(childFragmentManager, fragmentList, labels)
            viewpager.setAdapter(adapter)
        } else {
            val adapter = viewpager.getAdapter() as BaseFragmentAdapter
            adapter.updateFragments(fragmentList, labels)
        }

        //viewpager.setOffscreenPageLimit(3)
        viewpager.setCurrentItem(0, false)
        tabs.setupWithViewPager(viewpager)
        tabs.setScrollPosition(0, 0f, true)
        // 根据Tab的长度动态设置TabLayout的模式
        dynamicSetTabLayoutMode(tabs)
        //tabs.setTabMode(TabLayout.MODE_SCROLLABLE)
    }


    /**
     * 根据Tab合起来的长度动态修改tab的模式

     * @param tabLayout TabLayout
     */
    fun dynamicSetTabLayoutMode(tabLayout: TabLayout) {
        var tabTotalWidth = 0
        for (i in 0..tabLayout.childCount - 1) {
            val view = tabLayout.getChildAt(i)
            view.measure(0, 0)
            tabTotalWidth += view.measuredWidth
        }
        // 获取屏幕宽高
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val screenSize = Point()
        wm.defaultDisplay.getSize(screenSize)
        if (tabTotalWidth <= screenSize.x) {
            tabLayout.tabGravity = TabLayout.GRAVITY_FILL
            tabLayout.tabMode = TabLayout.MODE_FIXED
        } else {
            tabLayout.tabGravity = TabLayout.GRAVITY_CENTER
            tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        }
    }
    fun getNewsTypeCode(title: String): String =
            when (title) {
                NewsConstants.NEWS_TYPE_HEADLINE -> NewsConstants.NEWS_TYPE_CODE_HEADLINE
                NewsConstants.NEWS_TYPE_TEC -> NewsConstants.NEWS_TYPE_CODE_TEC
                NewsConstants.NEWS_TYPE_SPORT -> NewsConstants.NEWS_TYPE_CODE_SPORT
                NewsConstants.NEWS_TYPE_HEALTH -> NewsConstants.NEWS_TYPE_CODE_HEALTH
                NewsConstants.NEWS_TYPE_DADA -> NewsConstants.NEWS_TYPE_CODE_DADA
                NewsConstants.NEWS_TYPE_MILITARY -> NewsConstants.NEWS_TYPE_CODE_MILITARY
                NewsConstants.NEWS_TYPE_TRAVEL -> NewsConstants.NEWS_TYPE_CODE_TRAVEL
                else -> NewsConstants.NEWS_TYPE_CODE_HEADLINE
            }

}
