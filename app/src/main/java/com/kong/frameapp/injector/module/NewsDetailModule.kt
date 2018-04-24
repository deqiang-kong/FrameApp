package com.kong.frameapp.injector.module

import com.kong.fmklibrary.di.scope.ActivityScope
import com.kong.fmklibrary.di.scope.FragmentScope
import com.kong.frameapp.modules.wynews.contract.NewsChannelContract
import com.kong.frameapp.modules.wynews.contract.NewsDetailContract
import com.kong.frameapp.modules.wynews.contract.NewsListContract
import com.kong.frameapp.modules.wynews.model.NewsChannelModel
import com.kong.frameapp.modules.wynews.model.NewsDetailModel
import com.kong.frameapp.modules.wynews.model.NewsListModel
import dagger.Module
import dagger.Provides


/**
 * 新闻详情 Module
 * Created by kaipai on 17/10/31.
 */
@Module
class NewsDetailModule(var view: NewsDetailContract.View) {


    @ActivityScope
    @Provides
    internal fun provideView(): NewsDetailContract.View {
        return this.view
    }


    @ActivityScope
    @Provides
    internal fun provideModel(model: NewsDetailModel): NewsDetailContract.Model {
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