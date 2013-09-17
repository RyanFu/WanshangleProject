package com.wanshangle.api.request;

import com.loopj.android.http.RequestParams;

import java.util.TreeMap;

/**
 * Created by Zhangneixian on 13-9-16.
 */
public class CinemaListRequest extends RequestParams{
    private String API = "cinema.list";
    private String cityidKey = "cityid";
    private String offsetKey = "offset";
    private String orderKey = "order";
    private String latitudeKey = "lat";
    private String longitudeKey = "lng";
    private String limitKey = "limit";
    private String typeKey = "type";
    private String districtidKey = "districtid";

    private String cityId;
    private String offset;
    private String order;
    private String latitude;
    private String longitude;
    private String limit;
    private String type;
    private String districtid;

    public CinemaListRequest() {
        super();
        this.put("api", API);
    }

    public String getCityId() {
        return cityId;

    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
        this.put(cityidKey, cityId);
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
        this.put(offsetKey, offset);
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
        this.put(orderKey, order);
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
        this.put(latitudeKey, latitude);
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
        this.put(longitudeKey, longitude);
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
        this.put(limitKey, limit);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        this.put(typeKey, type);
    }

    public String getDistrictid() {
        return districtid;
    }

    public void setDistrictid(String districtid) {
        this.districtid = districtid;
        this.put(districtidKey, districtid);
    }
}
