package com.pddstudio.pocketutils;
/*
 * This Class was created by Patrick J
 * on 09.03.16. For more Details and licensing information
 * have a look at the README.md
 */

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public final class DateUtils {

    public static int getCurrentYear() {
        Calendar calendar = GregorianCalendar.getInstance(Locale.getDefault());
        return calendar.get(Calendar.YEAR);
    }

    public static int getCurrentMonth() {
        //returns the current month, starting from 0 = January
        Calendar calendar = GregorianCalendar.getInstance(Locale.getDefault());
        return calendar.get(Calendar.MONTH);
    }

    public static int getValueForMonth(String monthValue) {
        switch (monthValue) {
            case "Jan":
                return 1;
            case "Feb":
                return 2;
            case "Mar":
                return 3;
            case "Apr":
                return 4;
            case "May":
                return 5;
            case "Jun":
                return 6;
            case "Jul":
                return 7;
            case "Aug":
                return 8;
            case "Sep":
                return 9;
            case "Oct":
                return 10;
            case "Nov":
                return 11;
            case "Dec":
                return 12;
        }
        return -1;
    }

    public static String getCurrentDate() {
        Calendar calendar = GregorianCalendar.getInstance(Locale.getDefault());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(calendar.get(Calendar.YEAR))
                .append(Preferences.get().getDateSeperator())
                .append((calendar.get(Calendar.MONTH)+1))
                .append(Preferences.get().getDateSeperator())
                .append(calendar.get(Calendar.DATE));
        return stringBuilder.toString();
    }

}
