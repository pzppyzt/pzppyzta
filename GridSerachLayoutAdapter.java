package com.example.my12_17.vlayoutadapter;

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

public class GridSerachLayoutAdapter extends DelegateAdapter.Adapter<GridSerachLayoutAdapter.GridLayoutViewHolder> {
    private GridLayoutHelper GridLayoutHelper;
    private Context context;

    public GridSerachLayoutAdapter(GridLayoutHelper gridLayoutHelper, Context context) {
        this.GridLayoutHelper = gridLayoutHelper;
        this.context = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return GridLayoutHelper;
    }

    @NonNull
    @Override
    public GridLayoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gridlayout_home_serach_adapter, parent, false);
        return new GridLayoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridLayoutViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }


    public class GridLayoutViewHolder extends RecyclerView.ViewHolder {


        public GridLayoutViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}