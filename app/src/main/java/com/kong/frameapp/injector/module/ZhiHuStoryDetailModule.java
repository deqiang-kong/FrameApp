package com.kong.frameapp.injector.module;


import com.kong.fmklibrary.di.scope.ActivityScope;
import com.kong.frameapp.modules.zhihu.contract.ZhiHuStoryDetailContract;
import com.kong.frameapp.modules.zhihu.model.ZhiHuStoryDetailModel;

import dagger.Module;
import dagger.Provides;


@Module
public class ZhiHuStoryDetailModule {
    private ZhiHuStoryDetailContract.View view;


    public ZhiHuStoryDetailModule(ZhiHuStoryDetailContract.View view) {
        this.view = view;
    }


    @ActivityScope
    @Provides
    ZhiHuStoryDetailContract.View provideView(){
        return this.view;
    }


    @ActivityScope
    @Provides
    ZhiHuStoryDetailContract.Model provideModel(ZhiHuStoryDetailModel model){
        return model;
    }
}
