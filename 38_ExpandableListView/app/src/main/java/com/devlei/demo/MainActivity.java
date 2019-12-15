package com.devlei.demo;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class MainActivity extends Activity {

    private ExpandableListView mExpLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mExpLv=(ExpandableListView) findViewById(R.id.explv);

        //1.组的数据
        ArrayList<String> groupNames=new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
            groupNames.add("组 "+i);
        }
        //2.成员的数据
        HashMap<String, ArrayList<String>> childNames=
                new HashMap<String, ArrayList<String>>();
        for (int i = 0; i < groupNames.size(); i++) {
            ArrayList<String> children=new ArrayList<String>();
            for (int j = 0; j < 5; j++) {
                children.add("成员"+j);
            }
            //遍历下组名 key就是组名 值就时一个成员队列
            childNames.put(groupNames.get(i), children);
        }

        MyAdapter adapter=new MyAdapter(groupNames,childNames);
        mExpLv.setAdapter(adapter);


        //为ExpandableListView的组设置折叠监听器
//		mExpLv.setOnGroupCollapseListener(onGroupCollapseListener)
//		mExpLv.setOnGroupExpandListener(onGroupExpandListener)
//		mExpLv.setOnGroupClickListener(onGroupClickListener)
//		mExpLv.setOnChildClickListener(onChildClickListener)

        mExpLv.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(MainActivity.this, groupPosition+" 被展开了", 0).show();
            }
        });



    }


}
