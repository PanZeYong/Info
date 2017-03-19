package com.pan.info;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import com.pan.info.base.BaseActivity;
import com.pan.info.presenter.MainPresentImpl;
import com.pan.info.presenter.MainPresenter;
import com.pan.info.ui.view.MainView;
import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.container)
    FrameLayout mContent;
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawer)
    DrawerLayout mDrawer;

    private ActionBarDrawerToggle mActionBarDrawerToggle;

    @Override
    protected int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        mPresenter.attachView(this);
        initToolbar();
        initActionBarDrawerToggle();
    }

    @Override
    protected void registerListener() {
        if (null != mDrawer) {
            mDrawer.addDrawerListener(mActionBarDrawerToggle);
        }
        mNavigationView.setNavigationItemSelectedListener(listener);
    }

    @Override
    protected void release() {
        mPresenter.detachView();
    }

    @Override
    protected MainPresenter bindPresenter() {
        return new MainPresentImpl();
    }

    @Override
    public void showFragment(int containerViewId, Fragment fragment) {
        replace(containerViewId, fragment);
    }

    @Override
    public void setTitle(String title) {
        mToolbar.setTitle(title);
    }

    private void initToolbar() {
        mToolbar.setTitle(getResources().getString(R.string.app_name));
    }

    private void initActionBarDrawerToggle() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar,
                R.string.app_name, R.string.app_name);
        mActionBarDrawerToggle.syncState();
    }

    private NavigationView.OnNavigationItemSelectedListener listener =
            new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            mPresenter.clickMenuItem(mContext,item);
            mDrawer.closeDrawers();
            return false;
        }
    };
}
