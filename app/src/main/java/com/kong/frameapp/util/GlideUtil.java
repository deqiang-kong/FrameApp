package com.kong.frameapp.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Looper;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.kong.frameapp.R;


/**
 * glide框架使用
 * Created by kongdq on 17/4/10.
 */

public class GlideUtil {

    private static volatile GlideUtil instance = null;

    public static GlideUtil getInstance() {
        if (instance == null) {
            synchronized (GlideUtil.class) {
                if (instance == null) {
                    instance = new GlideUtil();
                }
            }
        }
        return instance;
    }


    /**
     * 装载图片
     *
     * @param context
     * @param imgUrl
     * @param imgView
     */
    public void loadImage(Context context, String imgUrl, final ImageView imgView) {
        Glide.with(context)
                .load(imgUrl)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.icon_defaultt)
                .error(R.mipmap.icon_defaultt)
                .into(imgView);
    }


    /**
     * 装载图片
     *
     * @param context
     * @param imgUrl
     * @param imgView
     */
    public void loadImageDrawable(Context context, String imgUrl, final ImageView imgView) {
        Glide.with(context)
                .load(imgUrl)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.icon_defaultt)
                .error(R.mipmap.icon_defaultt)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {

                        imgView.setImageDrawable(resource);
                    }
                });
    }


    /**
     * 装载图片
     *
     * @param context
     * @param imgUrl
     * @param imgView
     */
    public void loadImageBitmap(Context context, String imgUrl, final ImageView imgView, final PictureLoadUtil.PicSizeCallBack callBack) {
        Glide.with(context)
                .load(imgUrl)
                .asBitmap()//获取Bitmap要加上这个
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.icon_defaultt)
                .error(R.mipmap.icon_defaultt)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        imgView.setImageBitmap(resource);
                        callBack.picSize(resource.getWidth(), resource.getHeight());
                    }
                });
    }

    /**
     * 装载图片
     *
     * @param context
     * @param imgUrl
     * @param imgView
     */
    public void loadImageSize(Context context, String imgUrl, final ImageView imgView, final PictureLoadUtil.PicSizeCallBack callBack) {
        Glide.with(context)
                .load(imgUrl)
                .asBitmap()//获取Bitmap要加上这个
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.icon_defaultt)
                .error(R.mipmap.icon_defaultt)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        callBack.picSize(resource.getWidth(), resource.getHeight());
                    }
                });
    }

    /**
     * 装载图片回传位图
     *
     * @param context
     * @param imgUrl
     * @param imgView
     */
    public void loadImageBitmap(Context context, String imgUrl, final ImageView imgView, final PictureLoadUtil.PicBitmapCallBack callBack) {
        Glide.with(context)
                .load(imgUrl)
                .asBitmap()//获取Bitmap要加上这个
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.icon_defaultt)
                .error(R.mipmap.icon_defaultt)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        if (imgView != null)
                            imgView.setImageBitmap(resource);

                        callBack.picBitmap(resource);
                    }
                });
    }

    /**
     * 装载GIG动画，glide直接为ImageView提供解决方案
     *
     * @param context
     * @param gifUrl
     * @param imgView
     */
    public void loadGifImage(Context context, String gifUrl, ImageView imgView) {
        Glide.with(context)
                .load(gifUrl)
                .asGif() //判断加载的url资源是否为gif格式的资源
                .centerCrop()
                .placeholder(R.mipmap.icon_defaultt)
                .error(R.mipmap.icon_defaultt)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imgView);
    }


    /**
     * 装载头像并切割成圆形
     *
     * @param context
     * @param imgUrl
     * @param imgView
     */
    public void loadPortraitCircle(final Context context, String imgUrl, final ImageView imgView) {

        //效果不好无渐变效果
//        Glide.with(context)
//                .load(imgUrl)
//                .asBitmap()//获取Bitmap要加上这个
//                .dontAnimate()
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .placeholder(R.mipmap.personal)
//                .error(R.mipmap.personal)
//                .into(new SimpleTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
//                        circularBitmapDrawable.setCircular(true);
//                        imgView.setImageDrawable(circularBitmapDrawable);
//                    }
//                });

        Glide.with(context)
                .load(imgUrl)
                .centerCrop()
                .placeholder(R.mipmap.personal)
                .error(R.mipmap.personal)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .transform(new GlideCircleTransform(context))
                .into(imgView);
    }

    /**
     * 装载图片，并切割成圆角
     *
     * @param context
     * @param imgUrl
     * @param imgView
     */
    public void loadImageRound(Context context, String imgUrl, ImageView imgView) {
        Glide.with(context)
                .load(imgUrl)
                .centerCrop()
                .placeholder(R.mipmap.icon_defaultt)
                .error(R.mipmap.icon_defaultt)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .transform(new GlideRoundTransform(context))
                .into(imgView);
    }

    /**
     * 装载图片，并切割成圆角
     *
     * @param context
     * @param imgUrl
     * @param imgView
     */
    public void loadLocalImageRound(Context context, int imgUrl, ImageView imgView) {
        Glide.with(context)
                .load(imgUrl)
                .centerCrop()
                .placeholder(R.mipmap.icon_defaultt)
                .error(R.mipmap.icon_defaultt)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .transform(new GlideRoundTransform(context))
                .into(imgView);
    }


    /**
     * 内存缓存清理
     */
    public void clearMemory(final Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                Glide.get(context).clearMemory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 本地缓存清理
     */
    public void clearDiskCache(final Context context) {

        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(context).clearDiskCache();
                    }
                }).start();
            } else {
                Glide.get(context).clearDiskCache();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    相关配置设置
//         //加载资源图片
//        Glide.with(context).load(R.drawable.alipay).into(singleViewRes);
//        //加载资产目录图片
//        Glide.with(context).load("file:///android_asset/heart.png").into(singleViewAsset);
//        //加载sd卡图片文件
//        Glide.with(context).load(new File("XXX")).into(iv_picasso);

//    设置图片加载的动画
//      .dontAnimate()//设置图片直接加载，不显示动画
//      .crossFade()//设置加载图片为淡入淡出

//      .load(Uri.fromFile(new File(imgUrl))) //当加载本地的图片时
//      .skipMemoryCache(true)//跳过内存的缓存

//      .diskCacheStrategy( DiskCacheStrategy.NONE ) //跳过硬盘缓存
//    DiskCacheStrategy.NONE 什么都不缓存
//    DiskCacheStrategy.SOURCE 仅仅只缓存原来的全分辨率的图像
//    DiskCacheStrategy.RESULT 仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
//    DiskCacheStrategy.ALL 缓存所有版本的图像（默认行为）
//    .asGif() //判断加载的url资源是否为gif格式的资源

    /**
     * 图片转圆形的方法
     * 切小图会被拉伸 40dp以下
     */
    public class GlideCircleTransform extends BitmapTransformation {
        public GlideCircleTransform(Context context) {
            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return circleCrop(pool, toTransform);
        }

        private Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName();
        }
    }


    /**
     * 图片切圆角的方法
     */
    public class GlideRoundTransform extends BitmapTransformation {

        private float radius = 0f;

        public GlideRoundTransform(Context context) {
            this(context, 4);
        }

        public GlideRoundTransform(Context context, int dp) {
            super(context);
            this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return roundCrop(pool, toTransform);
        }

        private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName() + Math.round(radius);
        }
    }
}
