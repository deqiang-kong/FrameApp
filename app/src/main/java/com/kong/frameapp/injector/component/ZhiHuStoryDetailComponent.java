package com.kong.frameapp.injector.component;


import com.kong.fmklibrary.di.component.AppComponent;
import com.kong.fmklibrary.di.scope.ActivityScope;
import com.kong.frameapp.injector.module.ZhiHuStoryDetailModule;
import com.kong.frameapp.modules.zhihu.ui.ZhiHuStoryDetailActivity;

import dagger.Component;


@ActivityScope
@Component(modules = ZhiHuStoryDetailModule.class,dependencies = AppComponent.class)
public interface ZhiHuStoryDetailComponent {
    void inject(ZhiHuStoryDetailActivity activity);
}
