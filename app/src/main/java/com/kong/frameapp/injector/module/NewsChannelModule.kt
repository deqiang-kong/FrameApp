package com.kong.frameapp.injector.module

import com.kong.fmklibrary.di.scope.ActivityScope
import com.kong.fmklibrary.di.scope.FragmentScope
import com.kong.frameapp.modules.wynews.contract.NewsChannelContract
import com.kong.frameapp.modules.wynews.contract.NewsListContract
import com.kong.frameapp.modules.wynews.model.NewsChannelModel
import com.kong.frameapp.modules.wynews.model.NewsListModel
import dagger.Module
import dagger.Provides


/**
 * 新闻频道管理 Module
 * Created by kaipai on 17/10/31.
 */
@Module
class NewsChannelModule(var view: NewsChannelContract.View) {


    @ActivityScope
    @Provides
    internal fun provideView(): NewsChannelContract.View {
        return this.view
    }


    @ActivityScope
    @Provides
    internal fun provideModel(model: NewsChannelModel): NewsChannelContract.Model {
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