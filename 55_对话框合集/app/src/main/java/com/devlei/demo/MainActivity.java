package com.devlei.demo;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 打开一个退出应用的对话框
     */
    public void openDialog(View v) {
        // AlertDialog.Builder 通过Builder构建Dialog 建造者模式
        Builder builder = new Builder(this);
        // builder.setTitle("提示");
        builder.setMessage("是否残忍的退出应用??");
        // PositiveButton 确定按钮
        builder.setPositiveButton("是", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 退出应用
                MainActivity.this.finish();
                // 强制关闭某个dialog
                // dialog.dismiss();
            }
        });
        // 设置取消按钮
        // builder.setNegativeButton("否", null);

        AlertDialog dilog = builder.create();
        dilog.show();
    }

    public void openDialog2(View v) {
        // 链式调用
        AlertDialog dilog = new Builder(this).setTitle("提示")
                .setMessage("是否残忍的退出应用??")
                .setPositiveButton("是", new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                }).setNegativeButton("否", null).create();
        dilog.show();
    }

    String[] items=new String[]{"杨幂","凤姐"};
    public void openDialog3(View v){
        //checkedItem 默认选中的索引
        AlertDialog dilog = new Builder(this).setTitle("请选择你的女朋友")
                .setSingleChoiceItems(items, 1, new OnClickListener() {

                    //which
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String text = "当前选中的:"+items[which];
                        Toast.makeText(MainActivity.this, text, 0).show();
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("残忍拒绝", null)
                .create();
        dilog.show();
    }

    String[] multiItems=new String[]{"杨幂","凤姐","志玲"};
    boolean[] checkedItems=new boolean[]{false,true,false};
    public void openDialog4(View v){
        //String[] items 选项
        //boolean[] checkedItems
        AlertDialog dilog = new Builder(this)
                .setTitle("请选择你的女朋友团??")
                .setMultiChoiceItems(multiItems, checkedItems, new OnMultiChoiceClickListener() {

                    //which  索引
                    //isChecked 当前索引是否被选中
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        //找到checkedItems数组 根据当前的索引修改里面的值
                        checkedItems[which]=isChecked;
                    }
                })
                .setPositiveButton("确定", new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String toastText="";
                        for (int i = 0; i < checkedItems.length; i++) {
                            if (checkedItems[i]) {
                                toastText+=multiItems[i]+"  ";
                            }
                        }
                        Toast.makeText(MainActivity.this, toastText, 0).show();
                    }
                }).setNegativeButton("取消", null).create();
        dilog.show();
    }

    public void openDialog5(View v){
        final ProgressDialog dialog=new ProgressDialog(this);
        dialog.setTitle("提示");
        dialog.setMessage("请耐心等待...");
        dialog.show();
        //创建一个定时器 过了5秒后就自动消失
        //1.创建了一个定时器
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                //返回主线程
                runOnUiThread(new Runnable() {
                    public void run() {
                        dialog.dismiss();
                    }
                });

            }
        }, 5000);
    }

}

