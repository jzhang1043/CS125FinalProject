package com.example.timezonehelper.logic;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CountryName {
    private Map<String, String> countries = new HashMap<>();

    public CountryName() {
        for (String iso : Locale.getISOCountries()) {
            Locale l = new Locale("", iso);
            countries.put(l.getDisplayCountry(), iso);
        }
    }
    public String getISO(final String country) {
        return countries.get(country);
    }

}
