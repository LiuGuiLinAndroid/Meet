package com.liuguilin.meet.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.liuguilin.meet.R;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * FileName: TestActivity
 * Founder: 刘桂林
 * Create Date: 2019/3/24 18:41
 * Profile: 测试页面
 */
public class TestActivity extends AppCompatActivity {

    private static final String TAG = "TestActivity";

    private ListView mListView;

    private ArrayAdapter<String> mAdapter;
    private String[] mStr = {"增加一行数据", "获取一行数据", "修改一行数据", "删除一行数据"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.mListView);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mStr);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        dataSave();
                        break;
                    case 1:
                        dataGet();
                        break;
                    case 2:
                        dataUpdate();
                        break;
                    case 3:
                        dataDelete();
                        break;
                }
            }
        });
    }

    private void dataSave() {
        Person person = new Person();
        person.setName("小明");
        person.setAddress("北京");
        person.setSex(true);
        person.setHeight(120);
        person.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e == null){
                    Toast.makeText(TestActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(TestActivity.this, "添加失败:" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void dataGet() {
        BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
        bmobQuery.getObject("46e666dd37", new QueryListener<Person>() {
            @Override
            public void done(Person person, BmobException e) {
                if(e == null){
                    Toast.makeText(TestActivity.this, "查询成功：" + person.toString(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(TestActivity.this, "查询失败:" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void dataUpdate() {
        Person person = new Person();
        person.setHeight(150);
        person.update("46e666dd37", new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e == null){
                    Toast.makeText(TestActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(TestActivity.this, "更新失败:" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void dataDelete() {
        Person person = new Person();
        person.setObjectId("46e666dd37");
        person.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e == null){
                    Toast.makeText(TestActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(TestActivity.this, "删除失败:" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
