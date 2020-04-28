package com.example.timezonehelper;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_TEXT = "ExtraText";
    private timeThread clock;
    private static TextView systemTime;
    private static String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* set myTimeZone */
        TextView myTimeZone = findViewById(R.id.myTimeZone);
        TimeZone timeZone = TimeZone.getDefault();
        myTimeZone.setText(timeZone.getDisplayName());

        /* set the current Time. */
        systemTime = findViewById(R.id.currentTime);
        clock = new timeThread();
        clock.start();

        Button searchButton = findViewById(R.id.button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openActivity2();
            }
        });

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

    public void openActivity2() {
        EditText TextInput = findViewById(R.id.textInput);
        String text = TextInput.getText().toString();

        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra(EXTRA_TEXT, text);
        startActivity(intent);
    }

    class timeThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                do {
                    SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm  E");
                    date = currentTime.format(new java.util.Date());
                    Thread.sleep(1000);
                    Message m = new Message();
                    MainActivity.m_Handler.sendMessage(m);
                } while (!MainActivity.timeThread.interrupted());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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

}

