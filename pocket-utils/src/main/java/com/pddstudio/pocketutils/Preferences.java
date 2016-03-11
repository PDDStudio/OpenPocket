package com.pddstudio.pocketutils;
/*
 * This Class was created by Patrick J
 * on 11.03.16. For more Details and licensing information
 * have a look at the README.md
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public final class Preferences {

    private static Preferences preferences;

    private static final String TRANSPARENT_NAVIGATION_BAR = "tNavBar";
    private static final String CURRENCY = "currency";

    private final Context context;
    private final SharedPreferences sharedPreferences;

    private Preferences(Context context) {
        this.context = context;
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void init(Context context) {
        if(preferences == null) preferences = new Preferences(context);
    }

    public static Preferences get() {
        return preferences;
    }

    public boolean transparentNavigationBar() {
        return sharedPreferences.getBoolean(TRANSPARENT_NAVIGATION_BAR, true);
    }

    public String getCurrencySymbol() {
        return sharedPreferences.getString(CURRENCY, "$");
    }

    public void setTransparentNavigationBar(boolean transparentNavigationBar) {
        sharedPreferences.edit().putBoolean(TRANSPARENT_NAVIGATION_BAR, transparentNavigationBar).apply();
    }

    public void setCurrencySymbol(String currencySymbol) {
        sharedPreferences.edit().putString(CURRENCY, currencySymbol).apply();
    }

}
