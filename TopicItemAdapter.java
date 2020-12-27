package com.example.my12_17.vlayoutadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.my12_17.R;
import com.example.my12_17.bean.HomeBean;

import java.util.List;

public class TopicItemAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<HomeBean.DataBean.TopicListBean> list;

    public TopicItemAdapter(Context mContext, List<HomeBean.DataBean.TopicListBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(mContext).inflate(R.layout.layout_topic_item,parent,false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HomeBean.DataBean.TopicListBean topicListBean = list.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        Glide.with(mContext).load(topicListBean.getItem_pic_url()).into(viewHolder.iv_topic);
        viewHolder.tv_title.setText(topicListBean.getTitle());
        viewHolder.tv_intro.setText(topicListBean.getSubtitle());
        viewHolder.tv_price.setText("¥  "+topicListBean.getPrice_info()+" 元起");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_title;
        private final TextView tv_price;
        private final TextView tv_intro;
        private final ImageView iv_topic;

        public ViewHolder(View root) {
            super(root);
            tv_title = root.findViewById(R.id.tv_topictitle_home);
            tv_price = root.findViewById(R.id.tv_topicprice_home);
            tv_intro = root.findViewById(R.id.tv_topicintro_home);
            iv_topic = root.findViewById(R.id.iv_topic_home);
        }
    }
}
