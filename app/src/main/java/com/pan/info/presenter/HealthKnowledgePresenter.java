package com.pan.info.presenter;

import android.content.Context;

import com.pan.info.base.BasePresenter;
import com.pan.info.ui.view.HealthKnowledgeView;


/**
 * Author : Pan
 * Date : 13/03/2017
 */

public abstract class HealthKnowledgePresenter
        extends BasePresenter<HealthKnowledgeView>{

    public abstract void getCategories(Context context);
}
