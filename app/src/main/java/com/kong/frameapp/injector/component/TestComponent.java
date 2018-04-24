package com.kong.frameapp.injector.component;



import com.kong.fmklibrary.di.component.AppComponent;
import com.kong.fmklibrary.di.scope.ActivityScope;
import com.kong.frameapp.injector.module.TestModule;
import com.kong.frameapp.modules.main.ui.TestActivity;

import dagger.Component;



@ActivityScope
@Component(modules = TestModule.class,dependencies = AppComponent.class)
public interface TestComponent {
    void inject(TestActivity activity);
}
