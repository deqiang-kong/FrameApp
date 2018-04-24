package com.kong.frameapp.modules.video.ui

import android.animation.ArgbEvaluator
import android.animation.FloatEvaluator
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.text.TextUtils
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import com.kong.frameapp.R
import com.kong.frameapp.bean.MovieInfo
import com.kong.frameapp.modules.base.BaseActivity
import com.kong.frameapp.modules.video.adapter.NavigationAdapter
import com.kong.frameapp.rxbus.RxBus
import com.kong.frameapp.rxbus.RxConstants
import com.kong.frameapp.util.ColorUtils
import com.kong.frameapp.util.PictureLoadUtil
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.video_detail_layout.*
import java.util.*


/**
 * 视频详情界面
 * Created by kongdq on 17/9/12.
 */
class VideoDetailActivity : BaseActivity(), ViewPager.OnPageChangeListener {


    lateinit var mDisposable: Disposable
    lateinit var mPicDisposable: Disposable
    lateinit var argbEvaluator: ArgbEvaluator
    lateinit var floatEvaluator: FloatEvaluator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.video_detail_layout)


        initView()
        initRxBus()
    }

    private fun initView() {
        val title = intent.getStringExtra("titleName")
        val idOrUrl = intent.getStringExtra("id")
        val pic = intent.getStringExtra("img_url")

        toolbar11.title = title
        // toolbar_title.text = title
        //setSupportActionBar(toolbar11);
        toolbar11.setNavigationOnClickListener(View.OnClickListener { finishAfterTransition() })

        jc_video_player.backButton.setVisibility(View.GONE)
        jc_video_player.titleTextView.setVisibility(View.GONE)
        jc_video_player.tinyBackImageView.setVisibility(View.GONE)

        jc_video_player.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP)
        if (!TextUtils.isEmpty(pic)) {
            //PictureLoadUtil.getInstance().displayImageView(applicationContext, pic, jc_video_player.thumbImageView)
            //mToolbar颜色修改
            titleBarColor(pic, jc_video_player.thumbImageView)
        }

        val fragments = ArrayList<Fragment>()

        //将首次需要加载的电影Id传递过去
        val videoInfoFrag = VideoInfoFragment()
        val arg = Bundle()
        arg.putString("dataId", idOrUrl)
        videoInfoFrag.setArguments(arg)
        val videoCommentFrag = VideoCommentFragment()
        videoCommentFrag.setArguments(arg)
        fragments.add(videoInfoFrag)
        fragments.add(videoCommentFrag)

        var mPagerAdapter = NavigationAdapter(supportFragmentManager, null, fragments)
        vp_video_info.adapter = mPagerAdapter
        vp_video_info.addOnPageChangeListener(this)
        vp_video_info.currentItem = 0
        argbEvaluator = ArgbEvaluator()
        floatEvaluator = FloatEvaluator()
    }


    private fun titleBarColor(imgUrl: String, imgView: ImageView) {
        PictureLoadUtil.getInstance().getImageBitmap(this.applicationContext, imgUrl, imgView, object : PictureLoadUtil.PicBitmapCallBack {
            override fun picBitmap(bmp: Bitmap?) {

                var color = bmp?.getPixel(bmp.width / 2, 100)
                //toolbar11.setBackgroundColor(color!!)
                toolbar11.setBackgroundColor(ColorUtils.modifyAlpha(color!!, 0.5f))
                getWindow().setStatusBarColor(color!!)
            }
        })

    }

    private fun initRxBus() {
        // 点击推荐视频跳转观察者
         RxBus
                .getDefault()
                .toObservableWithCode(RxConstants.LOADED_DATA_CODE, MovieInfo::class.java)
                .subscribe(object : Observer<MovieInfo> {
                    override fun onSubscribe(d: Disposable) {
                        mDisposable = d
                    }

                    override fun onNext(value: MovieInfo) {
                        toolbar11.setTitle(value.title)
                        // toolbar_title.setText(value.title)
                        jc_video_player.setUp(value.getVideoUrl(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, value.title);
                        //jc_video_player.setUp(value.getVideoUrl(), JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, value.title)
                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {

                    }
                })
        RxBus
                .getDefault()
                .toObservableWithCode(RxConstants.LOADED_VIDEO_PIC_CODE, String::class.java)
                .subscribe(object : Observer<String> {
                    override fun onSubscribe(d: Disposable) {
                        mPicDisposable = d
                    }

                    override fun onNext(value: String) {
                        if (!TextUtils.isEmpty(value)) {
                            //PictureLoadUtil.getInstance().displayImageView(applicationContext, value, jc_video_player.thumbImageView)

                            titleBarColor(value, jc_video_player.thumbImageView)
                        }
                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {

                    }
                })
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        changePage(position, positionOffset)
    }

    override fun onPageSelected(position: Int) {

    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    private fun changePage(position: Int, positionOffset: Float) {
        if (position == 0) {
            // 字体颜色
            tv_tab_description.setTextColor(ContextCompat.getColor(this, R.color.white_FFFFFF))
            val stepsColor = argbEvaluator.evaluate(positionOffset, ContextCompat.getColor(this, R.color.white_FFFFFF),
                    ContextCompat.getColor(this, R.color.grey_607D8B)) as Int
            tv_tab_description.setTextColor(stepsColor)
            tv_tab_comment.setTextColor(ContextCompat.getColor(this, R.color.grey_607D8B))
            val sleepColor = argbEvaluator.evaluate(positionOffset, ContextCompat.getColor(this, R.color.grey_607D8B),
                    ContextCompat.getColor(this, R.color.white_FFFFFF)) as Int
            tv_tab_comment.setTextColor(sleepColor)
            // 字体大小
            tv_tab_description.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
            val stepsSize = floatEvaluator.evaluate(positionOffset, 18, 14)!!
            tv_tab_description.setTextSize(TypedValue.COMPLEX_UNIT_SP, stepsSize)
            tv_tab_comment.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
            val sleepSize = floatEvaluator.evaluate(positionOffset, 14, 18)!!
            tv_tab_comment.setTextSize(TypedValue.COMPLEX_UNIT_SP, sleepSize)
        } else {
            // 字体颜色
            tv_tab_description.setTextColor(ContextCompat.getColor(this, R.color.grey_607D8B))
            val stepsColor = argbEvaluator.evaluate(positionOffset, ContextCompat.getColor(this, R.color.grey_607D8B),
                    ContextCompat.getColor(this, R.color.white_FFFFFF)) as Int
            tv_tab_description.setTextColor(stepsColor)
            tv_tab_comment.setTextColor(ContextCompat.getColor(this, R.color.white_FFFFFF))
            val sleepColor = argbEvaluator.evaluate(positionOffset, ContextCompat.getColor(this, R.color.white_FFFFFF),
                    ContextCompat.getColor(this, R.color.grey_607D8B)) as Int
            tv_tab_comment.setTextColor(sleepColor)
            // 字体大小
            tv_tab_description.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
            val stepsSize = floatEvaluator.evaluate(positionOffset, 14, 18)!!
            tv_tab_description.setTextSize(TypedValue.COMPLEX_UNIT_SP, stepsSize)
            tv_tab_comment.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
            val sleepSize = floatEvaluator.evaluate(positionOffset, 18, 14)!!
            tv_tab_comment.setTextSize(TypedValue.COMPLEX_UNIT_SP, sleepSize)
        }
    }

    override fun onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return
        }
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()

    }

    override fun onDestroy() {
        if (mDisposable != null && !mDisposable.isDisposed) {
            mDisposable.dispose()
            mPicDisposable.dispose()
        }
        super.onDestroy()
        JCVideoPlayer.releaseAllVideos()
    }
}