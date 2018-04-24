package com.kong.frameapp.injector.module

import com.kong.fmklibrary.di.scope.ActivityScope
import com.kong.frameapp.modules.video.contract.VideoTypeListContract
import com.kong.frameapp.modules.video.model.VideoTypeListModel
import dagger.Module
import dagger.Provides


/**
 * 视频列表 Module
 * Created by kaipai on 17/10/31.
 */
@Module
class VideoTypeListModule(var view: VideoTypeListContract.View) {


    @ActivityScope
    @Provides
    internal fun provideView(): VideoTypeListContract.View {
        return this.view
    }


    @ActivityScope
    @Provides
    internal fun provideModel(model: VideoTypeListModel): VideoTypeListContract.Model {
        return model
    }



//    @ActivityScope
//    @Provides
//    internal fun provideList(): List<BaseItem<*>> {
//        return java.util.ArrayList<BaseItem<*>>()
//    }
//
//    @ActivityScope
//    @Provides
//    internal fun provideAdapter(list: ArrayList<BaseItem<*>>): VideoTypeListAdapter {
//        return VideoTypeListAdapter(view as Context)
//    }

}