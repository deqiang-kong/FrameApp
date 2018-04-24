package com.kong.frameapp.injector.component

import com.kong.fmklibrary.di.component.AppComponent
import com.kong.fmklibrary.di.scope.ActivityScope
import com.kong.frameapp.injector.module.CustomNewsChannelModule
import com.kong.frameapp.modules.customnews.ui.CustomNewsChannelActivity
import dagger.Component

/**
 * 新闻频道管理 Component
 * Created by kaipai on 17/10/31.
 */
@ActivityScope
@Component(modules = arrayOf(CustomNewsChannelModule::class), dependencies = arrayOf(AppComponent::class))
interface CustomNewsChannelComponent {


    fun inject(activity: CustomNewsChannelActivity)
}