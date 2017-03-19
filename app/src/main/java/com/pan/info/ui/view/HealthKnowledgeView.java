package com.pan.info.ui.view;

import com.pan.info.base.BaseView;
import com.pan.info.bean.HealthKnowledgeCategoryBean;

import java.util.List;

/**
 * Author : Pan
 * Date : 13/03/2017
 */

public interface HealthKnowledgeView extends BaseView {
    void getCategories(List<HealthKnowledgeCategoryBean.TngouBean> list);

    void showErrorMessage(String message);
}
