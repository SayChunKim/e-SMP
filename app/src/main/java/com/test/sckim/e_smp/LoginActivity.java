package com.test.sckim.e_smp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends Activity {

    private EditText matricID = null;
    private EditText password = null;
    private TextView attempts;
    private Button login;
    private Button reset;
//    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
////        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
////        WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        matricID = (EditText) findViewById(R.id.matricNo);
        password = (EditText) findViewById(R.id.password);
//        attempts = (TextView)findViewById(R.id.textView5);
//        attempts.setText(Integer.toString(counter));
        login = (Button) findViewById(R.id.loginBtn);
        reset = (Button) findViewById(R.id.resetBtn);
    }

    public void loginClick(View view) {
        if ((matricID.getText().toString().equals("student") || (matricID.getText().toString().equals("a")) &&
                password.getText().toString().equals("student"))) {
            Toast.makeText(getApplicationContext(), "Login Successful!",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);

        } else {
            Toast.makeText(getApplicationContext(), "Wrong Credentials",
                    Toast.LENGTH_SHORT).show();
//            attempts.setBackgroundColor(Color.RED);
//            counter--;
//            attempts.setText(Integer.toString(counter));
//            if(counter==0){
//                login.setEnabled(false);
//            }
        }
    }

    public void resetClick(View view) {
        matricID.setText("");
        password.setText("");

    }

    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);

    }
}




