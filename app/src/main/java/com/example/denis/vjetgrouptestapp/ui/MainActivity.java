package com.example.denis.vjetgrouptestapp.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.denis.vjetgrouptestapp.R;
import com.example.denis.vjetgrouptestapp.utils.Utils;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        mTabLayout = findViewById(R.id.tabLayout);
        setSupportActionBar(toolbar);

        MainFragment homeFragment = (MainFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);
        if (homeFragment == null) {
            homeFragment = MainFragment.newInstance();
            Utils.replaceFragment(getSupportFragmentManager(),
                    homeFragment, R.id.contentFrame);
        }

        new MainPresenter(homeFragment);
    }



    public void setupToolbarWithViewPager(ViewPager viewPager) {
        mTabLayout.setupWithViewPager(viewPager);
    }
}
