package com.example.onlineretailerspogect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.example.onlineretailerspogect.bean.shouBean;
import com.youth.banner.Banner;

import java.util.List;

class MainSingleAdapter extends DelegateAdapter.Adapter {
    private SingleLayoutHelper singleLayoutHelper;
    private List<shouBean>

    public MainSingleAdapter(SingleLayoutHelper singleLayoutHelper) {
        this.singleLayoutHelper=singleLayoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return singleLayoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sing_itm_layout3, parent, false);

        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public static class Vh extends RecyclerView.ViewHolder{

        private Banner banner;
        public Vh(@NonNull View itemView) {
            super(itemView);
          banner=  itemView.findViewById(R.id.banner);
        }
    }
}
