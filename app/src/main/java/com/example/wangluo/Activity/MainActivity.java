package com.example.wangluo.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;

import com.example.wangluo.Adapter.ViewPagerAdapter;
import com.example.wangluo.R;
import com.example.wangluo.Fragment.RankFragment;
import com.example.wangluo.Fragment.ReferFragment;
import com.example.wangluo.Fragment.TrackFragment;
import com.example.wangluo.Utils.BottomNavigationViewHelper;

import org.litepal.LitePal;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RankFragment rankFragment;
    private TrackFragment trackFragment;
    private ReferFragment referFragment;
    private Fragment[] fragments;
    private int lastShowFragment = 0;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private ViewPager viewPager;
    private BottomNavigationView navigation;
    private MenuItem menuItem;
    private MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(3);
        initView();
    }

    private void initView() {
        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            BottomNavigationViewHelper.disableShiftMode(navigation);
        }
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RankFragment());
        adapter.addFragment(new TrackFragment());
        adapter.addFragment(new ReferFragment());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        //建立数据库

        //缓存3个页面，来解决点击“我的”回来，首页空白的问题，
        // 存在的问题，如果有的页面不需要缓存该如何自动刷新，可以利用eventbus传参来进行该页面的操作
        //viewpager.setOffscreenPageLimit(3);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override

            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {

                    case R.id.navigation_rank:

                        viewPager.setCurrentItem(0);
                       /* transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.viewpager, rankFragment);
                        transaction.commit();*/
                        return true;

                    case R.id.navigation_track:

                        viewPager.setCurrentItem(1);
                        /*transaction.replace(R.id.viewpager, trackFragment);
                        transaction.commit();*/
                        return true;

                    case R.id.navigation_reference:

                        viewPager.setCurrentItem(2);
                        /*transaction.replace(R.id.viewpager, referFragment);
                        transaction.commit();*/
                        return true;
                }

                return false;

            }

        });


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (menuItem != null) {

                    menuItem.setChecked(false);

                } else {

                    navigation.getMenu().getItem(0).setChecked(false);

                }

                menuItem = navigation.getMenu().getItem(position);

                menuItem.setChecked(true);

            }


            @Override

            public void onPageScrollStateChanged(int state) {

            }

        });


        //禁止ViewPager滑动

        viewPager.setOnTouchListener(new View.OnTouchListener() {

            @Override

            public boolean onTouch(View v, MotionEvent event) {

                return true;

            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                MainActivity.this.finish();
                break;
            case R.id.account:
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
        }
        return true;
    }

}
