package com.pddstudio.pocketlibrary;
/*
 * This Class was created by Patrick J
 * on 07.03.16. For more Details and licensing information
 * have a look at the README.md
 */

import com.pddstudio.pocketlibrary.models.Profile;

import java.util.LinkedList;
import java.util.List;

import io.paperdb.Paper;

public class ProfileManager {

    private static final String PROFILE_LIST = "profileList";

    protected ProfileManager() {}

    public List<Profile> getProfileList() {
        return Paper.book().read(PROFILE_LIST, new LinkedList<Profile>());
    }

    public void addProfile(Profile profile) {
        Paper.book().write(PROFILE_LIST, Paper.book().read(PROFILE_LIST, new LinkedList<Profile>()).add(profile));
    }

    public void removeProfile(Profile profile) {
        List<Profile> profiles = Paper.book().read(PROFILE_LIST, new LinkedList<Profile>());
        for(int i = 0; i < profiles.size(); i++) {
            if(profile.getProfileName().equals(profiles.get(i).getProfileName())) profiles.remove(i);
        }
    }

}
