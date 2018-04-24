package com.kong.frameapp.injector.component

import com.kong.fmklibrary.di.component.AppComponent
import com.kong.fmklibrary.di.scope.ActivityScope
import com.kong.frameapp.injector.module.NewsDetailModule
import com.kong.frameapp.modules.wynews.ui.NewsDetailActivity
import dagger.Component

/**
 * 新闻详情 Component
 * Created by kaipai on 17/10/31.
 */
@ActivityScope
@Component(modules = arrayOf(NewsDetailModule::class), dependencies = arrayOf(AppComponent::class))
interface NewsDetailComponent{


    fun inject(activity: NewsDetailActivity)
}