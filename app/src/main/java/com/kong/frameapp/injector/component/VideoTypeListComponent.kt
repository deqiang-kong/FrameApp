package com.kong.frameapp.injector.component

import com.kong.fmklibrary.di.component.AppComponent
import com.kong.fmklibrary.di.scope.ActivityScope
import com.kong.frameapp.injector.module.VideoTypeListModule
import com.kong.frameapp.modules.video.ui.VideoTypeListActivity
import dagger.Component

/**
 * 视频列表 Component
 * Created by kaipai on 17/10/31.
 */
@ActivityScope
@Component(modules = arrayOf(VideoTypeListModule::class), dependencies = arrayOf(AppComponent::class))
interface VideoTypeListComponent{


    fun inject(activity: VideoTypeListActivity)
}