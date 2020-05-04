package com.example.timezonehelper.logic;

import icu.util

public class TimeZoneOfCountry {
    private String country;
    private String timeZone;


    public TimeZoneOfCountry(String setCountry) {
        country = setCountry;
        }
    }

    public String findTimeZone(final String country) {
        if (country == null) {
            return null;
        }


    }

    public String getCountry() {
        return country;
    }

    public String getTimeZone() {
        return timeZone;
    }


}
