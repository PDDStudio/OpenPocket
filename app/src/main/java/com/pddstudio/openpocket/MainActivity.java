package com.pddstudio.openpocket;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.pddstudio.openpocket.adapters.ViewPagerAdapter;
import com.pddstudio.pocketlibrary.OpenPocket;
import com.pddstudio.pocketlibrary.models.Profile;

import io.inject.InjectView;
import io.inject.Injector;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.toolbar) private Toolbar toolbar;
    @InjectView(R.id.tabLayout) private TabLayout tabLayout;
    @InjectView(R.id.viewPager) private ViewPager viewPager;

    private Drawer drawer;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //bind the views
        Injector.inject(this);
        setSupportActionBar(toolbar);

        //initialize pocket library
        OpenPocket.init(this);

        //build the navigation drawer
        buildNavigationDrawer(savedInstanceState);

        //setup the view pager
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void buildNavigationDrawer(Bundle savedInstance) {

        AccountHeader accountHeader = new AccountHeaderBuilder().withActivity(this).withHeaderBackground(R.color.colorPrimary).build();

        for(Profile profile : OpenPocket.get().getProfileManager().getProfileList()) {
            ProfileDrawerItem profileDrawerItem = new ProfileDrawerItem().withName(profile.getProfileName()).withEmail(profile.getProfileDescription());
            accountHeader.addProfiles(profileDrawerItem);
        }

        drawer = new DrawerBuilder(this)
                .withToolbar(toolbar)
                .withSavedInstance(savedInstance)
                .withActionBarDrawerToggleAnimated(true)
                .withAccountHeader(accountHeader)
                .build();
    }

}
