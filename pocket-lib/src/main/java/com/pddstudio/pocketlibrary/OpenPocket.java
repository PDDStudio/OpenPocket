package com.pddstudio.pocketlibrary;
/*
 * This Class was created by Patrick J
 * on 07.03.16. For more Details and licensing information
 * have a look at the README.md
 */

import android.content.Context;

import com.pddstudio.pocketlibrary.models.Profile;

import io.paperdb.Paper;

public final class OpenPocket {

    private static OpenPocket openPocket;

    private final Context context;
    private final ProfileManager profileManager;
    private final CategoryManager categoryManager;
    private final TransactionManager transactionManager;

    private OpenPocket(Context context) {
        Paper.init(context);
        this.context = context;
        this.profileManager = new ProfileManager();
        this.categoryManager = new CategoryManager();
        this.transactionManager = new TransactionManager();
    }

    public static void init(Context context) {
        openPocket = new OpenPocket(context);
    }

    public static OpenPocket get() {
        return openPocket;
    }

    public Context getContext() {
        return context;
    }

    public ProfileManager getProfileManager() {
        return profileManager;
    }

    public CategoryManager getCategoryManager() {
        return categoryManager;
    }

    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    public Profile getActiveProfile() {
        return profileManager.getActiveProfile();
    }

    public void setActiveProfile(Profile profile) {
        profileManager.setActiveProfile(profile);
    }

}
