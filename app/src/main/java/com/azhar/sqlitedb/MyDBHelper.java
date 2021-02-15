package com.azhar.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


public class MyDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_details";
    public static final int VERSION_NUMBER = 2;
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String GENDER = "gender";
    public static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("
            +ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +NAME+" VARCHAR(255), "
            +AGE+" INTEGER, "
            +GENDER+" VARCHER(20)) ";

    private final Context context;
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
    public static final String RETRIEVE_ALL = "SELECT * FROM "+TABLE_NAME;

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null,VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //table creation query

        try {
            Toast.makeText(context, "Database Created", Toast.LENGTH_LONG).show();
            db.execSQL(CREATE_TABLE);

        }catch (Exception e){
            Toast.makeText(context, "Exception: "+e, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            Toast.makeText(context, "Database Upgraded", Toast.LENGTH_LONG).show();
            db.execSQL(DROP_TABLE);
            onCreate(db);

        }catch (Exception e){
            Toast.makeText(context, "Exception: "+e, Toast.LENGTH_LONG).show();
        }

    }

    //Input Data
    public long insertData(String name, String age, String gender){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,name);
        contentValues.put(AGE,age);
        contentValues.put(GENDER,gender);
        long rowId = sqLiteDatabase.insert(TABLE_NAME, null,contentValues);
        return rowId;
    }

    //Display All Data
    public Cursor displayAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(RETRIEVE_ALL, null);
        return cursor;
    }

    //Update Data
    public boolean updateData(String name, String age, String gender, String id){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(AGE, age);
        contentValues.put(GENDER, gender);
        contentValues.put(ID,id);

        sqLiteDatabase.update(TABLE_NAME, contentValues, ID + " = ?", new String[]{id});
        return true;
    }

    //Delete Data
    public int deleteData(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, ID + " = ? ", new String[]{id});
    }
}
