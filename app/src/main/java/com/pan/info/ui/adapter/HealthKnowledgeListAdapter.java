package com.pan.info.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.pan.info.Constant;
import com.pan.info.R;
import com.pan.info.bean.HealthKnowledgeListBean;
import com.pan.info.util.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author : Pan
 * Date : 12/03/2017
 */

public class HealthKnowledgeListAdapter extends
        XRecyclerView.Adapter<HealthKnowledgeListAdapter.ViewHolder>{

    private Context mContext;

    private List<HealthKnowledgeListBean.TngouBean> mList;

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
        Picasso.with(mContext)
                .load(Constant.IMAGE_URL + mList.get(position).getImg())
                .tag(mContext)
                .placeholder(R.drawable.default_avatar)
                .error(R.drawable.default_avatar)
                .resizeDimen(R.dimen.adapter_item_avatar_width, R.dimen.adapter_item_avatar_height)
                .centerCrop()
                .into(holder.mAvatar);

        holder.mTitle.setText(mList.get(position).getTitle());
        holder.mKeyword.setText(mContext.getResources().getString(R.string.keyword) +
                mList.get(position).getKeywords());
        holder.mUpdateTime.setText(Utils.getTime(mList.get(position).getTime()));
        holder.mCollectCount.setText(mContext.getResources().getString(R.string.collect_count) +
                mList.get(position).getFcount());
        holder.mCommentCount.setText(mContext.getResources().getString(R.string.comment_count) +
                mList.get(position).getRcount());
        holder.mVisitCount.setText(mContext.getResources().getString(R.string.visit_count) +
                mList.get(position).getCount());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void refresh(List<HealthKnowledgeListBean.TngouBean> list) {
        this.mList = list;
        notifyDataSetChanged();
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
            ButterKnife.bind(this, itemView);
        }
    }
}
