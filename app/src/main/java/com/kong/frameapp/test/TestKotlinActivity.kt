package com.kong.frameapp.test

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.kong.frameapp.MainActivity
import com.kong.frameapp.R
import com.kong.frameapp.modules.base.BaseActivity
import com.kong.frameapp.modules.customnews.ui.CustomNewsChannelActivity
import com.kong.frameapp.modules.main.ui.HomeActivity
import com.kong.frameapp.modules.sortslide.BubbleSortActivity
import kotlinx.android.synthetic.main.test_layout.*

/**
 *
 */
class TestKotlinActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_layout)


        initView()

    }

    private fun initView() {

        button1.setOnClickListener(onViewClick);
        button2.setOnClickListener(onViewClick);
        button3.setOnClickListener(onViewClick);
        button4.setOnClickListener(onViewClick);

    }


    internal var onViewClick: View.OnClickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.button1 -> {

                val intent = Intent()
                intent.setClass(this, HomeActivity::class.java)
                startActivity(intent)

                button1.setText("VideoDetailActivity")
            }
            R.id.button2 -> {

                val intent = Intent()
                intent.setClass(this, MainActivity::class.java)
                startActivity(intent)
            }

            R.id.button3 -> {
                val intent = Intent()
                intent.setClass(this, CustomNewsChannelActivity::class.java)
                startActivity(intent)


            }

            R.id.button4 -> {

                val intent = Intent()
                intent.setClass(this, BubbleSortActivity::class.java)
                startActivity(intent)

            }


        }
    }


    override fun onDestroy() {
        super.onDestroy()

    }


}