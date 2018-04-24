package com.kong.frameapp.injector.component

import com.kong.fmklibrary.di.component.AppComponent
import com.kong.fmklibrary.di.scope.FragmentScope
import com.kong.frameapp.injector.module.ZhiHuDailyModule
import com.kong.frameapp.modules.zhihu.ui.ZhiHuDailyFragment
import dagger.Component

/**
 * 知乎列表 Component
 * Created by kaipai on 17/10/31.
 */
@FragmentScope
@Component(modules = arrayOf(ZhiHuDailyModule::class), dependencies = arrayOf(AppComponent::class))
interface ZhiHuDailyComponent{


    fun inject(fragment: ZhiHuDailyFragment)
}