package com.devlei.netease.splash;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;


public class TimeView extends View {

    //文字画笔
    TextPaint mTextPaint;
    Paint circleP;
    Paint outerP;
    String content = "跳过";
    //文字的间距
    int pading = 10;
    //内圆的直径
    int inner;
    //外圈的直径
    int all;
    //外圈的角度
    int dgree;
    RectF outerRect;

    public TimeView(Context context) {
        super(context);
    }

    public TimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTextPaint = new TextPaint();
        //抗锯齿
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(50);
        mTextPaint.setColor(Color.WHITE);

        //内圈画笔
        circleP = new Paint();
        circleP.setFlags(Paint.ANTI_ALIAS_FLAG);
        circleP.setColor(Color.BLUE);


        outerP = new Paint();
        outerP.setFlags(Paint.ANTI_ALIAS_FLAG);
        outerP.setColor(Color.GREEN);
        outerP.setStyle(Paint.Style.STROKE);
        outerP.setStrokeWidth(pading);

        //文字的宽度
        float text_Width =  mTextPaint.measureText(content);
        //内圆圈的直径
        inner = (int)text_Width+2*pading;
        //外圆圈的直径
        all = inner+2*pading;

        outerRect = new RectF(pading/2,pading/2,all-pading/2,all-pading/2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //文字的宽度+内圆的边距*2+画笔的宽度*2
        setMeasuredDimension(all,all);
    }

    public void setD(int d){
        this.dgree =d;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        //canvas.drawColor(Color.RED);
        canvas.drawCircle(all/2,all/2,inner/2,circleP);

        canvas.save();
        canvas.rotate(-90,all/2,all/2);
        canvas.drawArc(outerRect,0f,dgree,false,outerP);
        canvas.restore();
    }
}
