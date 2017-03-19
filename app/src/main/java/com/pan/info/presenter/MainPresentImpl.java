package com.pan.info.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.pan.info.R;
import com.pan.info.ui.view.MainView;
import com.pan.info.ui.fragment.HealthKnowledgeHomeFragment;

/**
 * Author : Pan
 * Date : 2/26/17
 */

public class MainPresentImpl extends MainPresenter {

    private MainView mView;

    @Override
    public void attachView(@NonNull MainView view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        if (null != mView) {
            mView = null;
        }
    }

    @Override
    public void clickMenuItem(Context context, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.health_knowledge:
                mView.showFragment(R.id.container, HealthKnowledgeHomeFragment.newInstance());
                mView.setTitle(context.getResources().getString(R.string.health_knowledge_title));
                break;

            default:
                break;
        }
    }
}
