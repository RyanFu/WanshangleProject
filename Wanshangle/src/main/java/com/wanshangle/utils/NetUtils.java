package com.wanshangle.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Zhangneixian on 13-8-30.
 */
public class NetUtils {

    private final static String TAG = NetUtils.class.getSimpleName();
    private final static int ALL = -100;


    /**
     * judge whether the network is connected
     * @param _context
     * @return
     */
    public static boolean isNetworkAvailable(Context _context)
    {
        return isSupportModuleAvailable(_context, ALL, true);
    }

    public static boolean isNetworkConnected(Context _context)
    {
        return isSupportModuleAvailable(_context, ALL, false);
    }


    public static boolean isWIFIAvailable(Context _context)
    {
        return isSupportModuleAvailable(_context, ConnectivityManager.TYPE_WIFI, true);

    }


    public static boolean isWIFIConnected(Context _context)
    {
        return isSupportModuleAvailable(_context, ConnectivityManager.TYPE_WIFI, false);
    }


    public static boolean isCellularNetworkAvailable(Context _context)
    {
        return isSupportModuleAvailable(_context, ConnectivityManager.TYPE_MOBILE, true);
    }


    public static boolean isCellularNetworkConnected(Context _context)
    {
        return isSupportModuleAvailable(_context, ConnectivityManager.TYPE_MOBILE, false);
    }


    /**
     *
     */
    public static boolean isSupportModuleAvailable(Context _context, int connectivityType, boolean availableOrConnected)
    {
        ConnectivityManager tConnectivityManager = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null == tConnectivityManager)
        {
            LogUtils.e(TAG, "could not get the connectivity manager");
            return false;
        }

        if (ALL == connectivityType)
        {
            NetworkInfo[] tNetworkArrays = tConnectivityManager.getAllNetworkInfo();
            if (null == tNetworkArrays)
            {
                return false;
            }
            for (NetworkInfo tInfo : tNetworkArrays)
            {
                if ( availableOrConnected ? tInfo.isAvailable() : tInfo.isConnectedOrConnecting())
                {
                    LogUtils.i(TAG, "network is available");
                    return true;
                }

            }
            LogUtils.i(TAG, "network is not available");
            return false;
        }
        else
        {
            NetworkInfo info = tConnectivityManager.getNetworkInfo(connectivityType);
            if (null == info)
            {
                LogUtils.i(TAG, "this net module does not exist");
                return false;
            }

            return availableOrConnected ? info.isAvailable() : info.isConnectedOrConnecting();
        }

    }



}
