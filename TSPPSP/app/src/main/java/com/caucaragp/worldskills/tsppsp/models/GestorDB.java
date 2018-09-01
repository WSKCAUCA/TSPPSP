package com.caucaragp.worldskills.tsppsp.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GestorDB extends SQLiteOpenHelper {
    //Constructor para la creación de la base de datos
    public GestorDB(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.TABLE_PROJECT);
        db.execSQL(Constants.TABLE_TIMELOG);
        db.execSQL(Constants.TABLE_DEFECTLOG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
