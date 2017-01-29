package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by Skinner on 1/29/17.
 */

public class HabitContract {

    public final static String TAG = HabitContract.class.getSimpleName();

    private HabitContract() {};

    public final static class HabitEntry implements BaseColumns {

        //Would it still be acceptable to utilize strings.xml for the values here?
        public final static String TABLE_NAME = "tracked_habits";

        //Are there rules for the ordering of "final" and "static" when declaring a variable.
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_HABIT_NAME = "name";
        public final static String COLUMN_HABIT_IMPORTANCE = "importance";
        public final static String COLUMN_HABIT_REPETITIONS = "repetitions";
        public final static String COLUMN_HABIT_ALERT = "alert";

        //Values for importance
        public final static int IMPORTANCE_LOW = 0;
        public final static int IMPORTANCE_MODERATE = 1;
        public final static int IMPORTANCE_HIGH = 2;
        public final static int IMPORTANCE_UBER_HIGH = 3;

        //Values for alert
        public final static int ALERT_FALSE = 0;
        public final static int ALERT_TRUE = 1;
    }

}
