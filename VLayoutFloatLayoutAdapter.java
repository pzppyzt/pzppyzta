package com.example.my12_17.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.FloatLayoutHelper;
import com.example.my12_17.R;

public class VLayoutFloatLayoutAdapter extends DelegateAdapter.Adapter {
    private FloatLayoutHelper FloatLayoutHelper;

    public VLayoutFloatLayoutAdapter(com.alibaba.android.vlayout.layout.FloatLayoutHelper floatLayoutHelper) {
        FloatLayoutHelper = floatLayoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return FloatLayoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.floatlayout_item,parent,false);
        return new FloatLayoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    private class FloatLayoutViewHolder extends RecyclerView.ViewHolder {
        public FloatLayoutViewHolder(View view) {
            super(view);
        }
    }
}
