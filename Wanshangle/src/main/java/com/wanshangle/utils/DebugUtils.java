package com.wanshangle.utils;

import com.wanshangle.api.AppEnv;


/**
 * Created by Zhangneixian on 13-8-29.
 */
public class DebugUtils {

    private final static String TAG = DebugUtils.class.getSimpleName();


    private static AppEnv pLevel;

    /**
     * enable StrickMode
     */
    private static void enableStrickMode(){
        LogUtils.i(TAG, "enableStrickMode");
    }
    public static void setDebugLevel(AppEnv _level){
        pLevel = _level;
        if (null == pLevel)
        {
            return;
        }

        switch (pLevel)
        {
            case APPDEV:
                enableStrickMode();

                break;
            case APPQA:
                enableStrickMode();

                break;
            case APPPROD:
//                enableStrickMode();

                break;
            case APPEND:


                break;
            default:
        }


    }


}
