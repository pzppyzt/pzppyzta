package com.example.my12_17.interfaces;

import com.example.mvplibrary.api.ICallBack;
import com.example.mvplibrary.api.base.BaseModel;
import com.example.mvplibrary.api.base.BaseView;
import com.example.my12_17.bean.HomeBean;

public interface MainInterface {

    interface View extends BaseView {
//        void getNews(NewBean newBean);
        void getHome(HomeBean homeBean);
    }

    interface Presenter{
//        void getnews();
        void getHome();
    }

    interface Model extends BaseModel {
//        <T> void requestnews(String url, ICallBack<T> callBack);
        <T> void requestHome(String url, ICallBack<T> callBack);
    }
}
