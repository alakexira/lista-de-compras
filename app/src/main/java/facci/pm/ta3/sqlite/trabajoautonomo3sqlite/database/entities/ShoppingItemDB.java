package facci.pm.ta3.sqlite.trabajoautonomo3sqlite.database.entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import facci.pm.ta3.sqlite.trabajoautonomo3sqlite.database.helper.ShoppingElementHelper;
import facci.pm.ta3.sqlite.trabajoautonomo3sqlite.database.model.ShoppingItem;
import java.util.ArrayList;

public class ShoppingItemDB {

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    private ShoppingElementHelper dbHelper;

    public ShoppingItemDB(Context context) {
        // Create new helper
        dbHelper = new ShoppingElementHelper(context);
    }

    /* Inner class that defines the table contents */
    public static abstract class ShoppingElementEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_TITLE = "title";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                COLUMN_NAME_TITLE + TEXT_TYPE + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }


    public void insertElement(String productName) {
        // INSERTAR un Item a la Base de datos

        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues contentValues = new ContentValues();
        contentValues.put(ShoppingElementEntry.COLUMN_NAME_TITLE, productName);

        // Insert the new row, returning the primary key value of the new row
        db.insert(ShoppingElementEntry.TABLE_NAME, null, contentValues);
    }


    public ArrayList<ShoppingItem> getAllItems() {

        ArrayList<ShoppingItem> shoppingItems = new ArrayList<>();

        String[] allColumns = { ShoppingElementEntry._ID,
            ShoppingElementEntry.COLUMN_NAME_TITLE};

        Cursor cursor = dbHelper.getReadableDatabase().query(
            ShoppingElementEntry.TABLE_NAME,    // The table to query
            allColumns,                         // The columns to return
            null,                               // The columns for the WHERE clause
            null,                               // The values for the WHERE clause
            null,                               // don't group the rows
            null,                               // don't filter by row groups
            null                                // The sort order
        );

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            ShoppingItem shoppingItem = new ShoppingItem(getItemId(cursor), getItemName(cursor));
            shoppingItems.add(shoppingItem);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        dbHelper.getReadableDatabase().close();
        return shoppingItems;
    }

    private long getItemId(Cursor cursor) {
        return cursor.getLong(cursor.getColumnIndexOrThrow(ShoppingElementEntry._ID));
    }

    private String getItemName(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndexOrThrow(ShoppingElementEntry.COLUMN_NAME_TITLE));
    }


    public void clearAllItems() {
        // ELIMINAR todos los Items de la Base de datos

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.delete(ShoppingElementEntry.TABLE_NAME,  null,  null);

    }

    public void updateItem(ShoppingItem shoppingItem) {
        // ACTUALIZAR un Item en la Base de datos

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ShoppingElementEntry.COLUMN_NAME_TITLE,shoppingItem.getName());
        String selection = ShoppingElementEntry._ID + "=?";
        String[] selectionArgs = {String.valueOf(shoppingItem.getId())};
        db.update(ShoppingElementEntry.TABLE_NAME,contentValues,selection,selectionArgs);

    }

    public void deleteItem(ShoppingItem shoppingItem) {
        // ELIMINAR un Item de la Base de datos

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = ShoppingElementEntry.COLUMN_NAME_TITLE + "LIKE ?";
        String[] selectionArgs = {shoppingItem.getName()};
        db.delete(ShoppingElementEntry.TABLE_NAME,selection,selectionArgs);
    }
}
