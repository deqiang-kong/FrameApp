package com.kong.frameapp.injector.component

import com.kong.fmklibrary.di.component.AppComponent
import com.kong.fmklibrary.di.scope.ActivityScope
import com.kong.frameapp.injector.module.NewsChannelModule
import com.kong.frameapp.modules.wynews.ui.NewsChannelActivity
import dagger.Component

/**
 * 新闻频道管理 Component
 * Created by kaipai on 17/10/31.
 */
@ActivityScope
@Component(modules = arrayOf(NewsChannelModule::class), dependencies = arrayOf(AppComponent::class))
interface NewsChannelComponent{


    fun inject(activity: NewsChannelActivity)
}