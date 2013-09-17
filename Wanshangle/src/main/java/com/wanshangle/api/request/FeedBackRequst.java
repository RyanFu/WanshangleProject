package com.wanshangle.api.request;

import com.loopj.android.http.RequestParams;

import java.util.TreeMap;

/**
 * Created by Zhangneixian on 13-9-13.
 * FeedBackRequst request <br\>  see {@link #API}
 */
public class FeedBackRequst extends RequestParams{

    private String osKey = "os";
    private String osVKey = "osv";
    private String contentKey = "content";
    private String API = "server.feedback";

    private String os;
    private String osv;
    private String content;

    public FeedBackRequst()
    {
        super();
        this.put("api", API);
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
        this.put(osKey, os);
    }

    public String getOsv() {
        return osv;
    }

    public void setOsv(String osv) {
        this.osv = osv;
        this.put(osVKey, osv);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        this.put(contentKey, content);
    }
}
