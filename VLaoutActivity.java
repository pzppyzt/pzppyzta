package com.example.my12_17.view;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.FloatLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.example.my12_17.R;
import com.example.my12_17.adapter.VLayoutFloatLayoutAdapter;
import com.example.my12_17.adapter.VLayoutGridLayoutAdapter;
import com.example.my12_17.adapter.VLayoutSingleLayoutAdapter;

public class VLaoutActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v_laout);
        recyclerView = findViewById(R.id.recycler);

        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        // 创建VirtualLayoutManager对象
        // 同时内部会创建一个LayoutHelperFinder对象，用来后续的LayoutHelper查找

        recyclerView.setLayoutManager(layoutManager);
        // 将VirtualLayoutManager绑定到recyclerView
        // 设置组件复用回收池
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);

        /**
         设置Grid布局
         */
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(5);
        // 在构造函数设置每行的网格个数

        // 公共属性
        gridLayoutHelper.setItemCount(6);// 设置布局里Item个数
        gridLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        gridLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        gridLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        gridLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // gridLayoutHelper特有属性（下面会详细说明）
        gridLayoutHelper.setWeights(new float[]{RecyclerView.LayoutParams.WRAP_CONTENT,RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT,RecyclerView.LayoutParams.WRAP_CONTENT,RecyclerView.LayoutParams.WRAP_CONTENT});//设置每行中 每个网格宽度 占 每行总宽度 的比例
        gridLayoutHelper.setVGap(20);// 控制子元素之间的垂直间距
        gridLayoutHelper.setHGap(20);// 控制子元素之间的水平间距
        gridLayoutHelper.setAutoExpand(false);//是否自动填充空白区域
        gridLayoutHelper.setSpanCount(5);// 设置每行多少个网格

        /**
         设置通栏布局
         */

        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();

        // 公共属性
        singleLayoutHelper.setItemCount(1);// 设置布局里Item个数
        singleLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        singleLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        singleLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        singleLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

/**
 设置浮动布局
 */
        FloatLayoutHelper floatLayoutHelper = new FloatLayoutHelper();
        // 创建FloatLayoutHelper对象

        // 公共属性
        floatLayoutHelper.setItemCount(1);// 设置布局里Item个数

        floatLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        floatLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        floatLayoutHelper.setBgColor(Color.GRAY);
        floatLayoutHelper.setAspectRatio(6);

        VLayoutFloatLayoutAdapter vLayoutFloatLayoutAdapter = new VLayoutFloatLayoutAdapter(floatLayoutHelper);
        VLayoutSingleLayoutAdapter vLayoutSingleLayoutAdapter = new VLayoutSingleLayoutAdapter(singleLayoutHelper);
        VLayoutGridLayoutAdapter vLayoutGridLayoutAdapter = new VLayoutGridLayoutAdapter(gridLayoutHelper, this);


        DelegateAdapter adapter = new DelegateAdapter(layoutManager, true);

        adapter.addAdapter(vLayoutGridLayoutAdapter);
        adapter.addAdapter(vLayoutSingleLayoutAdapter);
        adapter.addAdapter(vLayoutFloatLayoutAdapter);
        recyclerView.setAdapter(adapter);
    }

}