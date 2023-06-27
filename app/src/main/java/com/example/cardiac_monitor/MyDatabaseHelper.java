package com.example.cardiac_monitor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="cardiac.db";
    private static final String TABLE_NAME ="cardiac_details";
    private static final String  ID ="_id";
    private static final String SYSTOLIC ="systolic";
    private static final String DIASTOLIC ="diastolic";
    private static final String PRESURE_STATUS ="pressure_status";
    private static final String PULSE ="pulse";
    private static final String PULSE_STATUS ="pulse_status";
    private static final String DATE ="date";
    private static final String TIME ="time";
    private static final String COMMENTS ="comments";
    private static final String CREATE_TABLE ="CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+SYSTOLIC+" varchar,"+DIASTOLIC+" varchar,"+PRESURE_STATUS+" varchar,"+PULSE+" varchar,"+PULSE_STATUS+","+DATE+" varchar,"+TIME+" varchar,"+COMMENTS+" varchar(255));";
    private static final String SELECT_ALL = "SELECT * FROM cardiac_details WHERE _id = ?";
    private static final String UPDATE_DATA = "SELECT * FROM "+TABLE_NAME;
    private static final int VERSION_NUMBER = 1;
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;


    private final Context context;


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }catch (Exception e)
        {
            Toast.makeText(context,"Exception : "+e,Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        try{
            Toast.makeText(context,"onUpgrade is Called",Toast.LENGTH_SHORT).show();
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);

        }catch (Exception e)
        {
            Toast.makeText(context,"Exception : "+e,Toast.LENGTH_SHORT).show();
        }

    }

}

