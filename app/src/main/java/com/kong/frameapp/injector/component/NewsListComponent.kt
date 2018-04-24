package com.kong.frameapp.injector.component

import com.kong.fmklibrary.di.component.AppComponent
import com.kong.fmklibrary.di.scope.FragmentScope
import com.kong.frameapp.injector.module.NewsListModule
import com.kong.frameapp.modules.wynews.ui.NewsListFragment
import dagger.Component

/**
 * 新闻列表 Component
 * Created by kaipai on 17/10/31.
 */
@FragmentScope
@Component(modules = arrayOf(NewsListModule::class), dependencies = arrayOf(AppComponent::class))
interface NewsListComponent{


    fun inject(fragment: NewsListFragment)
}