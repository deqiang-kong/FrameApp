package com.kong.frameapp.injector.module

import android.support.v4.app.Fragment
import com.kong.fmklibrary.controls.magicrecyclerView.BaseRecyclerAdapter
import com.kong.fmklibrary.di.scope.FragmentScope
import com.kong.frameapp.bean.ZhiHuTopStory
import com.kong.frameapp.modules.zhihu.adapter.ZhihuDailyAdapter
import com.kong.frameapp.modules.zhihu.adapter.ZhihuTopPagerAdapter
import com.kong.frameapp.modules.zhihu.contract.ZhiHuDailyContract
import com.kong.frameapp.modules.zhihu.model.ZhiHuDailyModel
import dagger.Module
import dagger.Provides
import java.util.*


/**
 * 知乎列表 Module
 * Created by kaipai on 17/10/31.
 */
@Module
class ZhiHuDailyModule(var view: ZhiHuDailyContract.View) {


    @FragmentScope
    @Provides
    internal fun provideView(): ZhiHuDailyContract.View {
        return this.view
    }


    @FragmentScope
    @Provides
    internal fun provideModel(model: ZhiHuDailyModel): ZhiHuDailyContract.Model {
        return model
    }


    //kotlin中注入的对象不能用泛型数据
    @FragmentScope
    @Provides
    internal fun provideDailyAdapter(): BaseRecyclerAdapter {
        return ZhihuDailyAdapter(view as Fragment)
    }



    @FragmentScope
    @Provides
    internal fun provideList(): ArrayList<ZhiHuTopStory> {
        return ArrayList()
    }

    @FragmentScope
    @Provides
    internal fun provideTopAdapter(list: ArrayList<ZhiHuTopStory>): ZhihuTopPagerAdapter {
        return ZhihuTopPagerAdapter(view as Fragment,list)
    }

}