package com.example.my12_17.view.fragment;

import android.graphics.Color;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.example.mvplibrary.api.base.BaseFragment;
import com.example.my12_17.R;
import com.example.my12_17.bean.HomeBean;
import com.example.my12_17.interfaces.MainInterface;
import com.example.my12_17.presenter.MainPresenter;
import com.example.my12_17.util.HorizontalAdapter;
import com.example.my12_17.vlayoutadapter.GridCategoryAdapter;
import com.example.my12_17.vlayoutadapter.GridLayoutAdapter;
import com.example.my12_17.vlayoutadapter.GridLayoutAdapter_four;
import com.example.my12_17.vlayoutadapter.GridLayoutAdapter_six;
import com.example.my12_17.vlayoutadapter.GridLayoutAdapter_two;
import com.example.my12_17.vlayoutadapter.GridLayoutHelperAdapter_five;
import com.example.my12_17.vlayoutadapter.GridLayoutHelperAdapter_three;
import com.example.my12_17.vlayoutadapter.GridSerachLayoutAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<MainPresenter> implements MainInterface.View {
    private RecyclerView recyclerView;
    private List<HomeBean.DataBean.ChannelBean> channelBeans;
    private DelegateAdapter adapter;
    private List<HomeBean.DataBean.BrandListBean> brandListBeans;
    private GridLayoutAdapter gridLayoutAdapter;
    private List<HomeBean.DataBean.NewGoodsListBean> newGoodsListBeans;
    private GridLayoutAdapter_two gridLayoutAdapter_two;
    private GridLayoutHelperAdapter_three gridLayoutHelperAdapter_three;
    private List<HomeBean.DataBean.HotGoodsListBean> hotGoodsListBeans;
    private GridLayoutAdapter_four gridLayoutAdapter_four;
    private List<HomeBean.DataBean.BannerBean> bannerBeans;
    private GridLayoutAdapter_six gridLayoutAdapter_six;
    private List<HomeBean.DataBean.TopicListBean> topicList;
    private HorizontalAdapter horizontalAdapter;
    private VirtualLayoutManager layoutManager;
    private List<HomeBean.DataBean.CategoryListBean.GoodsListBean> categoryListBeans;
    private List<HomeBean.DataBean.CategoryListBean.GoodsListBean> goodsList1;

    @Override
    protected void initData() {
        presenter.getHome();
    }

    @Override
    protected void initView(View view) {
        recyclerView = view.findViewById(R.id.recycler);


        layoutManager = new VirtualLayoutManager(getActivity());
        // 创建VirtualLayoutManager对象
        // 同时内部会创建一个LayoutHelperFinder对象，用来后续的LayoutHelper查找

        recyclerView.setLayoutManager(layoutManager);
        // 将VirtualLayoutManager绑定到recyclerView
        // 设置组件复用回收池
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);

        GridLayoutHelper gridLayoutHelper_six = new GridLayoutHelper(1);
        // 在构造函数设置每行的网格个数

        // 公共属性
        gridLayoutHelper_six.setItemCount(1);// 设置布局里Item个数


        /**
         设置Grid布局
         */
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
        gridLayoutHelper.setItemCount(2);// 设置布局里Item个数

        /**
         设置Grid布局
         */
        GridLayoutHelper gridLayoutHelper_two = new GridLayoutHelper(2);
        // 在构造函数设置每行的网格个数

        // 公共属性
        gridLayoutHelper.setItemCount(3);// 设置布局里Item个数

        /**
         设置Grid布局
         */
        GridLayoutHelper gridLayoutHelper_three = new GridLayoutHelper(5);
        gridLayoutHelper.setItemCount(3);// 设置布局里Item个数


        /**
         设置Grid布局
         */
        GridLayoutHelper gridLayoutHelper_four = new GridLayoutHelper(1);
        gridLayoutHelper.setItemCount(4);// 设置布局里Item个数

        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        linearLayoutHelper.setBgColor(Color.WHITE);
        linearLayoutHelper.setPaddingBottom(40);


        bannerBeans = new ArrayList<>();
        gridLayoutAdapter_six = new GridLayoutAdapter_six(gridLayoutHelper_six, getActivity(), bannerBeans);

        channelBeans = new ArrayList<>();
        gridLayoutHelperAdapter_three = new GridLayoutHelperAdapter_three(getActivity(), channelBeans, gridLayoutHelper_three);

        newGoodsListBeans = new ArrayList<>();
        gridLayoutAdapter_two = new GridLayoutAdapter_two(gridLayoutHelper_two, getActivity(), newGoodsListBeans);


        brandListBeans = new ArrayList<>();
        gridLayoutAdapter = new GridLayoutAdapter(gridLayoutHelper, getActivity(), brandListBeans);

        hotGoodsListBeans = new ArrayList<>();
        gridLayoutAdapter_four = new GridLayoutAdapter_four(gridLayoutHelper_four, getActivity(), hotGoodsListBeans);

        topicList = new ArrayList<>();
        horizontalAdapter = new HorizontalAdapter(getActivity(), linearLayoutHelper, topicList);

        categoryListBeans = new ArrayList<>();

        adapter = new DelegateAdapter(layoutManager, true);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    private DelegateAdapter.Adapter initTitle(String title) {

        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(1);
        gridLayoutHelper.setItemCount(1);// 设置布局里Item个数
        gridLayoutHelper.setBgColor(Color.WHITE);
        gridLayoutHelper.setMarginTop(20);
        GridLayoutHelperAdapter_five titleAdapter = new GridLayoutHelperAdapter_five(getActivity(),title, gridLayoutHelper);
        return titleAdapter;
    }

    private DelegateAdapter.Adapter initCate(List<HomeBean.DataBean.CategoryListBean.GoodsListBean> goodsList){
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
        GridCategoryAdapter gridCategoryAdapter = new GridCategoryAdapter(getActivity(), goodsList, gridLayoutHelper);

        return gridCategoryAdapter;
    }

    private DelegateAdapter.Adapter initSerach(){
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(1);
        GridSerachLayoutAdapter gridSerachLayoutAdapter = new GridSerachLayoutAdapter(gridLayoutHelper, getActivity());
        return gridSerachLayoutAdapter;
    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    public void getHome(HomeBean homeBean) {

        bannerBeans.addAll(homeBean.getData().getBanner());

        channelBeans.addAll(homeBean.getData().getChannel());
        gridLayoutHelperAdapter_three.notifyDataSetChanged();

        brandListBeans.addAll(homeBean.getData().getBrandList());
        gridLayoutAdapter.notifyDataSetChanged();

        newGoodsListBeans.addAll(homeBean.getData().getNewGoodsList());
        gridLayoutAdapter_two.notifyDataSetChanged();

        hotGoodsListBeans.addAll(homeBean.getData().getHotGoodsList());
        gridLayoutAdapter_four.notifyDataSetChanged();

        topicList.addAll(homeBean.getData().getTopicList());
        horizontalAdapter.notifyDataSetChanged();



        adapter.addAdapter(initSerach());
        adapter.addAdapter(gridLayoutAdapter_six);
        adapter.addAdapter(gridLayoutHelperAdapter_three);
        adapter.addAdapter(initTitle("品牌制造商直供"));
        adapter.addAdapter(gridLayoutAdapter);
        adapter.addAdapter(initTitle("周一周四 新品首发"));
        adapter.addAdapter(gridLayoutAdapter_two);
        adapter.addAdapter(initTitle("人气推荐"));
        adapter.addAdapter(gridLayoutAdapter_four);
        adapter.addAdapter(initTitle("专题精选"));
        adapter.addAdapter(horizontalAdapter);
        List<HomeBean.DataBean.CategoryListBean> categoryList = homeBean.getData().getCategoryList();
        for (int i = 0; i <categoryList.size(); i++) {
            goodsList1 = homeBean.getData().getCategoryList().get(i).getGoodsList();
            categoryListBeans.addAll(goodsList1);
            adapter.addAdapter(initTitle(categoryList.get(i).getName()));
            adapter.addAdapter(initCate(categoryList.get(i).getGoodsList()));
        }

        recyclerView.setAdapter(adapter);
    }
}