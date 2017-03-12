package com.pan.info.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.pan.info.R;
import com.pan.info.ui.view.MainView;
import com.pan.info.ui.fragment.HealthKnowledgeHomeFragment;

import timber.log.Timber;

/**
 * Author : Pan
 * Date : 2/26/17
 */

public class MainPresentImpl extends MainPresenter {

    public MainPresentImpl(@NonNull MainView view) {
        super(view);
    }


    @Override
    public void clickMenuItem(Context context, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.health_knowledge:
                Timber.d("Click Health Knowledge");
                mView.showFragment(R.id.container, HealthKnowledgeHomeFragment.newInstance());
                mView.setTitle(context.getResources().getString(R.string.health_knowledge_title));
                break;

            default:
                break;
        }
    }
}
