package com.devlei.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.devlei.demo.R;
import com.devlei.demo.adapter.StudentAdapter;
import com.devlei.demo.dao.StudentDao;

public class MainActivity extends Activity {

    private EditText mStdIdEt;
    private EditText mStdNameEt;
    private EditText mStdPhoneEt;
    private ListView mStudentLv;
    private StudentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStdIdEt=(EditText) findViewById(R.id.studentid_et);
        mStdNameEt=(EditText) findViewById(R.id.studentname_et);
        mStdPhoneEt=(EditText) findViewById(R.id.studentphone_et);
        mStudentLv=(ListView)findViewById(R.id.std_lv);
        mAdapter=new StudentAdapter(this);
        mStudentLv.setAdapter(mAdapter);
    }

    public void addStudentInfo(View v){
        String stdId = mStdIdEt.getText().toString();
        String stdName = mStdNameEt.getText().toString();
        String stdPhone = mStdPhoneEt.getText().toString();
//		判断这里就不写了
        StudentDao dao=new StudentDao(this);
        boolean saveStudent = dao.saveStudent(stdId, stdName, stdPhone);
        if (saveStudent) {
            mAdapter.notifyDataSetChanged();
        }
    }


}

