package com.wanshangle.api;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.wanshangle.utils.LogUtils;

/**
 * Created by Zhangneixian on 13-9-3.
 */
public class ApiClient {

    private final static String TAG = ApiClient.class.getSimpleName();

    // request base url
    private static String REQUEST_URL = AppConfig.getApiRequestUrl();

    // request client
    private static AsyncHttpClient mClient = new AsyncHttpClient();

    public static void requestByGet(RequestParams _params, AsyncHttpResponseHandler _handler)
    {
        prepareParams(_params);
        //debug mode , print all params
        if (AppConfig.getApiMessageDebug())
        {
            LogUtils.d(TAG, REQUEST_URL);
            LogUtils.d(TAG, _params.toString());
        }
        mClient.get(REQUEST_URL , _params, _handler);
    }


    public static void requestByPost(RequestParams _params, AsyncHttpResponseHandler _handler)
    {
        prepareParams(_params);
        //debug mode , print all params
        if (AppConfig.getApiMessageDebug())
        {
            LogUtils.d(TAG, REQUEST_URL);
            LogUtils.d(TAG, _params.toString());
        }


        mClient.post(REQUEST_URL, _params, _handler);

    }

    private static void prepareParams(RequestParams _params)
    {
        _params.put(signKey ,sign);
        _params.put(vKey, v);
        _params.put(appIdKey, appId);
    }

    protected static String signKey = "sign";
    protected static String vKey = "v";
    protected static String appIdKey = "appId";

    protected static String sign = "sign";
    protected static String v = AppConfig.getApiVersion();
    protected static String appId = AppConfig.getAppId();

}
