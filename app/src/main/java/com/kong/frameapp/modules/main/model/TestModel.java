package com.kong.frameapp.modules.main.model;


import com.kong.fmklibrary.di.scope.ActivityScope;
import com.kong.fmklibrary.mvp.BaseModel;
import com.kong.frameapp.modules.main.contract.TestContract;

import javax.inject.Inject;



@ActivityScope
public class TestModel extends BaseModel implements TestContract.Model {
    public static final int USERS_PER_PAGE = 10;

    @Inject
    public TestModel() {

    }


    @Override
    public void getData(boolean isRefresh) {

    }
}
