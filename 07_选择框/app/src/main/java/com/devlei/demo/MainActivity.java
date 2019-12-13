package com.devlei.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//		RadioGroup
//		RadioButton
//		CheckBox
        RadioGroup radioGroup=(RadioGroup) findViewById(R.id.sex_rg);

        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
//			checkedId 被选中的RadioGroup的Id
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.radioButton1) {
                    Toast.makeText(MainActivity.this, "男",0).show();
                }else if (checkedId==R.id.radioButton2) {
                    Toast.makeText(MainActivity.this, "女",0).show();
                }
            }
        });

        CheckBox appleCbx=(CheckBox) findViewById(R.id.apple_cbx);
        appleCbx.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {

                    @Override
//			buttonView 当前的CheckBox
//			isChecked 是否被选中
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        Toast.makeText(MainActivity.this, isChecked?"选中":"未选中",0).show();
                    }
                });
    }

}

