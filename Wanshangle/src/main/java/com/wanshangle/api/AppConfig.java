package com.wanshangle.api;


import com.wanshangle.utils.DebugUtils;
import com.wanshangle.utils.LogUtils;

/**
 * Created by Zhangneixian on 13-9-3.
 */
public final class AppConfig {



    /********************************************************
     * define configuration item
     * ******************************************************/
    public static class ConfigItem {
        public String appId;
        public String apiVersion;
        public String apiRequestUrl;
        public String bdLocationAK;
    }

    // define static variables
    private static ConfigItem[] _configItemArray;
    private static AppEnv _currentEnv;
    private static Boolean _apiMessageDebug;

    // do static initialization
    static {
        _currentEnv = AppEnv.APPDEV;
        _apiMessageDebug = true;
        LogUtils.isDebug = true;

        _configItemArray = new ConfigItem[AppEnv.APPEND.getIntValue()];
        for (int i = 0; i < AppEnv.APPEND.getIntValue(); i++) {
            _configItemArray[i] = new ConfigItem();
        }

        _configItemArray[AppEnv.APPDEV.getIntValue()].appId = "000001";
        _configItemArray[AppEnv.APPDEV.getIntValue()].apiVersion = "1.1";
        _configItemArray[AppEnv.APPDEV.getIntValue()].apiRequestUrl = "http://api.wanshangle.com:10000/api";
        _configItemArray[AppEnv.APPDEV.getIntValue()].bdLocationAK = "7dcb9d8dc195728e87092260b56e6ae2";

        _configItemArray[AppEnv.APPQA.getIntValue()].appId = "000001";
        _configItemArray[AppEnv.APPQA.getIntValue()].apiVersion = "1.1";
        _configItemArray[AppEnv.APPQA.getIntValue()].apiRequestUrl = "http://api.wanshangle.com:10000/api";
        _configItemArray[AppEnv.APPDEV.getIntValue()].bdLocationAK = "7dcb9d8dc195728e87092260b56e6ae2";

        _configItemArray[AppEnv.APPPROD.getIntValue()].appId = "000001";
        _configItemArray[AppEnv.APPPROD.getIntValue()].apiVersion = "1.1";
        _configItemArray[AppEnv.APPPROD.getIntValue()].apiRequestUrl = "http://api.wanshangle.com:10000/api";
        _configItemArray[AppEnv.APPPROD.getIntValue()].apiRequestUrl = "35ae24610fce4b3984079f160801fde6";

    }

    public static void setApiEnv(AppEnv env) {
        _currentEnv = env;
        DebugUtils.setDebugLevel(_currentEnv);
    }

    public static Boolean getApiMessageDebug() {
        return _apiMessageDebug;
    }


    public static String getAppId() {
        return _configItemArray[_currentEnv.getIntValue()].appId;
    }

    public static String getApiVersion() {
        return _configItemArray[_currentEnv.getIntValue()].apiVersion;
    }

    public static String getApiRequestUrl() {
        return _configItemArray[_currentEnv.getIntValue()].apiRequestUrl;
    }

    public static String getBDLocationAK()
    {

        return _configItemArray[_currentEnv.getIntValue()].bdLocationAK;
    }
}

