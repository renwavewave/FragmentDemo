package com.wavewave.fragmentdemo;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.wavewave.fragmentdemo.fragment.ChatFragment;
import com.wavewave.fragmentdemo.fragment.HomeFragment;
import com.wavewave.fragmentdemo.fragment.MyFragment;
import com.wavewave.fragmentdemo.fragment.StudentFragment;
import com.wavewave.fragmentdemo.wedgit.MyViewPager;

import java.util.LinkedList;

/**
 * @author wavewave
 * @CreateDate: 2019/4/11 15:30 PM
 * @Description: ViewPager方式
 * @Version: 1.0
 */
public class ViewPagerActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private MyViewPager viewPagerVp;
    private BottomNavigationView mainBnv;
    private LinkedList<Fragment> fragmentList = new LinkedList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        fragmentList.add(HomeFragment.newInstance("", ""));
        fragmentList.add(ChatFragment.newInstance("", ""));
        fragmentList.add(StudentFragment.newInstance("", ""));
        fragmentList.add(MyFragment.newInstance("", ""));
        initView();
        mainBnv.setOnNavigationItemSelectedListener(this);
    }

    private void initView() {
        viewPagerVp = findViewById(R.id.view_pager_vp);
        mainBnv = findViewById(R.id.main_bnv);
        viewPagerVp.setOffscreenPageLimit(3);
        viewPagerVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragmentList.get(i);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_home_main:
                viewPagerVp.setCurrentItem(0);
                break;
            case R.id.menu_chat_main:
                viewPagerVp.setCurrentItem(1);
                break;
            case R.id.menu_student_main:
                viewPagerVp.setCurrentItem(2);
                break;
            case R.id.menu_my_main:
                viewPagerVp.setCurrentItem(3);
                break;
        }
        return true;
    }
}
