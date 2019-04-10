package com.wavewave.fragmentdemo;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.wavewave.fragmentdemo.fragment.ChatFragment;
import com.wavewave.fragmentdemo.fragment.HomeFragment;
import com.wavewave.fragmentdemo.fragment.MyFragment;
import com.wavewave.fragmentdemo.fragment.StudentFragment;

/**
 * @author wavewave
 * @CreateDate: 2019/4/10 18:20 PM
 * @Description:
 * @Version: 1.0
 */
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private LinearLayout mainLl;
    private BottomNavigationView mainBnv;

    private HomeFragment homeFragment;
    private ChatFragment chatFragment;
    private StudentFragment studentFragment;
    private MyFragment myFragment;

    //选择哪个fragment
    private static final String SELECT_FRAGMENT = "selectFragment";
    private final String fragmentTag[] = {"main_home", "main_chat", "main_student", "main_my"};

    //记录当前选中的 fragment 索引用于异常销毁 恢复
    private int selectPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {//处理activity 异常销毁恢复 fragment
            selectPosition = savedInstanceState.getInt(SELECT_FRAGMENT, -1);
            saveFragment(selectPosition);
        } else {
            showFragment(homeFragment, fragmentTag[0]);
        }
        initView();
        mainBnv.setOnNavigationItemSelectedListener(this);

    }

    private void initView() {
        mainBnv = findViewById(R.id.main_bnv);
        mainLl = findViewById(R.id.main_ll);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECT_FRAGMENT, selectPosition);
    }

    /**
     * 用于恢复 activity 异常销毁的fragment
     *
     * @param anInt
     */
    private void saveFragment(int anInt) {

        homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(fragmentTag[0]);
        chatFragment = (ChatFragment) getSupportFragmentManager().findFragmentByTag(fragmentTag[1]);
        studentFragment = (StudentFragment) getSupportFragmentManager().findFragmentByTag(fragmentTag[2]);
        myFragment = (MyFragment) getSupportFragmentManager().findFragmentByTag(fragmentTag[3]);
        switch (anInt) {
            case 0:
            default:
                showFragment(homeFragment, fragmentTag[0]);
                break;
            case 1:
                showFragment(chatFragment, fragmentTag[1]);
                break;
            case 2:
                showFragment(studentFragment, fragmentTag[2]);
                break;
            case 3:
                showFragment(myFragment, fragmentTag[3]);
                break;
        }
    }

    /**
     * 隐藏所有的fragment
     */
    private void hideFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (homeFragment != null) fragmentTransaction.hide(homeFragment);
        if (chatFragment != null) fragmentTransaction.hide(chatFragment);
        if (studentFragment != null) fragmentTransaction.hide(studentFragment);
        if (myFragment != null) fragmentTransaction.hide(myFragment);
        fragmentTransaction.commit();
    }

    /**
     * 用于切换 add 或者show
     *
     * @param fragment
     * @param tag
     */
    private void showFragment(Fragment fragment, String tag) {
        if (TextUtils.isEmpty(tag)) return;
        hideFragment();
        if (fragment == null) {
            if (tag.equals(fragmentTag[1])) {
                chatFragment = ChatFragment.newInstance("", "");
                getSupportFragmentManager().beginTransaction().add(R.id.main_ll, chatFragment, fragmentTag[1]).commit();
            } else if (tag.equals(fragmentTag[2])) {
                studentFragment = StudentFragment.newInstance("", "");
                getSupportFragmentManager().beginTransaction().add(R.id.main_ll, studentFragment, fragmentTag[2]).commit();
            } else if (tag.equals(fragmentTag[3])) {
                myFragment = MyFragment.newInstance("", "");
                getSupportFragmentManager().beginTransaction().add(R.id.main_ll, myFragment, fragmentTag[3]).commit();
            } else {
                homeFragment = HomeFragment.newInstance("", "");
                getSupportFragmentManager().beginTransaction().add(R.id.main_ll, homeFragment, fragmentTag[0]).commit();
            }
        } else {
            getSupportFragmentManager().beginTransaction().show(fragment).commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_home_main:
                selectPosition = 0;
                showFragment(homeFragment, fragmentTag[0]);
                break;
            case R.id.menu_chat_main:
                selectPosition = 1;

                showFragment(chatFragment, fragmentTag[1]);
                break;
            case R.id.menu_student_main:
                selectPosition = 2;

                showFragment(studentFragment, fragmentTag[2]);
                break;
            case R.id.menu_my_main:
                selectPosition = 3;

                showFragment(myFragment, fragmentTag[3]);
                break;
        }
        return true;
    }
}
