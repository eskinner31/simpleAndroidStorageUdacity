package com.example.android.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.habittracker.data.HabitContract.HabitEntry;

/**
 * Created by Skinner on 1/29/17.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    public static final String TAG = HabitDbHelper.class.getSimpleName();

    public static final String DATABASE_NAME = "habit.db";
    public static final int DATABASE_VERSION = 1;

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String SQL_CREATE_HABITS_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_HABIT_IMPORTANCE + " INTEGER NOT NULL, "
                + HabitEntry.COLUMN_HABIT_ALERT + " INTEGER NOT NULL, "
                + HabitEntry.COLUMN_HABIT_REPETITIONS + " INTEGER NOT NULL DEFAULT 1);";

        sqLiteDatabase.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    //Implemented method, No upgrade code since version will remain one.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}
}
