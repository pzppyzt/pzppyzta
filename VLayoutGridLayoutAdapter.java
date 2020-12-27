package com.example.my12_17.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.example.my12_17.R;

public class VLayoutGridLayoutAdapter extends DelegateAdapter.Adapter {
    private GridLayoutHelper GridLayoutHelper;
    private Context context;

    public VLayoutGridLayoutAdapter(com.alibaba.android.vlayout.layout.GridLayoutHelper gridLayoutHelper, Context context) {
        GridLayoutHelper = gridLayoutHelper;
        this.context = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return GridLayoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.gridlayout_item,parent,false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    private class GridViewHolder extends RecyclerView.ViewHolder {
        public GridViewHolder(View view) {
            super(view);
        }
    }
}
