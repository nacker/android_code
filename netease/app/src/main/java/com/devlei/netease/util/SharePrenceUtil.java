package com.devlei.netease.util;

import android.content.Context;
import android.content.SharedPreferences;


public class SharePrenceUtil {

    public static final String XML_FILE_NAME ="cache";


    public static  void saveString(Context context,String title, String content){

        SharedPreferences share =  context.getSharedPreferences(XML_FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        edit.putString(title,content);
        //立马xml写入
        //edit.commit();
        //以后才写
        edit.apply();
    }

    public static String getString(Context context, String title){
        String content;
        SharedPreferences share =  context.getSharedPreferences(XML_FILE_NAME,Context.MODE_PRIVATE);
        content = share.getString(title,"");
        return content;
    }


    public static  void saveInt(Context context,String title, int content){

        SharedPreferences share =  context.getSharedPreferences(XML_FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        edit.putInt(title,content);
        //立马xml写入
        //edit.commit();
        //以后才写
        edit.apply();
    }

    public static int getInt(Context context, String title){
        int content;
        SharedPreferences share =  context.getSharedPreferences(XML_FILE_NAME,Context.MODE_PRIVATE);
        content = share.getInt(title,0);
        return content;
    }

    public static  void saveLong(Context context,String title, long content){

        SharedPreferences share =  context.getSharedPreferences(XML_FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        edit.putLong(title,content);
        //立马xml写入
        //edit.commit();
        //以后才写
        edit.apply();
    }

    public static long getLong(Context context, String title){
        long content;
        SharedPreferences share =  context.getSharedPreferences(XML_FILE_NAME,Context.MODE_PRIVATE);
        content = share.getLong(title,0);
        return content;
    }
}
