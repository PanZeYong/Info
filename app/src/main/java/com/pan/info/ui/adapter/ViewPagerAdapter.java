package com.pan.info.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pan.info.bean.HealthKnowledgeCategoryBean;
import com.pan.info.ui.fragment.HealthKnowledgeListFragment;

import java.util.List;

/**
 * Author : Pan
 * Date : 09/03/2017
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<HealthKnowledgeListFragment> mList;

    private List<HealthKnowledgeCategoryBean.TngouBean> mCategories;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void refresh(List<HealthKnowledgeListFragment> list,
                        List<HealthKnowledgeCategoryBean.TngouBean> categories) {
        this.mList = list;
        this.mCategories = categories;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return null == mList ? 0 : mList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mCategories.get(position).getTitle();
    }
}
