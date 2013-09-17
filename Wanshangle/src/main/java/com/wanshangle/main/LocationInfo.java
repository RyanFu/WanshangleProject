package com.wanshangle.main;


import com.wanshangle.utils.DateUtils;

/**
 * Created by Zhangneixian on 13-9-11.
 */
public class LocationInfo {

    double latitude;
    double longitude;
    String province;
    String city;
    String district;
    String addr;
    String formatDate;
    double timestamp;


    public String getFormatDate() {
        return formatDate;
    }

    public void setFormatDate(String formatDate) {
        this.formatDate = formatDate;
        long _timestamp = DateUtils.parse_yyyyMMdd_HHmmss(formatDate);
        setTimestamp(_timestamp);
    }



    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(double timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "LocationInfo{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", addr='" + addr + '\'' +
                ", formatDate='" + formatDate + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
