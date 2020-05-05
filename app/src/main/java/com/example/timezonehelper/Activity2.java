package com.example.timezonehelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.timezonehelper.logic.TimeZoneOfCountry;
import com.ibm.icu.util.TimeZone;

import java.util.Set;

/** this activity 2 is for showing the timeZone that the user is looking for. */
public class Activity2 extends AppCompatActivity {

    private TimeZoneOfCountry temp1 = new TimeZoneOfCountry();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        // get information from MainActivity and store them in text.
        // text should be the country that the user entered in MainActivity.
        Intent intent = getIntent();
        String countryName = intent.getStringExtra(MainActivity.EXTRA_TEXT);

        // appear the country in Activity 2.
        TextView textView1 = findViewById(R.id.inputCountry);
        textView1.setText(countryName);

        // appear the time zone.
        showTimeZone(countryName);
        showZoneId(countryName);

    }

    public void showTimeZone(String NameOfCountry) {
        Set<TimeZone> timeZoneSet = temp1.timeZoneGetter(NameOfCountry);
        TextView textViewOfArray = findViewById(R.id.timeZone_array);

        for(TimeZone element : timeZoneSet) {
            String tempString = element.getDisplayName();
            textViewOfArray.append(tempString);
            textViewOfArray.append("\n");
        }
    }

    public void showZoneId(String NameOfCountry) {
        Set<String> zoneIdSet = temp1.zoneIdGetter(NameOfCountry);
        TextView zoneIdArray = findViewById(R.id.placeArray);

        for (String element : zoneIdSet) {
            zoneIdArray.append(element);
            zoneIdArray.append("\n");
        }
    }


}
