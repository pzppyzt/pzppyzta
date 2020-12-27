package com.example.my12_17.vlayoutadapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.bumptech.glide.Glide;
import com.example.my12_17.R;
import com.example.my12_17.bean.HomeBean;
import com.example.my12_17.util.WindowUtils;

import java.util.List;

public class GridCategoryAdapter extends DelegateAdapter.Adapter<GridCategoryAdapter.ViewHolder> {
    private final FragmentActivity context;
    private final List<HomeBean.DataBean.CategoryListBean.GoodsListBean> goodsList;
    private final GridLayoutHelper gridLayoutHelper;

    public GridCategoryAdapter(FragmentActivity context, List<HomeBean.DataBean.CategoryListBean.GoodsListBean> goodsList, GridLayoutHelper gridLayoutHelper) {
        this.context = context;
        this.goodsList = goodsList;
        this.gridLayoutHelper = gridLayoutHelper;
    }


    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return gridLayoutHelper;
    }

    @NonNull
    @Override
    public GridCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.layout_category_item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull GridCategoryAdapter.ViewHolder holder, int position) {
        int screenWidth = WindowUtils.getScreenWidth(context);
        HomeBean.DataBean.CategoryListBean.GoodsListBean goodsListBean = goodsList.get(position);
        ViewGroup.LayoutParams layoutParams = holder.iv_category.getLayoutParams();
        layoutParams.width = screenWidth / 2 - 50;

        holder.tv_title.setText(goodsListBean.getName());
        holder.tv_price.setText("¥ " + goodsListBean.getRetail_price());
        Glide.with(context).load(goodsListBean.getList_pic_url()).into(holder.iv_category);
    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_title;
        private final TextView tv_price;
        private final ImageView iv_category;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_category_title_home);
            tv_price = itemView.findViewById(R.id.tv_category_price_home);
            iv_category = itemView.findViewById(R.id.iv_category_home);
        }
    }
}
