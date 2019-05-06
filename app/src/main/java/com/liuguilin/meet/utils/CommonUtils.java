package com.liuguilin.meet.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import com.liuguilin.meet.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * FileName: CommonUtils
 * Founder: 刘桂林
 * Create Date: 2019/5/6 21:08
 * Profile: 通用
 */
public class CommonUtils {

    /**
     * 保存Bitmap到SD卡
     *
     * @param mContext
     * @param bitmap
     * @param name
     * @return
     */
    public static boolean saveBitmap(Context mContext, Bitmap bitmap, String name) {
        try {
            //获得sd卡路径
            String sdcardPath = System.getenv("EXTERNAL_STORAGE");
            //图片保存的文件夹名
            String dir = sdcardPath + "/Meet/";
            //以File来构建
            File file = new File(dir);
            //如果不存在,创建此文件夹
            if (!file.exists()) {
                file.mkdirs();
            }
            IMLog.i("file uri==>" + dir);
            //将要保存的图片文件
            File mFile = new File(dir + name);
            if (mFile.exists()) {
                Toast.makeText(mContext, R.string.str_toest_photo_is_have, Toast.LENGTH_SHORT).show();
                return false;
            }
            //构建输出流
            FileOutputStream outputStream = new FileOutputStream(mFile);
            //compress到输出outputStream
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            // 其次把文件插入到系统图库
            try {
                MediaStore.Images.Media.insertImage(mContext.getContentResolver(), file.getAbsolutePath(), name, null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // 最后通知图库更新
            mContext.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + dir + name)));
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
