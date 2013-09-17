package com.wanshangle.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.wanshangle.R;
import com.wanshangle.base.BaseActivity;
import com.wanshangle.main.LocationInfo;
import com.wanshangle.main.WSLApplication;
import com.wanshangle.utils.LogUtils;
import com.wanshangle.utils.NetUtils;

public class SplashActivity extends BaseActivity implements BDLocationListener{

    private static final String TAG = SplashActivity.class.getSimpleName();
    private LocationClient mLocClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getActionBar().hide();
        locateIfGPSIsOn();
    }



    /**
     * async execute locate
     */
    private void locateIfGPSIsOn()
    {
        mLocClient = ((WSLApplication)getApplication()).getLocationClient();
        //
        if (NetUtils.isNetworkConnected(this))
        {
            LogUtils.v(TAG, "NetWork is connected");
            //locate
            setLocationOption();
        }
        else
        {
            // There is no Network Or GPS signal , so we just launch the activity
            LogUtils.v(TAG, "NetWork is not connected");
            launchMain();
        }
    }


    private void setLocationOption()
    {
        LocationClientOption mOption = new LocationClientOption();
        mOption.setOpenGps(false);
        mOption.disableCache(true);
        mOption.setPriority(LocationClientOption.NetWorkFirst);
        mOption.setAddrType("all");
        mOption.setCoorType("bd09ll");
        mOption.setTimeOut(3000);
        mLocClient.setLocOption(mOption);
        mLocClient.registerLocationListener(this);
        if (mLocClient.isStarted())
            mLocClient.requestLocation();
        else
        {
            mLocClient.start();
            mLocClient.requestLocation();
        }
    }




    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        if (null == bdLocation)
        {
            LogUtils.v(TAG, "bdLocation is null");
            launchMain();
            return;
        }
        LogUtils.v(TAG, "LocType = " + bdLocation.getLocType());
        if (bdLocation.getLocType() < 162)
        {

            LocationInfo mLocInfo = new LocationInfo();
            mLocInfo.setLatitude(bdLocation.getLatitude());
            mLocInfo.setLongitude(bdLocation.getLongitude());
            mLocInfo.setProvince(bdLocation.getProvince());
            mLocInfo.setCity(bdLocation.getCity());
            mLocInfo.setAddr(bdLocation.getAddrStr());
            mLocInfo.setFormatDate(bdLocation.getTime());
            ((WSLApplication)getApplication()).setLocation(mLocInfo);
            LogUtils.i(TAG, "My Location is \n " + mLocInfo.toString());
        }

        launchMain();
    }

    @Override
    public void onReceivePoi(BDLocation bdLocation) {

    }


    private void launchMain()
    {
        Log.e(TAG, Build.VERSION.CODENAME);
        Log.e(TAG, Build.VERSION.INCREMENTAL);
        Log.e(TAG, Build.VERSION.RELEASE);
        Log.e(TAG, Build.VERSION.SDK_INT + "");
        Log.e(TAG, Build.BOARD);
        Log.e(TAG, Build.BRAND);
        Log.e(TAG, Build.DEVICE);
        Log.e(TAG, Build.DISPLAY);
        Log.e(TAG, Build.ID);
        Log.e(TAG, Build.PRODUCT);
        Log.e(TAG, Build.MODEL);
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        finish();
    }
}
