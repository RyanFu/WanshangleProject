package com.wanshangle.main;

import android.app.Application;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.wanshangle.api.AppConfig;
import com.wanshangle.api.AppEnv;
import com.wanshangle.utils.LogUtils;

/**
 * Created by Zhangneixian on 13-9-10.
 */
public class WSLApplication extends Application{

    private static final String TAG = WSLApplication.class.getSimpleName();


    private LocationClient mLocationClient;
    private LocationInfo mLocation;

    @Override
    public void onCreate() {
        super.onCreate();

        // Configure the app environment
        AppConfig.setApiEnv(AppEnv.APPDEV);

        // Init lcationclient
        initLocation();
    }


    private void initLocation() {
        mLocationClient = new LocationClient(getApplicationContext());
        LogUtils.d(TAG, "AK = " + AppConfig.getBDLocationAK());
        mLocationClient.setAK(AppConfig.getBDLocationAK());

    }

    public LocationClient getLocationClient() {
            return mLocationClient;
    }

    public LocationInfo getLocation() {
        return mLocation;
    }

    public void setLocation(LocationInfo mLocation) {
        this.mLocation = mLocation;
    }
}
