package com.example.cardiac_monitor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * A helper class for managing a SQLite database for cardiac data.
 * Provides methods for creating, upgrading, inserting, updating, and retrieving data from the database.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    // Database constants
    private static final String DATABASE_NAME = "cardiac.db";
    private static final String TABLE_NAME = "cardiac_details";

    // Table and column constants
    private static final String ID = "_id";
    private static final String SYSTOLIC = "systolic";
    private static final String DIASTOLIC = "diastolic";
    private static final String PRESURE_STATUS = "pressure_status";
    private static final String PULSE = "pulse";
    private static final String PULSE_STATUS = "pulse_status";
    private static final String DATE = "date";
    private static final String TIME = "time";
    private static final String COMMENTS = "comments";

    // SQL statements
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SYSTOLIC + " varchar," + DIASTOLIC + " varchar," + PRESURE_STATUS + " varchar," + PULSE + " varchar," + PULSE_STATUS + "," + DATE + " varchar," + TIME + " varchar," + COMMENTS + " varchar(255));";
    private static final String SELECT_ALL = "SELECT * FROM cardiac_details WHERE _id = ?";
    private static final String UPDATE_DATA = "SELECT * FROM " + TABLE_NAME;
    private static final int VERSION_NUMBER = 1;
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;


    //By storing the context variable as a member variable in the MyDatabaseHelper class, it can be accessed and used throughout the class's methods.
    private final Context context;



    /**
     * Constructs a new instance of MyDatabaseHelper.
     *
     * @param context The context of the application.
     */
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }


    /**
     * Called when the database is created for the first time.
     *
     * @param sqLiteDatabase The SQLite database instance.
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            sqLiteDatabase.execSQL(CREATE_TABLE);
        } catch (Exception e) {
            Toast.makeText(context, "Exception : " + e, Toast.LENGTH_SHORT).show();
        }


    }


    /**
     * Called when the database needs to be upgraded.
     *
     * @param sqLiteDatabase The SQLite database instance.
     * @param i1    The old version of the database.
     * @param i    The new version of the database.
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        try {
            Toast.makeText(context, "onUpgrade is Called", Toast.LENGTH_SHORT).show();
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);

        } catch (Exception e) {
            Toast.makeText(context, "Exception : " + e, Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Inserts data into the cardiac_details table in the database.
     *
     * @param systolic  The systolic value to be inserted.
     * @param diastolic The diastolic value to be inserted.
     * @param pre_stat  The pressure status to be inserted.
     * @param pulse     The pulse value to be inserted.
     * @param pul_stat  The pulse status to be inserted.
     * @param date      The date to be inserted.
     * @param time      The time to be inserted.
     * @param comments  The comments to be inserted.
     * @return The row ID of the newly inserted record.
     */
    public long insertData(String systolic, String diastolic, String pre_stat, String pulse, String pul_stat, String date, String time, String comments) {

        //Get a writable database instance
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        // Create a new ContentValues object to store the column-value pairs for the new record
        ContentValues contentValues = new ContentValues();

        // Put the values for each column into the ContentValues object
        contentValues.put(SYSTOLIC, systolic);
        contentValues.put(DIASTOLIC, diastolic);
        contentValues.put(PRESURE_STATUS, pre_stat);
        contentValues.put(PULSE, pulse);
        contentValues.put(PULSE_STATUS, pul_stat);
        contentValues.put(DATE, "Date: " + date);
        contentValues.put(TIME, "Time: " + time);
        contentValues.put(COMMENTS, "Comments: " + comments);

        // Insert the new record into the database table and return the row ID of the newly inserted record
        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    /**
     * Updates data in the cardiac_details table in the database.
     *
     * @param id        The ID of the record to be updated.
     * @param systolic  The new systolic value.
     * @param diastolic The new diastolic value.
     * @param pre_stat  The new pressure status.
     * @param pulse     The new pulse value.
     * @param pul_stat  The new pulse status.
     * @param date      The new date.
     * @param time      The new time.
     * @param comments  The new comments.
     * @return true if the update was successful, false otherwise.
     */
    public Boolean updateData(String id, String systolic, String diastolic, String pre_stat, String pulse, String pul_stat, String date, String time, String comments) {

      //Obtain a writable instance of the SQLiteDatabase using getWritableDatabase() method.
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(SYSTOLIC, systolic);
        contentValues.put(DIASTOLIC, diastolic);
        contentValues.put(PRESURE_STATUS, pre_stat);
        contentValues.put(PULSE, pulse);
        contentValues.put(PULSE_STATUS, pul_stat);
        contentValues.put(DATE, "Date: " + date);
        contentValues.put(TIME, "Time: " + time);
        contentValues.put(COMMENTS, "Comments: " + comments);

        //update table table_name where _id = id
        sqLiteDatabase.update(TABLE_NAME, contentValues, "_id = ?", new String[]{id});

        return true;
    }

    /**
     *Deletes a record from the database based on the provided ID.
     *@param id The ID of the record to be deleted.
     *@return The number of rows affected by the deletion operation.
     */
    public long deleteData(String id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //delete from table_name where ID = id
        return sqLiteDatabase.delete(TABLE_NAME, ID + " = ?", new String[]{id});
    }


    /**
     * Populates a ListView with data retrieved from the database.
     *
     * @return A SimpleCursorAdapter for binding data to the ListView.
     */
    public SimpleCursorAdapter populateListViewFromDB() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        //Define an array columns that represents the columns to retrieve from the database table.
        String[] columns = {MyDatabaseHelper.ID, MyDatabaseHelper.SYSTOLIC, MyDatabaseHelper.DIASTOLIC, MyDatabaseHelper.PRESURE_STATUS, MyDatabaseHelper.PULSE, MyDatabaseHelper.PULSE_STATUS, MyDatabaseHelper.DATE, MyDatabaseHelper.TIME, MyDatabaseHelper.COMMENTS};

        /*
        *The query() method is used to perform a SELECT query on the SQLite database.
        * The query() method returns a Cursor object, which provides access to the retrieved data.
        * The Cursor allows you to iterate over the rows and retrieve values from the columns.
         */

        Cursor cursor = sqLiteDatabase.query(MyDatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);

        // This array specifies the column names from the database that correspond to the views in the layout that will be populated.
        String[] fromFieldNames = new String[]{
                MyDatabaseHelper.ID, MyDatabaseHelper.SYSTOLIC, MyDatabaseHelper.DIASTOLIC, MyDatabaseHelper.PRESURE_STATUS, MyDatabaseHelper.PULSE, MyDatabaseHelper.PULSE_STATUS, MyDatabaseHelper.DATE, MyDatabaseHelper.TIME, MyDatabaseHelper.COMMENTS
        };

        // toViewId array specifies the IDs of the views in the layout where the data will be displayed.
        int[] toViewId = new int[]{
                R.id.item_id, R.id.systol, R.id.diastol, R.id.pressure_stat, R.id.pulse, R.id.pulse_status, R.id.date, R.id.time, R.id.comments
        };
        return new SimpleCursorAdapter(context, R.layout.sample_list, cursor, fromFieldNames, toViewId);

    }

}


