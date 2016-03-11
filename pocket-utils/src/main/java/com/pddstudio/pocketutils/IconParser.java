package com.pddstudio.pocketutils;
/*
 * This Class was created by Patrick J
 * on 11.03.16. For more Details and licensing information
 * have a look at the README.md
 */

import android.content.Context;
import android.util.Log;

import com.mikepenz.iconics.Iconics;
import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.iconics.typeface.ITypeface;

public final class IconParser {

    private static IconParser iconParser;

    final Context context;

    private IconParser(Context context) {
        this.context = context;
    }

    public static void initialize(Context context) {
        if(iconParser == null) iconParser = new IconParser(context);
    }

    public static IIcon getIcon(final String iconName) {
        if(iconParser != null) {
            ITypeface font = Iconics.findFont(iconParser.context, iconName.substring(0, 3));
            return font.getIcon(iconName.replace("-", "_"));
        }
        Log.e("IconParser", "You have to initialize the IconParser instance before calling it!");
        return null;
    }

}