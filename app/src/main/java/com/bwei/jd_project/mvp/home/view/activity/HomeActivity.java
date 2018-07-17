package com.bwei.jd_project.mvp.home.view.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.bwei.jd_project.R;
import com.bwei.jd_project.mvp.classify.view.fragment.ClassifyFragment;
import com.bwei.jd_project.mvp.home.view.adapter.MyFragmentAdapter;
import com.bwei.jd_project.mvp.home.view.fragment.HomeFragment;
import com.bwei.jd_project.mvp.myinfo.view.fragment.MyInfoFragment;
import com.bwei.jd_project.mvp.shoppingcar.view.fragment.ShoppingCarFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;
import com.roughike.bottombar.TabSelectionInterceptor;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private android.support.v4.view.ViewPager mainViewPager;
    private com.roughike.bottombar.BottomBar bottomBar;

    private List<Fragment> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        initViews();


        initDatas();
    }

    private void initDatas() {

        list.add(new HomeFragment());
        list.add(new ClassifyFragment());
        list.add(new ShoppingCarFragment());
        list.add(new MyInfoFragment());

        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), list);

        mainViewPager.setAdapter(adapter);

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {

                switch (tabId) {
                    case R.id.tab_recents:

                        mainViewPager.setCurrentItem(0);

                        break;

                    case R.id.tab_favorites:

                        mainViewPager.setCurrentItem(1);

                        break;

                    case R.id.tab_nearby:

                        mainViewPager.setCurrentItem(2);

                        break;


                    case R.id.tab_friends:

                        mainViewPager.setCurrentItem(3);

                        break;

                }
            }
        });

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {

                switch (tabId) {

                    case R.id.tab_recents:

                        mainViewPager.setCurrentItem(0);

                        break;

                    case R.id.tab_favorites:

                        mainViewPager.setCurrentItem(1);

                        break;

                    case R.id.tab_nearby:


                        mainViewPager.setCurrentItem(2);

                        break;


                    case R.id.tab_friends:

                        mainViewPager.setCurrentItem(3);

                        break;

                }

            }
        });

        mainViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position){

                    case 0:

                        bottomBar.selectTabWithId(R.id.tab_recents);

                        break;

                    case 1:

                        bottomBar.selectTabWithId(R.id.tab_favorites);


                        break;

                    case 2:

                        bottomBar.selectTabWithId(R.id.tab_nearby);


                        break;

                    case 3:

                        bottomBar.selectTabWithId(R.id.tab_friends);

                        break;

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void initViews() {

        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        mainViewPager = (ViewPager) findViewById(R.id.mainViewPager);

    }
}
