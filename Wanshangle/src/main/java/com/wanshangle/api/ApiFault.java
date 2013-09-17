package com.wanshangle.api;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Zhangneixian on 13-9-3.
 */
public class ApiFault implements IBeanParser{
    private Integer errorCode;
    private String errorDetail;
    private String errorMessage;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    @Override
    public void parseData(JSONObject jsonObject) throws JSONException {
        setErrorCode(jsonObject.getInt("code"));
        setErrorDetail(jsonObject.getString("detail"));
        setErrorMessage(jsonObject.getString("message"));
    }

    @Override
    public String toString() {
        return "ApiFault{" +
                "errorCode=" + errorCode +
                ", errorDetail='" + errorDetail + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
