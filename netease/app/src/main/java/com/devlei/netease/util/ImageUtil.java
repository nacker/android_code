package com.devlei.netease.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;

public class ImageUtil {

    public static boolean checkImageIsDownLoad(String imageName){
        File image = getFileByName(imageName);
        if(image.exists()){
          Bitmap bitmap =   getImageBitMapByFile(image);
            if(bitmap!=null){
                return  true;
            }
        }
        return false;
    }

    public static File getFileByName(String imageName){
        File SD = Environment.getExternalStorageDirectory();
        File cacheFile = new File(SD, Constant.CACHE);
        File image = new File(cacheFile,imageName+".jpg");
        return  image;
    }

    public static Bitmap getImageBitMapByFile(File image){
        Bitmap bitmap =  BitmapFactory.decodeFile(image.getAbsolutePath());
        return bitmap;
    }

}
