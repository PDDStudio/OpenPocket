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

}
