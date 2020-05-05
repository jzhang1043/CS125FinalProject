package com.example.timezonehelper.logic;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.ibm.icu.util.TimeZone;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class TimeZoneOfCountry {

    /** map that stores all countryCode and its timeZones. */
    private static Map<String, Set<TimeZone>> availableTimezones = new HashMap<String, Set<TimeZone>>();
    private static Map<String, Set<String>> timezoneID = new HashMap<String, Set<String>>();

    /** constructor. */
    public TimeZoneOfCountry() {
        //set up the map.
        for (Locale locale : Locale.getAvailableLocales()) {
            //get country code.
            final String countryCode = locale.getCountry();

            Set<TimeZone> timezones = availableTimezones.get(countryCode);
            Set<String> zoneID = timezoneID.get(countryCode);

            if (timezones == null) {
                timezones = new HashSet<TimeZone>();
                zoneID = new HashSet<String>();
                availableTimezones.put(countryCode, timezones);
                timezoneID.put(countryCode, zoneID);
            }

            // Find all timezones for that country (code) using ICU4J
            for (String id : com.ibm.icu.util.TimeZone.getAvailableIDs(countryCode)) {
                // Add timezone to the map
                timezones.add(TimeZone.getTimeZone(id));
                zoneID.add(id);
            }
        }
    }

    public Set<TimeZone> timeZoneGetter(String countryCode) {
        return availableTimezones.get(countryCode);
    }

    public Set<String> zoneIdGetter(String countryCode) {
        return timezoneID.get(countryCode);
    }

    public boolean isCountryExist(String countryCode) {
        for (Map.Entry<String, Set<TimeZone>> entry : availableTimezones.entrySet()) {
            String key = entry.getKey();
            if (key.equals(countryCode)) {
                return true;
            }
        }
        return false;
    }
}