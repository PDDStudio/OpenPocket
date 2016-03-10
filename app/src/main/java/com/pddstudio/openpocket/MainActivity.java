package com.pddstudio.openpocket;

import android.support.design.widget.TabLayout;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mikepenz.iconics.context.IconicsLayoutInflater;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.pddstudio.openpocket.adapters.ViewPagerAdapter;
import com.pddstudio.openpocket.views.CoordinatorBalanceView;
import com.pddstudio.pocketlibrary.OpenPocket;
import com.pddstudio.pocketlibrary.models.Profile;
import com.pddstudio.pocketutils.DateUtils;

import io.inject.InjectView;
import io.inject.Injector;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    @InjectView(R.id.balanceView) private CoordinatorBalanceView coordinatorBalanceView;
    @InjectView(R.id.tabLayout) private TabLayout tabLayout;
    @InjectView(R.id.viewPager) private ViewPager viewPager;

    private Drawer drawer;
    private AccountHeader accountHeader;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //bind the views
        Injector.inject(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        //initialize pocket library
        OpenPocket.init(this);

        //build the navigation drawer
        buildNavigationDrawer(savedInstanceState);

        //setup the view pager
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                toolbar.setTitle(getString(R.string.toolbar_title, viewPagerAdapter.getPageTitle(position).toString() + " " + DateUtils.getCurrentYear()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
        tabLayout.setupWithViewPager(viewPager);

        //switch to the current month
        viewPager.setCurrentItem(DateUtils.getCurrentMonth(), true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //save the drawer values to restore it later if needed
        outState = drawer.saveInstanceState(outState);
        outState = accountHeader.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else {
            this.finish();
            System.exit(0);
        }
    }

    private void buildNavigationDrawer(Bundle savedInstance) {

        accountHeader = new AccountHeaderBuilder().withActivity(this).withHeaderBackground(R.color.colorPrimary).build();

        for(Profile profile : OpenPocket.get().getProfileManager().getProfileList()) {
            ProfileDrawerItem profileDrawerItem = new ProfileDrawerItem().withName(profile.getProfileName()).withEmail(profile.getProfileDescription());
            accountHeader.addProfiles(profileDrawerItem);
        }

        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withSavedInstance(savedInstance)
                .withActionBarDrawerToggleAnimated(true)
                .withAccountHeader(accountHeader)
                .withFullscreen(false)
                .withTranslucentStatusBar(true)
                .withTranslucentNavigationBarProgrammatically(true)
                .build();
    }

}
