package com.example.loginandregister;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class DBOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = "DBOpenHelper";
    private SQLiteDatabase db;



    public DBOpenHelper(Context context){
        super(context,Contents.DATABASE_NAME,null,Contents.VERSION_CODE);
        db = getReadableDatabase();
    }



    @Override
    public void onCreate(SQLiteDatabase db){
        Log.d(TAG, "onCreate: ......");
        db.execSQL("CREATE TABLE IF NOT EXISTS user(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "password TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.d(TAG, "onUpgrade: ......");
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }


    public void add(String name,String password){
        db.execSQL("INSERT INTO user (name,password) VALUES(?,?)",new Object[]{name,password});
    }
    public void delete(String name,String password){
        db.execSQL("DELETE FROM user WHERE name = AND password ="+name+password);
    }
    public void updata(String password){
        db.execSQL("UPDATE user SET password = ?",new Object[]{password});
    }

    /* 在游的过程中把游出来的数据存放到list容器中
     * @return
     */
    public ArrayList<User> getAllData(){

        ArrayList<User> list = new ArrayList<User>();
        Cursor cursor = db.query("user",null,null,null,null,null,"name DESC");
        while(cursor.moveToNext()){
            int name_id=cursor.getColumnIndex("name");
            int passwd_id=cursor.getColumnIndex("password");
            String name = cursor.getString(name_id);
            String password = cursor.getString(passwd_id);
            list.add(new User(name,password));
        }
        return list;
    }
}

