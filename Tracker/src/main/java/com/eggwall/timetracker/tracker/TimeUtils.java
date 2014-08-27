package com.eggwall.timetracker.tracker;

import android.content.res.Resources;

/**
 * Utilities to manage time formatting.
 */
public class TimeUtils {

    /** Returns a human readable string for the time since last work start */
    public static String timeSinceStart(long endTime, long startTime, Resources r) {
        if (startTime < 0) {
            return r.getString(R.string.stopped_string);
        }
        long secondsSinceStart = (endTime - startTime) / 1000;
        long hours = secondsSinceStart / 3600;
        long minutes = (secondsSinceStart - hours*3600) / 60;
        long seconds = secondsSinceStart - hours*3600 - minutes*60;
        return zeroPaddedNumber(hours)
                + ":" + zeroPaddedNumber(minutes)
                + ":" + zeroPaddedNumber(seconds);
    }

    private static String zeroPaddedNumber(long number) {
        if (number < 10) {
            return "0" + number;
        }
        return "" + number;
    }
}
