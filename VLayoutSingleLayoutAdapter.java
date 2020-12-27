package com.example.my12_17.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.example.my12_17.R;

public class VLayoutSingleLayoutAdapter extends DelegateAdapter.Adapter {
    private SingleLayoutHelper SingleLayoutHelper;

    public VLayoutSingleLayoutAdapter(com.alibaba.android.vlayout.layout.SingleLayoutHelper singleLayoutHelper) {
        SingleLayoutHelper = singleLayoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return SingleLayoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlelayout_item,parent,false);
        return new SingleLayoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    private class SingleLayoutViewHolder extends RecyclerView.ViewHolder {
        public SingleLayoutViewHolder(View view) {
            super(view);
        }
    }
}
