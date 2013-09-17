package com.wanshangle.api.request;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.wanshangle.api.AppConfig;
import com.wanshangle.api.ApiFault;
import com.wanshangle.api.OnApiCompleteListener;
import com.wanshangle.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhangneixian on 13-9-2. <br/>
 * ApiResponseHandler extends JsonHttpResponseHandler , subclass need implements {@link #parseResultData(org.json.JSONObject)}
 */
public abstract class ApiResponseHandler extends JsonHttpResponseHandler{

    private final static String TAG = ApiResponseHandler.class.getSimpleName();

    private OnApiCompleteListener mListener;

    /**
     * register a listener to listen the state of api's execution
     * @param mListener
     */
    public void addApiCompletionListener(OnApiCompleteListener mListener) {
        this.mListener = mListener;
    }

    protected JSONObject _rootJsonObject;

    protected List<ApiFault> errorList = new ArrayList<ApiFault>();

    public abstract BaseResponse parseResultData(JSONObject jsonObject) throws JSONException;


    public List<ApiFault> getErrorList(){
        return errorList;
    }

    public Boolean hasError() {
        return !errorList.isEmpty();
    }

    public void parseApiError(JSONArray jsonArray) throws JSONException {

        if (jsonArray.length() <= 0) {
            // no error, we should return
            return;
        }

        // clear old values
        errorList.clear();

        // add new values
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            ApiFault apiFault = new ApiFault();
            apiFault.parseData(jsonObject);
            errorList.add(apiFault);
        }
    }

    public void parseHttpDataAll(JSONObject jsonObject) throws JSONException {

        _rootJsonObject = jsonObject;

        parseApiError(_rootJsonObject.getJSONArray("errors"));

        if (hasError()) {
            // api return error, we should return
            if (AppConfig.getApiMessageDebug()) {
                for (ApiFault apiFault : errorList) {
                    LogUtils.d(this.getClass().getSimpleName(),
                            apiFault.toString());
                }
            }
            mListener.onApiError(errorList);
            return;
        }


        mListener.onApiDone(parseResultData(jsonObject.getJSONObject("data")));
    }

    @Override
    public void onSuccess(JSONObject jsonObject) {
       try {
           parseHttpDataAll(jsonObject);
       }
       catch (JSONException e)
       {
           Log.d(TAG , e.toString());
       }
    }

    @Override
    public void onFailure(Throwable throwable) {

        mListener.onApiProcessError();
    }
}
