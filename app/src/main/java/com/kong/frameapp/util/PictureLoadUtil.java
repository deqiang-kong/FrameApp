package com.kong.frameapp.util;


import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * 图片装载 图片加载统一调用方式
 * Created by kongdq on 17/4/10.
 */

public class PictureLoadUtil {

    private static volatile PictureLoadUtil instance = null;

    public static PictureLoadUtil getInstance() {
        if (instance == null) {
            synchronized (PictureLoadUtil.class) {
                if (instance == null) {
                    instance = new PictureLoadUtil();
                }
            }
        }
        return instance;
    }


    //Context 使用全局Application避免出现Context无效

    /**
     * 装载头像并切割成圆形
     *
     * @param imgView
     * @param url
     */
    public void loadPortraitCircle(final Context mContext, String url, final ImageView imgView) {

        GlideUtil.getInstance().loadPortraitCircle(mContext.getApplicationContext(), url, imgView);
    }


    /**
     * 装载GIG动画，glide直接为ImageView提供解决方案
     *
     * @param gifUrl
     * @param imgView
     */
    public void loadGifImage(String gifUrl, ImageView imgView) {


    }


    /**
     * imageView装载图片
     *
     * @param imgView
     * @param url
     */
    public void displayImageView(final Context mContext, String url, final ImageView imgView) {

        GlideUtil.getInstance().loadImage(mContext.getApplicationContext(), url, imgView);
    }

    /**
     * imageView装载图片
     *
     * @param imgView
     * @param url
     */
    public void displayImageView(final Context mContext, String url, final ImageView imgView, final PicSizeCallBack callBack) {

        GlideUtil.getInstance().loadImageBitmap(mContext.getApplicationContext(), url, imgView, callBack);
    }

    /**
     * imageView装载图片
     *
     * @param imgView
     * @param url
     */
    public void caculateImageView(final Context mContext, String url, final ImageView imgView, final PicSizeCallBack callBack) {

        GlideUtil.getInstance().loadImageSize(mContext.getApplicationContext(), url, imgView, callBack);
    }

    /**
     * 获取图片位图
     *
     * @param imgView
     * @param url
     */
    public void getImageBitmap(final Context mContext, String url, final ImageView imgView, final PicBitmapCallBack callBack) {

        GlideUtil.getInstance().loadImageBitmap(mContext.getApplicationContext(), url, imgView, callBack);
    }


    /**
     * 缓存清理
     *
     */
    public void clearMemory(Context mContext) {
        GlideUtil.getInstance().clearMemory(mContext.getApplicationContext());
    }

    /**
     * 图片尺寸回调
     */
    public interface PicSizeCallBack {

        void picSize(int pWidth, int pHeight);
    }

    /**
     * 图片位图
     */
    public interface PicBitmapCallBack {

        void picBitmap(Bitmap bmp);
    }
}

/*
 *   ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 *     ┃　　　┃
 *     ┃　　　┃
 *     ┃　　　┗━━━┓
 *     ┃　　　　　　　┣┓
 *     ┃　　　　　　　┏┛
 *     ┗┓┓┏━┳┓┏┛
 *       ┃┫┫　┃┫┫
 *       ┗┻┛　┗┻┛
 *        神兽保佑
 *        代码无BUG!
 */