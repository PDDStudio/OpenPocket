package com.pddstudio.openpocket;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.pddstudio.pocketlibrary.OpenPocket;

import io.inject.InjectView;
import io.inject.Injector;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.toolbar) private Toolbar toolbar;
    @InjectView(R.id.tabLayout) private TabLayout tabLayout;
    @InjectView(R.id.viewPager) private ViewPager viewPager;

    private Drawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //bind the views
        Injector.inject(this);

        //initialize pocket library
        OpenPocket.init(this);

        //build the navigation drawer
        buildNavigationDrawer(savedInstanceState);
    }

    private void buildNavigationDrawer(Bundle savedInstance) {
        drawer = new DrawerBuilder(this)
                .withToolbar(toolbar)
                .withSavedInstance(savedInstance)
                .withActionBarDrawerToggleAnimated(true)
                .build();
    }

}
