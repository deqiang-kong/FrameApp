package com.kong.frameapp.injector.component

import com.kong.fmklibrary.di.component.AppComponent
import com.kong.fmklibrary.di.scope.FragmentScope
import com.kong.frameapp.injector.module.VideoInfoModule
import com.kong.frameapp.modules.video.ui.VideoInfoFragment
import dagger.Component

/**
 * 视频信息 Component
 * Created by kaipai on 17/10/31.
 */
@FragmentScope
@Component(modules = arrayOf(VideoInfoModule::class), dependencies = arrayOf(AppComponent::class))
interface VideoInfoComponent{


    fun inject(fragment: VideoInfoFragment)
}