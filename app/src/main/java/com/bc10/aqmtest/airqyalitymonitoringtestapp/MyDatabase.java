package com.bc10.aqmtest.airqyalitymonitoringtestapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class MyDatabase extends AppCompatActivity {


    MainActivity ins = MainActivity.instance();

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle mDrawerToggle;
    TabLayout tabs;
    ViewPager viewpager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_database);
        toolbar = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Air Quality Monitoring");
        initNavigationDrawer();
        viewpager = (ViewPager)findViewById(R.id.viewpager);
        setupViewPager(viewpager);

        tabs = (TabLayout)findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewpager);
    }



    public void initNavigationDrawer(){
        NavigationView navigationview = (NavigationView)findViewById(R.id.navigation_view);
        final Intent in = new Intent(this, MainActivity.class);
        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.home:
                        startActivity(in);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.knowdata:
                        ins.sendsms();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.viewdb:
                        drawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }



    private void setupViewPager(ViewPager viewpager){
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new fragment_bargraph(), "Bar Graphs");
        adapter.addFragment(new fragment_linegraph(), "Line Graphs");
        adapter.addFragment(new fragment_values(), "Values");
        viewpager.setAdapter(adapter);
    }

}
