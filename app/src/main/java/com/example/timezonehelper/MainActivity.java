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

import com.example.timezonehelper.logic.TimeZoneOfCountry;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/** main activity of the app.*/

public class MainActivity extends AppCompatActivity {
    /** information carried to Activity 2. */
    public static final String EXTRA_TEXT = "ExtraText";
    /** information carried to Activity 2.*/
    public static final String EXTRA_TIME = "ExtraTime";
    /** timeThread instance that updates the time. */
    private timeThread clock;
    /** the TextView of the current time which can be updated. */
    private static TextView systemTime;
    /** the time and date information of the system in String. */
    private static String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set action to the toolbar. Top right can pull out menu.
        Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        setSupportActionBar(toolbar); // this line specficially adds a toolbar to action

        // set the current Time.
        //systemTime = findViewById(R.id.currentTime);
        systemTime = findViewById(R.id.currentTime); // set the current time to system time
        //clock = new timeThread();
        clock = new timeThread();
        clock.start();

        // set my time Zone using timezone.getDefault(); get the system's time Zone.
        TextView myTimeZone = findViewById(R.id.myTimeZone);
        TimeZone timeZone = TimeZone.getDefault();
        myTimeZone.setText(timeZone.getDisplayName());

        // set search button. OnClickListener is able to run openActivity2() once clicked.
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
        // it is located at the top right; 3 little dot menu.
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
            Intent about = new Intent(this, Activity_About.class);
            startActivity(about);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openActivity2() {
        EditText TextInput = findViewById(R.id.textInput);
        String userInput = TextInput.getText().toString();
        TextView errorMess = findViewById(R.id.error);

        if (userInput == null || userInput.length() < 2) {
            errorMess.setVisibility(View.VISIBLE);
        } else {
            TimeZoneOfCountry temp = new TimeZoneOfCountry();
            String upperInput = userInput.toUpperCase();
            if (!temp.isCountryExist(upperInput)) {
                errorMess.setVisibility(View.VISIBLE);
            } else if (temp.isCountryExist(upperInput)) {
                errorMess.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(this, Activity2.class);
                intent.putExtra(EXTRA_TEXT, upperInput);
                startActivity(intent);
            }
        }
    }

    /** timeThread class that extends Thread.
     * It is for counting the time and update the time every second.
     * It will be called by onCreate method when the app is first initialized.
     */
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

    /** Handler is called by timeThread class.
     * private instance variable date will be used to update the TextView systemTime.
     * This handler will update the private instance variable systemTime */
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

