package com.pan.info.presenter;

import android.content.Context;

import com.pan.info.base.BasePresenter;
import com.pan.info.ui.view.HealthKnowledgeListView;
import com.pan.info.ui.view.HealthKnowledgeView;


/**
 * Author : Pan
 * Date : 13/03/2017
 */

public abstract class HealthKnowledgeListPresenter
        extends BasePresenter<HealthKnowledgeListView>{

    public abstract void getList(Context context, int categoryId, int page, int rows);
}
