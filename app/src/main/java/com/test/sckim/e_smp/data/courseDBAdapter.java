package com.test.sckim.e_smp.data;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class courseDBAdapter extends SQLiteOpenHelper {

        public static final String ROW_ID = "_id";
        public static final String COURSE_CODE = "courseCode";
        public static final String COURSE_TITLE = "courseTitle";
        public static final String COURSE_SET = "courseSet";
        public static final String COURSE_TIME = "courseTime";
        public static final String COURSE_HOUR = "hour";
        public static final String COURSE_ROOM = "courseRoom";

        private static final String TAG = "CourseDBAdapter";
        private static final String LOG_TAG = "courseDb";
        private static final String DATABASE_NAME = "CourseData";
        private static final Integer DATABASE_VERSION = 1;
        public static final String SQLITE_TABLE = "CourseRegistration";
//        private DatabaseHelper mDbHelper;
        private static SQLiteDatabase mDB;
        static Cursor mCursor;
//        private final Context mCtx;





        /** Constructor */
        public courseDBAdapter(Context context) {
            super(context, DATABASE_NAME , null, DATABASE_VERSION);
            this.mDB = getWritableDatabase();
        }




    public static Cursor fetchCoursesByName(String inputText) throws SQLException {
        Log.w(TAG, inputText);

        if (inputText == null  ||  inputText.length () == 0)  {
            mCursor = mDB.query(SQLITE_TABLE, new String[] {ROW_ID,
                            COURSE_CODE, COURSE_TITLE, COURSE_TIME, COURSE_SET, COURSE_HOUR, COURSE_ROOM },
                    null, null, null, null, null);

        }
        else {
            mCursor = mDB.query(true, SQLITE_TABLE, new String[] {ROW_ID,
                    COURSE_CODE, COURSE_TITLE, COURSE_TIME, COURSE_SET, COURSE_HOUR, COURSE_ROOM },
                    COURSE_TITLE + " like '%" + inputText + "%'", null,
                    null, null, null, null);
        }
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }


  @Override
        public void onCreate(SQLiteDatabase db) {

      String DATABASE_SQL =
              "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
                      ROW_ID + " INTEGER primary key AUTOINCREMENT, " +
                      COURSE_CODE + " text ," +
                      COURSE_TITLE + " text ," +

                      COURSE_TIME + " text ," +
                      COURSE_SET + " text ," +
                      COURSE_HOUR + " , " +
                      COURSE_ROOM + " , " +
                      "  UNIQUE (" + COURSE_CODE + "));";


      Log.w(TAG, DATABASE_SQL);
      db.execSQL(DATABASE_SQL);

      DATABASE_SQL = "insert into " + SQLITE_TABLE + " ( " + COURSE_CODE + "," + COURSE_TITLE + "," + COURSE_TIME + "," + COURSE_SET + "," + COURSE_HOUR + "," + COURSE_ROOM + " ) "
              + " values ( 'CSMM2101', 'Statistic & Maths', 'MON 0830', 1, 2, 'BK001FTSM' )";
      db.execSQL(DATABASE_SQL);

      DATABASE_SQL = "insert into " + SQLITE_TABLE + " ( " + COURSE_CODE + "," + COURSE_TITLE + "," + COURSE_TIME + "," + COURSE_SET + "," + COURSE_HOUR + "," + COURSE_ROOM + " ) "
              + " values ( 'CSMM2102', 'Maths & Probability', 'MON 0830', 1, 2, 'BK002FTSM' )";
      db.execSQL(DATABASE_SQL);

      DATABASE_SQL = "insert into " + SQLITE_TABLE + " ( " + COURSE_CODE + "," + COURSE_TITLE + "," + COURSE_TIME + "," + COURSE_SET + "," + COURSE_HOUR + "," + COURSE_ROOM + " ) "
              + " values ( 'CSCS2104', 'C++ Programming', 'TUE 1230', 1, 2, 'DKFTSM' )";
      db.execSQL(DATABASE_SQL);

      DATABASE_SQL = "insert into " + SQLITE_TABLE + " ( " + COURSE_CODE + "," + COURSE_TITLE + "," + COURSE_TIME + "," + COURSE_SET + "," + COURSE_HOUR + "," + COURSE_ROOM + " ) "
              + " values ( 'CSCS2103', 'Programming Algorithm & Design', 'TUE 0930', 1, 2, 'DKFTSM' )";
      db.execSQL(DATABASE_SQL);

      DATABASE_SQL = "insert into " + SQLITE_TABLE + " ( " + COURSE_CODE + "," + COURSE_TITLE + "," + COURSE_TIME + "," + COURSE_SET + "," + COURSE_HOUR + "," + COURSE_ROOM + " ) "
              + " values ( 'CSCK2203', 'History Culture', 'WED 0930', 1, 2, 'MULTIFTSM' )";
      db.execSQL(DATABASE_SQL);


  }

        public Cursor getAllCourses() {
            Cursor mCursor = mDB.query(SQLITE_TABLE, new String[]{ROW_ID,
                            COURSE_CODE, COURSE_TITLE, COURSE_TIME, COURSE_SET, COURSE_HOUR, COURSE_ROOM},
                    null, null, null, null, null);

            if (mCursor != null) {
                mCursor.moveToFirst();
            }
            return mCursor;

        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
        onCreate(db);
    }

}

