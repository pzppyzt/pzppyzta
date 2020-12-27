package com.example.my12_17.view;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.example.my12_17.R;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity implements AMap.OnMyLocationChangeListener, PoiSearch.OnPoiSearchListener, View.OnClickListener, RouteSearch.OnRouteSearchListener {

    private MapView mapView;
    private AMap aMap;
    MyLocationStyle myLocationStyle;
    private EditText edSerach;
    private Button btnSerach;
    private Button qie;
    private EditText edStart;
    private EditText edEnd;
    private Button btnSerachTwo;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mapView = (MapView) findViewById(R.id.map);
        edSerach = findViewById(R.id.ed_Serach);
        edStart = findViewById(R.id.ed_Start);
        edEnd = findViewById(R.id.ed_End);
        btnSerachTwo = findViewById(R.id.btn_Serach_two);
        btnSerach = findViewById(R.id.btn_Serach);
        qie = findViewById(R.id.qie);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        aMap = mapView.getMap();
        btnSerach.setOnClickListener(this);
        qie.setOnClickListener(this);
        btnSerachTwo.setOnClickListener(this);
//        initView();
        initMapD();
    }

    private boolean isButtom=true;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qie:
                if (isButtom){
                    edSerach.setVisibility(View.GONE);
                    btnSerach.setVisibility(View.GONE);

                    edStart.setVisibility(View.VISIBLE);
                    edEnd.setVisibility(View.VISIBLE);
                    btnSerachTwo.setVisibility(View.VISIBLE);
                    isButtom=false;
                }else {
                    edSerach.setVisibility(View.VISIBLE);
                    btnSerach.setVisibility(View.VISIBLE);

                    edStart.setVisibility(View.GONE);
                    edEnd.setVisibility(View.GONE);
                    btnSerachTwo.setVisibility(View.GONE);
                    isButtom=true;
                }

                break;
            case R.id.btn_Serach_two:
                Serach_two();
                break;
            case R.id.btn_Serach:
                Serach();
                break;
        }
    }

    private void Serach_two() {
        RouteSearch routeSearch = new RouteSearch(this);

        routeSearch.setRouteSearchListener(this);

        LatLonPoint mStartPoint = null;
        LatLonPoint mEndPoint = null;
        RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(mStartPoint, mEndPoint);
        RouteSearch.WalkRouteQuery query = new RouteSearch.WalkRouteQuery(fromAndTo, RouteSearch.WALK_DEFAULT);
        routeSearch.calculateWalkRouteAsyn(query);
    }


    private int currentPage = 0;

    private void Serach() {
        PoiSearch.Query query = new PoiSearch.Query(edSerach.getText().toString(), "", "北京");
        query.setPageSize(10);
        query.setPageNum(currentPage);
        currentPage++;
        PoiSearch poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();

    }

    public void initMapD() {
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
//aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。


  myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，并且蓝点会跟随设备移动。


        myLocationStyle.showMyLocation(true);

        myLocationStyle.interval(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onMyLocationChange(Location location) {
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        ArrayList<PoiItem> pois = poiResult.getPois();
        for (PoiItem poiItem :
                pois) {
            Log.e("TAG", "onPoiSearched: " + poiItem.getAdName());
            Log.e("TAG", "onPoiSearched: " + poiItem.toString());
        }

        PoiOverlay poiOverlay = new PoiOverlay(aMap, pois);
        poiOverlay.removeFromMap();
        poiOverlay.addToMap();
        poiOverlay.zoomToSpan();

    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }


    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {

    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

    }

    private void initView() {

    }
}