package com.kong.frameapp.injector.module

import com.kong.fmklibrary.di.scope.FragmentScope
import com.kong.frameapp.modules.video.contract.VideoCommentContract
import com.kong.frameapp.modules.video.contract.VideoInfoContract
import com.kong.frameapp.modules.video.model.VideoCommentModel
import com.kong.frameapp.modules.video.model.VideoInfoModel
import dagger.Module
import dagger.Provides


/**
 * 视频评论 Module
 * Created by kaipai on 17/10/31.
 */
@Module
class VideoCommentModule(var view: VideoCommentContract.View) {


    @FragmentScope
    @Provides
    internal fun provideView(): VideoCommentContract.View {
        return this.view
    }


    @FragmentScope
    @Provides
    internal fun provideModel(model: VideoCommentModel): VideoCommentContract.Model {
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