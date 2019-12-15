package facci.pm.ta3.sqlite.trabajoautonomo3sqlite.database.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import facci.pm.ta3.sqlite.trabajoautonomo3sqlite.database.entities.ShoppingItemDB;

public class ShoppingElementHelper extends SQLiteOpenHelper {

    // if you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ShoppingElements.db";

    public ShoppingElementHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ShoppingItemDB.ShoppingElementEntry.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(ShoppingItemDB.ShoppingElementEntry.DELETE_TABLE);
        onCreate(db);
    }
}