package com.test.sckim.e_smp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.test.sckim.e_smp.data.courseDBAdapter;
import com.test.sckim.e_smp.data.courseDBProvider;

//import com.test.sckim.e_smp.data.DBProvider;


public class CourseRegistrationActivity extends ActionBarActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private SimpleCursorAdapter dataAdapter;
    private courseDBAdapter dbHelper;
    private String courseCode;
    private String courseName;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_registration);
//        dbHelper = new courseDBAdapter(this);
//        dbHelper.open();
//        dbHelper.deleteAllCourses();
//        dbHelper.insertSomeCourses();
        displayListView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_course_registration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Starts a new or restarts an existing Loader in this manager
//        getLoaderManager().restartLoader(0, null, this);
    }

    private void displayListView() {

        Button editButton = (Button)findViewById(R.id.course_edit);
        editButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    new AlertDialog.Builder(CourseRegistrationActivity.this)
                            .setTitle("Info")
                            .setMessage("This Feature is currently unavailable. Sorry for such inconvenience.")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .show();
                }
        });


//        Cursor cursor = dbHelper.fetchAllCourses();
//        The desired columns to be bound
        String[] columns = new String[]{
                courseDBAdapter.COURSE_CODE,
                courseDBAdapter.COURSE_TITLE,
                courseDBAdapter.COURSE_TIME,
//                courseDB.COURSE_HOUR,
                courseDBAdapter.COURSE_SET,
                courseDBAdapter.COURSE_ROOM
        };

        int[] to = new int[]{
                R.id.course_code,
                R.id.course_title,
                R.id.course_time,
//                R.id.course_hour,
                R.id.course_set,
                R.id.course_room
        };

        dataAdapter = new SimpleCursorAdapter(
                getBaseContext(),
                R.layout.course_info,
                null,
                columns,
                to,
                0
        );
        // get reference to the ListView
        listView = (ListView) findViewById(R.id.courseList);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);
//        //Ensures a loader is initialized and active.
        getSupportLoaderManager().initLoader(0, null, this);







        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                // Get the cursor, positioned to the corresponding row in the result set
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                // display the selected country
                courseCode =
                        cursor.getString(cursor.getColumnIndexOrThrow(courseDBAdapter.COURSE_CODE));
                courseName =
                        cursor.getString(cursor.getColumnIndexOrThrow(courseDBAdapter.COURSE_TITLE));
//                Toast.makeText(getApplicationContext(),
//                        courseCode, Toast.LENGTH_SHORT).show();



                AlertDialog.Builder adb=new AlertDialog.Builder(CourseRegistrationActivity.this);
                adb.setTitle("Registration");
                adb.setMessage("Do you want to register course " + courseCode + " : " + courseName +" ? ");
                adb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       Toast.makeText(getApplicationContext(),
                               "Successfully Registered!!", Toast.LENGTH_SHORT).show();
                    }
                });
                adb.setNegativeButton("No", null);
                adb.show();
            }
        });


        EditText myFilter = (EditText) findViewById(R.id.searchView);
        myFilter.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                dataAdapter.getFilter().filter(s.toString());
            }
        });

        dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) {
                return courseDBAdapter.fetchCoursesByName(constraint.toString());
            }
        });
//                String rowId =
//                        cursor.getString(cursor.getColumnIndexOrThrow(courseDBAdapter.ROW_ID));
//
//                // starts a new Intent to update/delete a Country
//                // pass in row Id to create the Content URI for a single row
//                Intent courseEdit = new Intent(getBaseContext(), CourseAddEdit.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("mode", "update");
//                bundle.putString("rowId", rowId);
//                courseEdit.putExtras(bundle);
//                startActivity(courseEdit);
//
//            }
//        });
//    }
//
//    // This is called when a new Loader needs to be created.
//    @Override
//    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
//        String[] projection = {
//                courseDBAdapter.COURSE_CODE,
//                courseDBAdapter.COURSE_TITLE,
//                courseDBAdapter.COURSE_TIME,
////                courseDB.COURSE_HOUR,
//                courseDBAdapter.COURSE_SET,
//                courseDBAdapter.COURSE_ROOM
//        };
//        CursorLoader cursorLoader = new CursorLoader(this,
//                DBProvider.CONTENT_URI, projection, null, null, null);
//        return cursorLoader;
//    }
//
//    @Override
//    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//        // Swap the new cursor in.  (The framework will take care of closing the
//        // old cursor once we return.)
//        dataAdapter.swapCursor(data);
//    }
//
//    @Override
//    public void onLoaderReset(Loader<Cursor> loader) {
//        // This is called when the last Cursor provided to onLoadFinished()
//        // above is about to be closed.  We need to make sure we are no
//        // longer using it.
//        dataAdapter.swapCursor(null);
//    }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = courseDBProvider.CONTENT_URI;
        String[] projection = {
                courseDBAdapter.COURSE_CODE,
                courseDBAdapter.COURSE_TITLE,
                courseDBAdapter.COURSE_TIME,
//                courseDB.COURSE_HOUR,
                courseDBAdapter.COURSE_SET,
                courseDBAdapter.COURSE_ROOM
        };
        return new CursorLoader(this, uri, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        dataAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        dataAdapter.swapCursor(null);
    }
}
