package com.pan.info.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.pan.info.R;

import butterknife.BindView;

/**
 * Author : Pan
 * Date : 12/03/2017
 */

public class HealthKnowledgeListAdapter extends
        XRecyclerView.Adapter<HealthKnowledgeListAdapter.ViewHolder>{

    private Context mContext;

    public HealthKnowledgeListAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.adapter_health_knowledge_home_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public void refresh() {

    }

    class ViewHolder extends XRecyclerView.ViewHolder {

        @BindView(R.id.iv_avatar)
        ImageView mAvatar;
        @BindView(R.id.tv_title)
        TextView mTitle;
        @BindView(R.id.tv_keyword)
        TextView mKeyword;
        @BindView(R.id.iv_update_time)
        TextView mUpdateTime;
        @BindView(R.id.iv_comment_count)
        TextView mCommentCount;
        @BindView(R.id.iv_collect_count)
        TextView mCollectCount;
        @BindView(R.id.iv_visit_count)
        TextView mVisitCount;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
