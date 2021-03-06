package org.wiky.letscorp.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wiky on 7/13/16.
 * 数据库处理
 */
public class SQLHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "letscorp";

    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PostItemHelper.SQL_CREATE_TABLE);
        db.execSQL(PostHelper.SQL_CREATE_TABLE);
        db.execSQL(QueryHelper.SQL_CREATE_TABLE);
    }

    private void recreate(SQLiteDatabase db) {
        db.execSQL(PostItemHelper.SQL_DELETE_TABLE);
        db.execSQL(PostHelper.SQL_DELETE_TABLE);
        db.execSQL(QueryHelper.SQL_DELETE_TABLE);

        db.execSQL(PostItemHelper.SQL_CREATE_TABLE);
        db.execSQL(PostHelper.SQL_CREATE_TABLE);
        db.execSQL(QueryHelper.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 1 && newVersion == 2) {
            db.execSQL(PostHelper.SQL_DELETE_TABLE);
            db.execSQL(PostItemHelper.SQL_DELETE_TABLE);
            db.execSQL(PostItemHelper.SQL_CREATE_TABLE);
            db.execSQL(PostHelper.SQL_CREATE_TABLE);
        } else if (oldVersion == 2 && newVersion == 3) {
            PostHelper.clear();
            PostItemHelper.clear();
        }
    }
}
