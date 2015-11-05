package com.fefeyo.parsefavoriter;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTabLayout = (TabLayout) findViewById(R.id.tab);
        mTabLayout.addTab(mTabLayout.newTab().setText("Reader"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Favorite"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Settings"));
        mTabLayout.setOnTabSelectedListener(this);
        final Handler handle = new Handler();
//        final ProgressDialog dialog = new ProgressDialog(this);
//        dialog.setTitle("読込中");
//        dialog.show();
//        handle.post(new Runnable() {
//            @Override
//            public void run() {
//                if (null != ParseUser.getCurrentUser()) {
        getFragmentManager().beginTransaction().replace(R.id.container, new RssFragment()).commit();
//                    dialog.dismiss();
//                }else{
//                    handle.postDelayed(this, 3000);
//                }
//            }
//        });
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Fragment fragment = null;
        switch (tab.getPosition()) {
            case 0:
                fragment = new RssFragment();
                break;
            case 1:
                fragment = new FavoriteFragment();
                break;
            case 2:
                fragment = new SettingsFragment();
                break;
        }
        getFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }
}