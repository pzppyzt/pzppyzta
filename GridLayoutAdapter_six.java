package com.example.my12_17.vlayoutadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.bumptech.glide.Glide;
import com.example.my12_17.R;
import com.example.my12_17.bean.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

public class GridLayoutAdapter_six extends DelegateAdapter.Adapter<GridLayoutAdapter_six.GridLayoutViewHolder> {
    private GridLayoutHelper GridLayoutHelper;
    private Context context;
    private List<HomeBean.DataBean.BannerBean> bannerBeans;

    public GridLayoutAdapter_six(GridLayoutHelper gridLayoutHelper, Context context, List<HomeBean.DataBean.BannerBean> bannerBeans) {
        this.GridLayoutHelper = gridLayoutHelper;
        this.context = context;
        this.bannerBeans = bannerBeans;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return GridLayoutHelper;
    }

    @NonNull
    @Override
    public GridLayoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gridlayout_home_six_adapter, parent, false);
        return new GridLayoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridLayoutViewHolder holder, int position) {
        GridLayoutViewHolder gridLayoutViewHolder = (GridLayoutViewHolder) holder;
        gridLayoutViewHolder.banner.setImages(bannerBeans).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.DataBean.BannerBean bannerBean= (HomeBean.DataBean.BannerBean) path;
                Glide.with(context).load(bannerBean.getImage_url()).into(imageView);
            }
        }).start();
    }

    @Override
    public int getItemCount() {
        return 1 ;
    }


    public class GridLayoutViewHolder extends RecyclerView.ViewHolder {
        private Banner banner;
        public GridLayoutViewHolder(@NonNull View itemView) {
            super(itemView);
            banner=itemView.findViewById(R.id.banner);
        }
    }
}