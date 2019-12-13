package com.devlei.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        //year 年
        //month 月   减1 比如写的是5  显示的是6月
        //day 天
        datePicker.init(1979, 5, 1, new OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                String text=year+"年"+(monthOfYear+1)+"月"+dayOfMonth+"日";
                Toast.makeText(MainActivity.this,text , 0).show();
            }
        });

        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker1);
        //设置24小时表示法
        timePicker.setIs24HourView(true);
        //设置时间改变的监听器
        timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {

            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(MainActivity.this,hourOfDay+":"+minute , 0).show();
            }
        });
    }


}
