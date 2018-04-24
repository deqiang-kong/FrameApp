package com.kong.frameapp

import android.os.Bundle
import com.kong.frameapp.modules.base.BaseActivity
import com.kong.frameapp.modules.wynews.ui.NewsMainFragment

class MainActivity : BaseActivity() {


    internal var fragment = NewsMainFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        this.addFragment(R.id.llLayout, fragment)

    }



}
