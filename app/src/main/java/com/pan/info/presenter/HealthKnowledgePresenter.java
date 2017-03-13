package com.pan.info.presenter;

import android.support.annotation.NonNull;

import com.pan.info.base.BasePresenter;
import com.pan.info.ui.view.HealthKnowledgeView;

/**
 * Author : Pan
 * Date : 13/03/2017
 */

public abstract class HealthKnowledgePresenter
        extends BasePresenter<HealthKnowledgeView>{

    protected HealthKnowledgePresenter(@NonNull HealthKnowledgeView view) {
        super(view);
    }
}
