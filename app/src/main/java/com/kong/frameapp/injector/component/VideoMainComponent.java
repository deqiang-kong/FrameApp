package com.kong.frameapp.injector.component;


import com.kong.fmklibrary.di.component.AppComponent;
import com.kong.fmklibrary.di.scope.FragmentScope;
import com.kong.frameapp.injector.module.VideoMainModule;
import com.kong.frameapp.modules.video.ui.VideoMainFragment;

import dagger.Component;


@FragmentScope
@Component(modules = VideoMainModule.class,dependencies = AppComponent.class)
public interface VideoMainComponent {

    void inject(VideoMainFragment fragment);
}
