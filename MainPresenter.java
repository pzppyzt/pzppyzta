package com.example.my12_17.presenter;


import com.example.mvplibrary.api.ICallBack;
import com.example.mvplibrary.api.URLService;
import com.example.mvplibrary.api.base.BasePresenter;
import com.example.my12_17.bean.HomeBean;
import com.example.my12_17.interfaces.MainInterface;
import com.example.my12_17.model.MainModel;

public class MainPresenter extends BasePresenter<MainInterface.View, MainInterface.Model> implements MainInterface.Presenter {


//    @Override
//    public void getbanner() {
//        IModel.requestbanner(URLService.Banner_list, new ICallBack<BannerBean>() {
//            @Override
//            public void onSuccess(BannerBean bannerBean) {
//                Iview.getBanner(bannerBean);
//            }
//
//            @Override
//            public void onFail() {
//
//            }
//        });
//    }

    @Override
    protected MainModel getModel() {
        return new MainModel();
    }

    @Override
    public void getHome() {
        IModel.requestHome(URLService.Home_list, new ICallBack<HomeBean>() {
            @Override
            public void onSuccess(HomeBean homeBean) {
                Iview.getHome(homeBean);
            }

            @Override
            public void onFail() {

            }
        });
    }
}
