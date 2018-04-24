package com.kong.frameapp.modules.zhihu.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kong.fmklibrary.base.BaseHolder;
import com.kong.fmklibrary.controls.magicrecyclerView.BaseItem;
import com.kong.fmklibrary.di.component.AppComponent;
import com.kong.fmklibrary.utils.ArmsUtils;
import com.kong.frameapp.R;
import com.kong.frameapp.bean.ZhiHuStoryInfo;
import com.kong.frameapp.util.PictureLoadUtil;


public class TestItemHolder extends BaseHolder<ZhiHuStoryInfo> {



    ImageView mNewsImage;
    TextView mNewsTitle;

    private AppComponent mAppComponent;


    public TestItemHolder(View itemView) {
        super(itemView);
        //可以在任何可以拿到 Context 的地方,拿到 AppComponent,从而得到用 Dagger 管理的单例对象
        mAppComponent = ArmsUtils.obtainAppComponentFromContext(itemView.getContext());
        // mImageLoader = mAppComponent.imageLoader();

        mNewsImage = itemView.findViewById(R.id.news_image);
        mNewsTitle = itemView.findViewById(R.id.news_title);
    }

    @Override
    public void setData(ZhiHuStoryInfo data, int position) {

//        ZhiHuStoryInfo story = (ZhiHuStoryInfo) data.getData();
//
//        mNewsTitle.setText(story.getTitle());
//        PictureLoadUtil.getInstance().displayImageView( mAppComponent.application(), story.getImages().get(0), mNewsImage);


//        Observable.just(data.getLogin())
//                .subscribe(s -> mName.setText(s));

//        mImageLoader.loadImage(mAppComponent.appManager().getTopActivity() == null
//                        ? mAppComponent.application() : mAppComponent.appManager().getTopActivity(),
//                ImageConfigImpl
//                        .builder()
//                        .url(data.getAvatarUrl())
//                        .imageView(mAvater)
//                        .build());
    }


    @Override
    protected void onRelease() {
//        mImageLoader.clear(mAppComponent.application(), ImageConfigImpl.builder()
//                .imageViews(mAvater)
//                .build());
    }
}
