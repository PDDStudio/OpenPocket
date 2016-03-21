package com.pddstudio.openpocket.adapters;
/*
 * This Class was created by Patrick J
 * on 07.03.16. For more Details and licensing information
 * have a look at the README.md
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.pddstudio.openpocket.fragments.OverviewFragment;
import com.pddstudio.pocketlibrary.enums.Month;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final OverviewFragment[] overviewFragments;
    private final String[] fragmentTitles;

    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.overviewFragments = new OverviewFragment[Month.values().length];
        this.fragmentTitles = new String[Month.values().length];
        for(int i = 0; i < Month.values().length; i++) {
            this.overviewFragments[i] = OverviewFragment.newInstance(Month.values()[i]);
            this.fragmentTitles[i] = Month.values()[i].getMonthName();
        }
    }

    @Override
    public Fragment getItem(int position) {
        return overviewFragments[position];
    }

    @Override
    public int getCount() {
        return overviewFragments.length;
    }

    public void reloadItemData() {
        for(OverviewFragment overviewFragment : overviewFragments) {
            overviewFragment.reloadItems();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = fragmentTitles[position];
        Log.d("ViewPagerAdapter", "Position [" + position + "] Title: " + title);
        return title;
    }
}
