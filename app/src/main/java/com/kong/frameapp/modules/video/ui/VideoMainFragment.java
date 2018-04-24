package com.kong.frameapp.modules.video.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.kong.fmklibrary.base.MVPBaseFragment;
import com.kong.fmklibrary.controls.loopbander.AutoScrollViewPager;
import com.kong.fmklibrary.controls.loopbander.ViewGroupIndicator;
import com.kong.fmklibrary.controls.magicrecyclerView.BaseItem;
import com.kong.fmklibrary.controls.magicrecyclerView.BaseRecyclerAdapter;
import com.kong.fmklibrary.controls.magicrecyclerView.MagicRecyclerView;
import com.kong.fmklibrary.controls.magicrecyclerView.SpaceDecoration;
import com.kong.fmklibrary.di.component.AppComponent;
import com.kong.frameapp.R;
import com.kong.frameapp.bean.MovieInfoBean;
import com.kong.frameapp.injector.component.DaggerVideoMainComponent;
import com.kong.frameapp.injector.module.VideoMainModule;
import com.kong.frameapp.modules.video.adapter.VideoTopPagerAdapter;
import com.kong.frameapp.modules.video.adapter.VideoTypesAdapter;
import com.kong.frameapp.modules.video.contract.VideoMainContract;
import com.kong.frameapp.modules.video.presenter.VideoMainFragPresenter;
import com.kong.frameapp.util.DensityUtil;
import com.kong.frameapp.util.PictureLoadUtil;

import org.reactivestreams.Subscriber;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;


public class VideoMainFragment extends MVPBaseFragment<VideoMainFragPresenter> implements VideoMainContract.View, SwipeRefreshLayout.OnRefreshListener, BaseRecyclerAdapter.OnItemClickListener {

    MagicRecyclerView mMrvVideo;
    SwipeRefreshLayout mSrlRefresh;
    TextView mEmptyMsg;

    private AutoScrollViewPager scrollViewPager;
    private ViewGroupIndicator viewGroupIndicator;


    private VideoTopPagerAdapter mPagerAdapter;
    private VideoTypesAdapter mAdapter;

    private ArrayList<BaseItem> mBaseItems;
    private Disposable mDisposable;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;


