package com.kong.frameapp.modules.main.ui

import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.kong.frameapp.R
import com.kong.frameapp.modules.base.BaseActivity
import com.kong.frameapp.modules.video.ui.VideoMainFragment
import com.kong.frameapp.modules.wynews.ui.NewsMainFragment
import com.kong.frameapp.modules.zhihu.ui.ZhiHuDailyFragment
import kotlinx.android.synthetic.main.home_activity.*
import kotlin.properties.Delegates


/**
 * 应用主页
 */
class HomeActivity : BaseActivity(), BottomNavigationBar.OnTabSelectedListener {

    private var fm: FragmentManager = getSupportFragmentManager();
    private var mCurrentFrag: Fragment? = null


    val fragment0 = ZhiHuDailyFragment()
    val fragment1 = VideoMainFragment()
    val fragment2 = NewsMainFragment()

    var mBottomSheetBehavior: BottomSheetBehavior<FrameLayout> by Delegates.notNull()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        setContentView(R.layout.home_activity)


        initView()
    }


    private fun initView() {

        mBottomSheetBehavior = BottomSheetBehavior.from(fl_bottom_navgation)
        mBottomSheetBehavior.setPeekHeight(0)
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)

        initNavigation()
        switchContent(fragment0)
    }


    private fun initNavigation() {
        bottom_navgation
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE)
                .setMode(BottomNavigationBar.MODE_SHIFTING)
                .addItem(BottomNavigationItem(R.mipmap.ic_tab_home, "知乎日报").setActiveColorResource(R.color.colorPrimary))
                .addItem(BottomNavigationItem(R.mipmap.ic_tab_home, "电影").setActiveColorResource(R.color.transp))
                .addItem(BottomNavigationItem(R.mipmap.ic_tab_home, "网易新闻").setActiveColorResource(R.color.colorPrimary))
                .setFirstSelectedPosition(0)
                .setTabSelectedListener(this)
                .initialise()
    }

    /**
     * 动态添加fragment，不会重复创建fragment
     * @param to 将要加载的fragment
     */
    fun switchContent(to: Fragment) {
        if (mCurrentFrag !== to) {
            if (!to.isAdded) {
                if (mCurrentFrag != null) {
                    fm.beginTransaction().hide(mCurrentFrag).commit()
                }
                fm.beginTransaction()
                        .add(R.id.main_view, to)
                        .commit()
            } else {
                fm.beginTransaction().hide(mCurrentFrag).show(to).commit()
            }
            mCurrentFrag = to
        }
    }


    internal var down_y: Float = 0.toFloat()
    private var drawerOpen = false
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (drawerOpen) {
            return super.dispatchTouchEvent(event)
        }

        //上下滑动时bottomBar滑动显示隐藏
        when (event.action) {
            MotionEvent.ACTION_DOWN -> down_y = event.y
            MotionEvent.ACTION_MOVE -> {
                if (event.y - down_y > 20) {
                    mBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                }
                if (event.y - down_y < -150) {
                    mBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }
            }
            MotionEvent.ACTION_UP -> {
                if (event.y - down_y > 20) {
                    if (mBottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                        mBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                }
                if (event.y - down_y < -150) {
                    if (mBottomSheetBehavior.state != BottomSheetBehavior.STATE_COLLAPSED) {
                        mBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                    }
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    override fun onTabSelected(position: Int) {
        when (position) {
            0 -> {
                switchContent(fragment0)
            }
            1 -> {
                switchContent(fragment1)
            }
            2 -> {
                switchContent(fragment2)
            }
            else -> {
            }
        }
    }

    override fun onTabReselected(position: Int) {
        //todo ddd

    }

    override fun onTabUnselected(position: Int) {
    }


    internal var onViewClick: View.OnClickListener = View.OnClickListener { view ->
        when (view.id) {
//            R.id.button1 -> {
//
//
//            }
//            R.id.button2, R.id.button3 -> {
//
//
//            }


        }
    }


    override fun onDestroy() {
        super.onDestroy()

    }

}
