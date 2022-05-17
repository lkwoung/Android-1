package com.example.localdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, "memodb", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String memoSQL = "create table tb_memo" +
                "(_id integer primary key autoincrement," +
                "title," +
                "content)";
        db.execSQL(memoSQL);
    }

    //DATABASE_VERSION이 증가하면 실행되며 테이블을 다시 작성해줌
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == DATABASE_VERSION){
            db.execSQL("drop table tb_memo");
            onCreate(db);
        }

    }
}
