package com.kong.frameapp.injector.component

import com.kong.fmklibrary.di.component.AppComponent
import com.kong.fmklibrary.di.scope.FragmentScope
import com.kong.frameapp.injector.module.VideoCommentModule
import com.kong.frameapp.modules.video.ui.VideoCommentFragment
import dagger.Component

/**
 * 视频信息 Component
 * Created by kaipai on 17/10/31.
 */
@FragmentScope
@Component(modules = arrayOf(VideoCommentModule::class), dependencies = arrayOf(AppComponent::class))
interface VideoCommentComponent{


    fun inject(fragment: VideoCommentFragment)
}