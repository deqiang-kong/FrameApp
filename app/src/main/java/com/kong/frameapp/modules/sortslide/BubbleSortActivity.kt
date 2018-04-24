package com.kong.frameapp.modules.sortslide

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.WindowManager
import com.kong.frameapp.R
import com.kong.frameapp.modules.base.BaseActivity
import kotlinx.android.synthetic.main.sort_detail_info.*
import kotlinx.android.synthetic.main.sort_detail_layout.*


/**
 * 冒泡排序过程展示
 * Created by kongdq on 17/10/13.
 */
class BubbleSortActivity : BaseActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        setContentView(R.layout.sort_detail_layout)



        initView()
        iv_detail_photo.setBackgroundResource(R.drawable.ic_header)
    }


//    override fun initView(savedInstanceState: Bundle?): Int {
//        return R.layout.news_detail_layout
//    }
//
//    override fun initData(savedInstanceState: Bundle?) {
//
//        val bundle = intent.extras
//
//        val title = bundle.getString("title")
//        val id = bundle.getString("id")
//        val imgUrl = bundle.getString("imgUrl")
//        val url = bundle.getString("url")
//
//
//        PictureLoadUtil.getInstance().displayImageView(applicationContext, imgUrl,iv_detail_photo )
//
//        initView()
//
//
//        refreshData(false)
//    }


    fun initView(){
        toolbar_layout.title = "冒泡排序"
        toolbar_layout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.accent))
        toolbar_layout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.material_white))
        toolbar.setOnClickListener { view ->
            finishAfterTransition()
        }
    }


}