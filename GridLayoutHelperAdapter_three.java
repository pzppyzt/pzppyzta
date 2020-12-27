package com.example.my12_17.vlayoutadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.bumptech.glide.Glide;
import com.example.my12_17.R;
import com.example.my12_17.bean.HomeBean;

import java.util.List;

public class GridLayoutHelperAdapter_three extends DelegateAdapter.Adapter<GridLayoutHelperAdapter_three.ViewHolder> {

    private Context context;
    private List<HomeBean.DataBean.ChannelBean> channelBean;
    private GridLayoutHelper GridLayoutHelper;

    public GridLayoutHelperAdapter_three(Context context, List<HomeBean.DataBean.ChannelBean> channelBean, GridLayoutHelper GridLayoutHelper) {
        this.context = context;
        this.channelBean = channelBean;
        this.GridLayoutHelper = GridLayoutHelper;
    }


    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return GridLayoutHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.column_home_adapter,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        
        Glide.with(context).load(channelBean.get(position).getIcon_url()).into(holder.img);
        holder.title.setText(channelBean.get(position).getName());
    }


    @Override
    public int getItemCount() {
        return channelBean.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            title=itemView.findViewById(R.id.title);
        }
    }
}
