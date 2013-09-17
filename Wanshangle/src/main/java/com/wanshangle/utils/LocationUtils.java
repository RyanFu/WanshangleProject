package com.wanshangle.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;


/**
 * Created by Zhangneixian on 13-8-30.
 */
public class LocationUtils {

    private final static String TAG = LocationUtils.class.getSimpleName();

    public static boolean isGPSAvailable(Context _context)
    {
        return isSupportProviderAvailable(_context, LocationManager.GPS_PROVIDER);
    }

    public static boolean isNetLocateAvailable(Context _context)
    {
        return isSupportProviderAvailable(_context, LocationManager.NETWORK_PROVIDER);
    }


    public static boolean isSupportProviderAvailable(Context _context, String _providerName)
    {
        LocationManager tLocationManager = (LocationManager) _context.getSystemService(Context.LOCATION_SERVICE);
        if (null == tLocationManager)
        {
            LogUtils.i(TAG, "LocationManager is null");
            return false;
        }

        if (tLocationManager.isProviderEnabled(_providerName))
        {
            LogUtils.i(TAG, "This location module Is Available");
            return true;
        }
        LogUtils.i(TAG, "This location module Is Not Available");

        return false;
    }


    public static final void openGPS(Context context) {
        Intent GPSIntent = new Intent();
        GPSIntent.setClassName("com.android.settings",
                "com.android.settings.widget.SettingsAppWidgetProvider");
        GPSIntent.addCategory("android.intent.category.ALTERNATIVE");
        GPSIntent.setData(Uri.parse("custom:3"));
        try {
            PendingIntent.getBroadcast(context, 0, GPSIntent, 0).send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }




}
