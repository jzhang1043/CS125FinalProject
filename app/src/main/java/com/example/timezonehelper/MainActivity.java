package com.example.timezonehelper;

import android.os.Bundle;


import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    private static SimpleDateFormat currentTime;
    private static TextView systemTime;
    private timeThread Clock;
    private static String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView textView1 = findViewById(R.id.textView1);
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView3 = findViewById(R.id.textView3);

        /* set myTimeZone */
        TextView myTimeZone = findViewById(R.id.myTimeZone);
        TimeZone timeZone = TimeZone.getDefault();
        myTimeZone.setText(timeZone.getDisplayName());

        /* set the current Time. */
        systemTime = findViewById(R.id.currentTime);
        Clock = new timeThread();
        Clock.start();

        TextInputLayout textInputLayout = findViewById(R.id.textInput);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public static Handler m_Handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    systemTime.setText(date);
                    break;
            }
        }
    };
    class timeThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                do {
                    currentTime = new SimpleDateFormat("HH:mm  E");
                    date = currentTime.format(new java.util.Date());
                    Thread.sleep(1000);
                    Message m = new Message();
                    MainActivity.m_Handler.sendMessage(m);
                } while (!timeThread.interrupted());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

