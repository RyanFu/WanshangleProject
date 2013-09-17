package com.wanshangle.api;

/**
 * Created by Zhangneixian on 13-9-3.
 */
public enum AppEnv {
    APPDEV(0),
    APPQA(1),
    APPPROD(2),
    APPEND(3);

    private Integer _intValue;
    AppEnv(Integer i) {
        this._intValue = i;
    }

    public Integer getIntValue()
    {
        return _intValue;
    }


}
