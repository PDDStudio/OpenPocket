package com.pddstudio.pocketlibrary;
/*
 * This Class was created by Patrick J
 * on 07.03.16. For more Details and licensing information
 * have a look at the README.md
 */

import android.content.Context;

public final class OpenPocket {

    private static OpenPocket openPocket;

    private final Context context;

    private OpenPocket(Context context) {
        this.context = context;
    }

    public static void init(Context context) {
        openPocket = new OpenPocket(context);
    }

    public static OpenPocket get() {
        return openPocket;
    }

}
