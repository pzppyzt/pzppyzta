package com.example.my12_17.vlayoutadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.example.my12_17.R;

public class GridLayoutHelperAdapter_five extends DelegateAdapter.Adapter {

    private Context context;
    private String title;
    private GridLayoutHelper GridLayoutHelper;

    public GridLayoutHelperAdapter_five(Context context, String title, GridLayoutHelper gridLayoutHelper) {
        this.context = context;
        this.title = title;
        GridLayoutHelper = gridLayoutHelper;
    }


    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return GridLayoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.singlelayout_home_adapter,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder= (ViewHolder) holder;
        viewHolder.title.setText(title);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        public ViewHolder(View view) {
            super(view);
            title=view.findViewById(R.id.title);
        }
    }
}
