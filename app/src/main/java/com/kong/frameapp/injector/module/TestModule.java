package com.kong.frameapp.injector.module;


import com.kong.fmklibrary.di.scope.ActivityScope;
import com.kong.frameapp.modules.main.contract.TestContract;
import com.kong.frameapp.modules.main.model.TestModel;

import dagger.Module;
import dagger.Provides;



@Module
public class TestModule {
    private TestContract.View view;


    public TestModule(TestContract.View view) {
        this.view = view;
    }


    @ActivityScope
    @Provides
    TestContract.View provideView(){
        return this.view;
    }


    @ActivityScope
    @Provides
    TestContract.Model provideModel(TestModel model){
        return model;
    }
}
