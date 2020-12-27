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

public class GridLayoutAdapter extends DelegateAdapter.Adapter<GridLayoutAdapter.GridLayoutViewHolder> {
    private GridLayoutHelper GridLayoutHelper;
    private Context context;
    private List<HomeBean.DataBean.BrandListBean> brandListBeans;

    public GridLayoutAdapter(GridLayoutHelper gridLayoutHelper, Context context, List<HomeBean.DataBean.BrandListBean> brandListBeans) {
        this.GridLayoutHelper = gridLayoutHelper;
        this.context = context;
        this.brandListBeans = brandListBeans;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return GridLayoutHelper;
    }

    @NonNull
    @Override
    public GridLayoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gridlayout_home_adapter, parent, false);
        return new GridLayoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridLayoutViewHolder holder, int position) {
        GridLayoutViewHolder gridLayoutViewHolder = (GridLayoutViewHolder) holder;
        Glide.with(context).load(brandListBeans.get(position).getNew_pic_url()).into(gridLayoutViewHolder.img);
        gridLayoutViewHolder.title.setText(brandListBeans.get(position).getName());
        gridLayoutViewHolder.title_price.setText(brandListBeans.get(position).getFloor_price() + "元起");
    }

    @Override
    public int getItemCount() {
        return brandListBeans.size();
    }


    public class GridLayoutViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView title_price;
        private ImageView img;

        public GridLayoutViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            title_price=itemView.findViewById(R.id.title_price);
            img=itemView.findViewById(R.id.img);
        }
    }
}