    /**
     * 初始化属于自己的Component对象
     */
    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerVideoMainComponent
                .builder()
                .appComponent(appComponent)
                .videoMainModule(new VideoMainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_fragment, container, false);
        mActivity = this.getActivity();
        mParent = view;
        return view;
    }


    private void titleBarColor( String imgUrl ,ImageView imgView){
        PictureLoadUtil.getInstance().getImageBitmap(this.getContext(), imgUrl, imgView, new PictureLoadUtil.PicBitmapCallBack() {
                    @Override
                    public void picBitmap(Bitmap bmp) {

                    }
                });

        PictureLoadUtil.getInstance().displayImageView(this.getContext(), imgUrl, imgView, new PictureLoadUtil.PicSizeCallBack() {
            @Override
            public void picSize(int pWidth, int pHeight) {

            }
        });

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
    }


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }


    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void setData(Object data) {
        

    }


    @Override
    public void onResume() {
        super.onResume();
        onHiddenChanged(false);
    }

    @Override
    public void onPause() {
        super.onPause();
        mSrlRefresh.setRefreshing(false);
        onHiddenChanged(true);
    }

    private void initView() {
        mSrlRefresh = mParent.findViewById(R.id.srl_refresh);
        mMrvVideo = mParent.findViewById(R.id.mrv_video);
        mEmptyMsg = mParent.findViewById(R.id.empty_msg);


        mBaseItems = new ArrayList<>();
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mMrvVideo.setLayoutManager(mStaggeredGridLayoutManager);
        mMrvVideo.setItemAnimator(new DefaultItemAnimator());
        mMrvVideo.getItemAnimator().setChangeDuration(0);
        SpaceDecoration itemDecoration = new SpaceDecoration(DensityUtil.dip2px(getContext(), 8));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        mMrvVideo.addItemDecoration(itemDecoration);
        FrameLayout headerView = (FrameLayout) mMrvVideo.getHeaderView();
        scrollViewPager = headerView.findViewById(R.id.scroll_pager);
        viewGroupIndicator = headerView.findViewById(R.id.scroll_pager_indicator);
        mSrlRefresh.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.white_FFFFFF));
        mSrlRefresh.setOnRefreshListener(this);
        mSrlRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        refreshData(false);

        mMrvVideo.addOnItemClickListener(this);
    }

    @Override
    public void refreshData(boolean isRefresh) {
        mPresenter.getData(isRefresh);
    }

    @Override
    public void refreshSuc(Object dataObj) {
        mSrlRefresh.setRefreshing(false);
        ArrayList<MovieInfoBean> listBeen = (ArrayList<MovieInfoBean>) dataObj;
        if (listBeen == null || listBeen.size() <= 0) {
            mEmptyMsg.setVisibility(View.VISIBLE);
            mMrvVideo.setVisibility(View.INVISIBLE);
            mSrlRefresh.requestFocus();
            return;
        } else {
            mEmptyMsg.setVisibility(View.GONE);
            mMrvVideo.setVisibility(View.VISIBLE);
        }
        MovieInfoBean banner = listBeen.get(0);
        if ("banner".equals(banner.getShowType())) { //判断是否为 banner
            //配置顶部故事
            if (mPagerAdapter == null) {

                mPagerAdapter = new VideoTopPagerAdapter(this, banner.getChildList());
                scrollViewPager.setAdapter(mPagerAdapter);
            } else {
                mPagerAdapter.resetData(banner.getChildList());
            }
            viewGroupIndicator.setParent(scrollViewPager);
            listBeen.remove(banner);
        }
        //配置底部列表故事s
        mBaseItems.clear();
        for (MovieInfoBean listBean : listBeen) {
            if (!TextUtils.isEmpty(listBean.getMoreURL()) &&
                    !listBean.getTitle().equals("直播专区") && !listBean.getTitle().equals("专题")) {
                BaseItem<MovieInfoBean> baseItem = new BaseItem<>();
                baseItem.setData(listBean);
                mBaseItems.add(baseItem);
            }
        }
        if (mAdapter == null) {
            mAdapter = new VideoTypesAdapter(this);
            mAdapter.setBaseDatas(mBaseItems);
            mMrvVideo.setAdapter(mAdapter);
        } else {
            if (listBeen.size() != 0) {
                mAdapter.setBaseDatas(mBaseItems);
            }
        }
    }

    @Override
    public void refreshFail(String errMsg) {
        if (mAdapter == null) {
            mEmptyMsg.setVisibility(View.VISIBLE);
            mMrvVideo.setVisibility(View.INVISIBLE);
            mSrlRefresh.requestFocus();
        }
    }


    @Override
    public void showWaitingView() {

    }

    @Override
    public void againWaitingView() {

    }

    @Override
    public void dismissWaitingView() {

    }


    @Override
    public void onRefresh() {
        refreshData(true);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden && mSrlRefresh.isRefreshing()) { // 隐藏的时候停止 SwipeRefreshLayout 转动
            mSrlRefresh.setRefreshing(false);
        }
        if (!hidden) {
//            RxBus.getDefault()
//                    .toObservableWithCode(RxConstants.BACK_PRESSED_CODE, String.class)
//                    .subscribeWith(new Observer<String>() {
//                        @Override
//                        public void onSubscribe(Disposable d) {
//                            mDisposable = d;
//                        }
//
//                        @Override
//                        public void onNext(String value) {
//                            if (value.equals(RxConstants.BACK_PRESSED_DATA) && mMrvVideo != null) {
//                                //滚动到顶部
//                                mStaggeredGridLayoutManager.smoothScrollToPosition(mMrvVideo, null, 0);
//                            }
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//
//                        }
//
//                        @Override
//                        public void onComplete() {
//
//                        }
//                    });
        } else {
            if (mDisposable != null && !mDisposable.isDisposed()) {
                mDisposable.dispose();
            }
        }
    }


    @Override
    public void onItemClick(int position, BaseItem data, View view) {
        MovieInfoBean dataBean = (MovieInfoBean) data.getData();
        Intent intent = new Intent(this.getActivity(), VideoTypeListActivity.class);
        intent.putExtra("videoType", dataBean.getTitle());
        startActivity(intent);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void showMessage(String message) {

    }

}
