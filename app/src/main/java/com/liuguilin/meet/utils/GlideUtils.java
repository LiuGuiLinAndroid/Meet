package com.liuguilin.meet.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.util.Util;

import java.io.File;

/**
 * FileName: GlideUtils
 * Founder: 刘桂林
 * Create Date: 2019/5/6 20:38
 * Profile: 图片加载工具类
 */
public class GlideUtils {

    /**
     * 标准的图片加载函数
     *
     * @param mContext
     * @param imgUrl
     * @param resId
     * @param mImageView
     */
    public static void loadImg(Context mContext, String imgUrl, int resId,ImageView mImageView) {
        if (Util.isOnMainThread()) {
            Glide.with(mContext).load(imgUrl).dontAnimate().placeholder(resId).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mImageView);
        }
    }

    /**
     * 加载Gif
     *
     * @param mContext
     * @param res
     * @param mImageView
     */
    public static void loadGif(Context mContext, int res, ImageView mImageView) {
        if (Util.isOnMainThread()) {
            Glide.with(mContext).load(res).asGif().into(mImageView);
        }
    }

    /**
     * 加载文件图片
     *
     * @param mContext
     * @param imgFile
     * @param mImageView
     */
    public static void loadFile(Context mContext, File imgFile, int resId, ImageView mImageView) {
        try {
            if (Util.isOnMainThread()) {
                Glide.with(mContext).load(imgFile).dontAnimate().placeholder(resId).into(mImageView);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
