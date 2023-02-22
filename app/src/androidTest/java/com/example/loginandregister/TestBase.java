package com.example.loginandregister;

import android.content.Context;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TestBase {
    private static final String TAG = "TestBase";

    @Test
    public void testCreate(){
        //测试创建数据库
    }
    @Test
    public void testInsert(){
        //测试插入数据
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DBOpenHelper dao = new DBOpenHelper(appContext);
        dao.add("123","123");
        Log.d(TAG, "testInsert: ......");
    }
    @Test
    public void testDelete(){
        //测试删除数据
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DBOpenHelper dao = new DBOpenHelper(appContext);
        dao.delete("123","123");
    }
    @Test
    public void testUpdate(){
        //测试修改数据
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DBOpenHelper dao = new DBOpenHelper(appContext);
        dao.updata("123");
    }
    @Test
    public void testQuery(){
        //测试查询数据
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DBOpenHelper dao = new DBOpenHelper(appContext);
        dao.getAllData();
    }

}
