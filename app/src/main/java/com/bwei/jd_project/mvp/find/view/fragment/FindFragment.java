package com.bwei.jd_project.mvp.find.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwei.jd_project.R;
import com.bwei.jd_project.base.BaseFragment;
import com.bwei.jd_project.mvp.find.presenter.FindPresenter;
import com.bwei.jd_project.mvp.find.view.adapter.FindFragmentPagerAdapter;
import com.bwei.jd_project.mvp.find.view.findfragment.BoonFragment;
import com.bwei.jd_project.mvp.find.view.findfragment.MenuFragment;
import com.bwei.jd_project.mvp.find.view.findfragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;

public class FindFragment extends BaseFragment<FindPresenter>{

    private android.support.design.widget.TabLayout findTabLayout;

    private android.support.v4.view.ViewPager findViewPager;

    private List<Fragment> list = new ArrayList<>();

    protected void initDatas() {


    }

    protected void initViews() {

        findViewPager = view.findViewById(R.id.findViewPager);

        findTabLayout = view.findViewById(R.id.findTabLayout);

        addTabLayout();

    }

    @Override
    protected int ByLayout() {
        return R.layout.find_fragment_layout;
    }

    private void addTabLayout() {

        list.clear();

        BoonFragment boonFragment = BoonFragment.create("福利");
        NewsFragment newsFragment = NewsFragment.create("新闻");
        MenuFragment menuFragment = MenuFragment.create("菜谱");

        list.add(boonFragment);
        list.add(newsFragment);
        list.add(menuFragment);

        FindFragmentPagerAdapter findFragmentPagerAdapter = new FindFragmentPagerAdapter(getChildFragmentManager(),list);

        findViewPager.setAdapter(findFragmentPagerAdapter);

        findTabLayout.setSelectedTabIndicatorColor(Color.RED);

        TabLayout.Tab tab1 = findTabLayout.newTab();

        tab1.setText("福利");

        TabLayout.Tab tab2 = findTabLayout.newTab();

        tab2.setText("新闻");

        TabLayout.Tab tab3 = findTabLayout.newTab();

        tab3.setText("菜谱");

        findTabLayout.addTab(tab1);
        findTabLayout.addTab(tab2);
        findTabLayout.addTab(tab3);

        //将viewpager关联到tablayout
        findTabLayout.setupWithViewPager(findViewPager);
        //设置可以滑动
        findTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

    }

}
