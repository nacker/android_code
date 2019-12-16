package com.devlei.demo;


import java.io.Serializable;

import com.devlei.demo.bean.Person;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import com.devlei.demo.bean.Person;

public class MySecActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sec);


        Intent intent = getIntent();
        Person p = (Person) intent.getSerializableExtra("person");
//        Log.v("it", p+"");

        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setText(p.toString());
    }



}
