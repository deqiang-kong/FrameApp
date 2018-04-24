package com.kong.frameapp.injector.module

import com.kong.fmklibrary.di.scope.FragmentScope
import com.kong.frameapp.modules.video.contract.VideoInfoContract
import com.kong.frameapp.modules.video.model.VideoInfoModel
import dagger.Module
import dagger.Provides


/**
 * 视频信息 Module
 * Created by kaipai on 17/10/31.
 */
@Module
class VideoInfoModule(var view: VideoInfoContract.View) {


    @FragmentScope
    @Provides
    internal fun provideView(): VideoInfoContract.View {
        return this.view
    }


    @FragmentScope
    @Provides
    internal fun provideModel(model: VideoInfoModel): VideoInfoContract.Model {
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