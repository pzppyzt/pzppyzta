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

public class GridLayoutAdapter_four extends DelegateAdapter.Adapter<GridLayoutAdapter_four.GridLayoutViewHolder> {
    private GridLayoutHelper GridLayoutHelper;
    private Context context;
    private List<HomeBean.DataBean.HotGoodsListBean> hotGoodsListBeans;

    public GridLayoutAdapter_four(GridLayoutHelper gridLayoutHelper, Context context, List<HomeBean.DataBean.HotGoodsListBean> hotGoodsListBeans) {
        this.GridLayoutHelper = gridLayoutHelper;
        this.context = context;
        this.hotGoodsListBeans = hotGoodsListBeans;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return GridLayoutHelper;
    }

    @NonNull
    @Override
    public GridLayoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gridlayout_home_four_adapter, parent, false);
        return new GridLayoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridLayoutViewHolder holder, int position) {
        Glide.with(context).load(hotGoodsListBeans.get(position).getList_pic_url()).into(holder.img);
        holder.title.setText(hotGoodsListBeans.get(position).getName());
        holder.title_two.setText(hotGoodsListBeans.get(position).getGoods_brief());
        holder.title_price.setText("¥"+hotGoodsListBeans.get(position).getRetail_price());
    }

    @Override
    public int getItemCount() {
        return hotGoodsListBeans.size();
    }


    public class GridLayoutViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView title_two;
        private TextView title_price;
        private ImageView img;

        public GridLayoutViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            title_two=itemView.findViewById(R.id.title_two);
            title_price=itemView.findViewById(R.id.title_price);
            img=itemView.findViewById(R.id.img);
        }
    }
}