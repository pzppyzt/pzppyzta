package com.example.onlineretailerspogect;

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

import java.util.ArrayList;
import java.util.List;

class MainGridAdapter extends DelegateAdapter.Adapter {
    private Context context;
    private GridLayoutHelper gridLayoutHelper;

    private List<String> list=new ArrayList();

    public void Additem(List<String> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    public MainGridAdapter(Context context, GridLayoutHelper gridLayoutHelper) {
        this.context = context;
        this.gridLayoutHelper = gridLayoutHelper;
    }


    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return gridLayoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.grid_itm_layout2, parent, false);

        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String s = list.get(position);
        Vh vh= (Vh) holder;
        Glide.with(context).load(s).into(vh.lv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class Vh extends RecyclerView.ViewHolder{
        private ImageView lv;
        public Vh(@NonNull View itemView) {
            super(itemView);
            itemView.findViewById(R.id.grid_my_image);
        }
    }
}
