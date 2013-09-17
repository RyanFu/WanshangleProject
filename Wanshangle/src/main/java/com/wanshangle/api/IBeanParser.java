package com.wanshangle.api;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Zhangneixian on 13-9-3.
 */
public interface IBeanParser {
    public void parseData(JSONObject jsonObject) throws JSONException;
}
