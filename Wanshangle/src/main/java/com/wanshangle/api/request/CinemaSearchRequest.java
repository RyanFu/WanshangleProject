package com.wanshangle.api.request;

import com.loopj.android.http.RequestParams;

/**
 * Created by Zhangneixian on 13-9-16.
 * api request {@link #api}
 */
public class CinemaSearchRequest extends RequestParams {

    private String api = "cinema.search";
    private String nameKey = "name";

    private String name;

    public CinemaSearchRequest()
    {
        super();
        this.put("api", api);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.put(nameKey, name);
    }
}
