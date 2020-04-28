package com.example.timezonehelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/** this activity 2 is for showing the timeZone that the user is looking for. */
public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        // get information from MainActivity and store them in text.
        // text should be the country that the user entered in MainActivity.
        Intent intent = getIntent();
        String text = intent.getStringExtra(MainActivity.EXTRA_TEXT);

        // appear the country in Activity 2 UI.
        TextView textView1 = findViewById(R.id.inputCountry);
        textView1.setText(text);
    }
}
