package com.kong.frameapp.injector.module

import com.kong.fmklibrary.di.scope.FragmentScope
import com.kong.frameapp.modules.wynews.contract.NewsListContract
import com.kong.frameapp.modules.wynews.model.NewsListModel
import dagger.Module
import dagger.Provides


/**
 * 新闻列表 Module
 * Created by kaipai on 17/10/31.
 */
@Module
class NewsListModule(var view: NewsListContract.View) {


    @FragmentScope
    @Provides
    internal fun provideView(): NewsListContract.View {
        return this.view
    }


    @FragmentScope
    @Provides
    internal fun provideModel(model: NewsListModel): NewsListContract.Model {
        return model
    }


//    //kotlin中注入的对象不能用泛型数据
//    @FragmentScope
//    @Provides
//    internal fun provideDailyAdapter(): BaseRecyclerAdapter {
//        return ZhihuDailyAdapter(view as Fragment)
//    }
//
//
//
//    @FragmentScope
//    @Provides
//    internal fun provideList(): ArrayList<ZhiHuTopStory> {
//        return ArrayList()
//    }
//
//    @FragmentScope
//    @Provides
//    internal fun provideTopAdapter(list: ArrayList<ZhiHuTopStory>): ZhihuTopPagerAdapter {
//        return ZhihuTopPagerAdapter(view as Fragment,list)
//    }

}