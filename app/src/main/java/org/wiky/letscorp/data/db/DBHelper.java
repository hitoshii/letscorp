package org.wiky.letscorp.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wiky on 7/13/16.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "letscorp";

    public DBHelper(Context context) {
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
        if(oldVersion==1&&newVersion==2){
            db.execSQL(QueryHelper.SQL_CREATE_TABLE);
        } else if (newVersion == 3 && oldVersion == 2) {
            db.execSQL(PostItemHelper.SQL_DELETE_TABLE);
            db.execSQL(PostItemHelper.SQL_CREATE_TABLE);
        } else if (newVersion == 4 && oldVersion == 3) {
            db.execSQL(String.format("ALTER TABLE %s ADD %s INTEGER NOT NULL DEFAULT 0", PostHelper.TABLE_NAME, PostHelper.COLUMN_NAME_ID));
        } else {
            recreate(db);
        }
    }
}
