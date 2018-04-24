package com.kong.frameapp.injector.module;


import com.kong.fmklibrary.controls.magicrecyclerView.BaseItem;
import com.kong.fmklibrary.di.scope.FragmentScope;
import com.kong.frameapp.modules.video.contract.VideoMainContract;
import com.kong.frameapp.modules.video.model.VideoMainModel;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;


@Module
public class VideoMainModule {
    private VideoMainContract.View view;

    /**
     * @param view
     */
    public VideoMainModule(VideoMainContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    VideoMainContract.View provideView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    VideoMainContract.Model provideModel(VideoMainModel model) {
        return model;
    }


    //＊＊＊＊该实例是是为了给RecyclerView.Adapter添加数据参数，没有他Adapter会实例化失败，造成编译不通过
    @FragmentScope
    @Provides
    List<BaseItem> provideList() {
        return new ArrayList<>();
    }

//    @FragmentScope
//    @Provides
//    RecyclerView.Adapter provideAdapter(List<BaseItem> list) {
//        return new TestAdapter(list);
//    }
}
