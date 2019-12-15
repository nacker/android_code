package com.devlei.demo;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1.控件已经装载完毕了
        ListView lv = (ListView) findViewById(R.id.lv);
        adapter=new MyAdapter(this);
        //系统内部自动帮你回调了多次 getCount()   getView()
        lv.setAdapter(adapter);

        //2.数据是从后台服务器发送回来(延迟)
        ArrayList<String> datas=new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            datas.add("测试"+i);
        }
        //这个地方只是简单的setDatas() 并没有调用getCount()/getView()
        //上面setAdapter导致的多次调用getCount()/getView()可能还没调用完 此时代码执行很快
        //导致了我们setDatas的时候数据进去了 并且刷新的ListView
        adapter.setDatas(datas);
        //调用了该方法 系统就会再次调用getCount()/getView()
        adapter.notifyDataSetChanged();


        //1.当列表使用背景图之后 上拉或者下拉都会出现一个问题:背景图片不见了
        //android:cacheColorHint="#0FFF"
        //2.当列表使用背景图之后 上拉或者下拉都会出现一个问题:出现黑色块
        //android:scrollingCache="false"
        //3.当想设置分隔条的样式的时候
        //android:divider="#F00"
        //android:dividerHeight="2dp"
        //4.当发现上下拉的时候 上/下边出现阴影
        //android:fadingEdge="none"
        //5.点击的时候会出现系统给的颜色 如果想去掉
        //android:listSelector="#0000"


        //lv的滚动监听器
        lv.setOnScrollListener(new OnScrollListener() {

            /**
             * 滑动的状态改变的时候调用的
             * scrollState 滚动的状态
             * 		SCROLL_STATE_FLING	轻扫
             * 		SCROLL_STATE_IDLE	什么都不动的情况下
             * 		SCROLL_STATE_TOUCH_SCROLL  拖拽
             * */
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case OnScrollListener.SCROLL_STATE_FLING:
                        Log.v("520it", "SCROLL_STATE_FLING");
                        break;
                    case OnScrollListener.SCROLL_STATE_IDLE:
                        Log.v("520it", "SCROLL_STATE_IDLE");
                        break;
                    case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                        Log.v("520it", "SCROLL_STATE_TOUCH_SCROLL");
                        break;
                }
            }

            /**
             *	滚动的时候被调用
             *	只要列表滚动 就会毁掉该方法
             * */
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
//				Log.v("520it", "onScroll  firstItem="+firstVisibleItem
//						+"   visibleCount="+visibleItemCount
//						+" totalCount="+totalItemCount);
            }
        });

        //lv的点击事件
        lv.setOnItemClickListener(new OnItemClickListener() {

            /**
             * id 就是BaseAdapter的getItemId 返回的值
             *
             * */
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String data = (String) adapter.getItem(position);
                Toast.makeText(MainActivity.this, data, 0).show();
            }
        });

    }


}
