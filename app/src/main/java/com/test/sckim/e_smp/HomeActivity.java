package com.test.sckim.e_smp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;


public class HomeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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

        if (id == R.id.action_share){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "This App is Definitely what we wishing for! #eSMPDemo");
            sendIntent.setType("image/*");
            Uri path = Uri.fromFile(new File("android.resource://com.test.sckim.e_smp/"+ R.drawable.coming_soon));
            sendIntent.putExtra(Intent.EXTRA_STREAM, path );
            Intent mailer = Intent.createChooser(sendIntent, null);
            startActivity(mailer);
//            startActivity(sendIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);

    }

    public void profileClick(View view){
//        Toast.makeText(getApplicationContext(), "Clicked 1",
//                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
        startActivity(intent);
}

    public void courseRegistrationClick(View view) {
        Toast.makeText(getApplicationContext(), "Clicked 2",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(HomeActivity.this, CourseRegistrationActivity.class);
        startActivity(intent);
    }

    public void timetableClick(View view){
        Toast.makeText(getApplicationContext(), "Clicked 3",
                Toast.LENGTH_SHORT).show();
        new AlertDialog.Builder(this)
                .setTitle("Info")
                .setMessage("Your course timetable will be shown after registration has been verified from our server. Thank you")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .show();
    }

    public void courseVerifyClick(View view){
        Toast.makeText(getApplicationContext(), "Clicked 4",
                Toast.LENGTH_SHORT).show();
        new AlertDialog.Builder(this)
                .setTitle("Info")
                .setMessage("Course Registration Form Verification Period for Undergraduate expired!")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .show();
    }


    public void resultClick(View view){
        Toast.makeText(getApplicationContext(), "Clicked 5",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(HomeActivity.this, ExamResultActivity.class);
        startActivity(intent);
    }

    public void logoutClick(View view){
        Toast.makeText(getApplicationContext(), "Logging Out",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
