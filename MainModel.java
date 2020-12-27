package com.example.my12_17.model;


import com.example.mvplibrary.api.ICallBack;
import com.example.mvplibrary.api.URLService;
import com.example.mvplibrary.api.net.RetroitUtils;
import com.example.my12_17.interfaces.MainInterface;

public class MainModel implements MainInterface.Model {
    @Override
    public <T> void requestHome(String url, ICallBack<T> callBack) {
        RetroitUtils.getInstance().get(URLService.Home_list,callBack);
    }


//    @Override
//    public <T> void requestbanner(String url, ICallBack<T> callBack) {
//        RetroitUtils.getInstance().get(URLService.Banner_list,callBack);
//    }
}
