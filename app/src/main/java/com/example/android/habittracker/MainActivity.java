package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.habittracker.data.HabitContract.HabitEntry;
import com.example.android.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {


    public final static String TAG = MainActivity.class.getSimpleName();
    private HabitDbHelper mDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Instantiate the DBHelper with context
        mDbHelper = new HabitDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        insertHabit();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        //Set up values wanted in the return
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_IMPORTANCE,
                HabitEntry.COLUMN_HABIT_REPETITIONS,
                HabitEntry.COLUMN_HABIT_ALERT
        };

        //Create a cursor to get the values
        Cursor cursor = database.query(
                HabitEntry.TABLE_NAME, //The table I'm hitting
                projection,            //The values I want back followed by other options.
                null,
                null,
                null,
                null,
                null
        );

        //Bind to a view that will eventually display the results.

        /**
         * Use Try-Finally to attempt the transaction.
         *
         * Why do we not attempt to catch any potential errors with the transaction at this point.
         */
        try {

            /**
             * set up column indexes, so we can get the selected values that we want. keep in mind
             * these are likely associated("available") depending on the columns you return in the
             * projection.
             */

            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int importanceColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_IMPORTANCE);
            int repetitionColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_REPETITIONS);
            int alertColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_ALERT);

            while(cursor.moveToNext()) {

                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentRepetitions = cursor.getInt(repetitionColumnIndex);
                String currentAlert = grabAlertValue(cursor.getInt(alertColumnIndex));
                String currentImportanceLevel = grabImportanceValue(cursor.getInt(importanceColumnIndex));

                //Aggregate to a final return, or display values actively in the view
            }

            //DO SOMETHING WITH A VALUE THAT NEEDS TO BE RETURNED.
        } finally {
            cursor.close(); //ALWAYS CLOSE THE CURSOR WHEN YOU'RE DONE.

            /**
             *  NOTE: THINK OF DATABASE INTERACTIONS SIMILAR TO NETWORK INTERACTIONS. WE ALWAYS WANT
             *  TO BE NEAT AND CLEAN, AND CLOSE OUR CONNECTIONS WHEN WE ARE FINISHED.
             */

        }

    }

    private void insertHabit() {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        //key value pairs for the entries into the table.

        //Values that don't have limited options don't utilize constants in the value slot
        values.put(HabitEntry.COLUMN_HABIT_NAME, "Gym");
        values.put(HabitEntry.COLUMN_HABIT_IMPORTANCE, HabitEntry.IMPORTANCE_UBER_HIGH);
        values.put(HabitEntry.COLUMN_HABIT_REPETITIONS, 10);
        values.put(HabitEntry.COLUMN_HABIT_ALERT, HabitEntry.ALERT_TRUE);

        long newRowId = database.insert(HabitEntry.TABLE_NAME, null, values);

        //DO STUFF WITH THE RESULTING VALUE
    }

    private String grabImportanceValue (int dbValue) {
        String result;
        switch(dbValue) {
            case 0 :
                result = "Low";
                break;
            case 1 :
                result = "Medium";
                break;
            case 2 :
                result = "High";
                break;
            default :
                result = "UBER IMPORTANT";
        }

        return result;
    };

    private String grabAlertValue (int dbValue) {
        String result;
        switch(dbValue) {
            case 0 :
                result = "false";
                break;
            default :
                result = "true";
        }

        return result;
    }
}
