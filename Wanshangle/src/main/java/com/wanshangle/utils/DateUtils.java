package com.wanshangle.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Zhangneixian on 13-9-11.
 */
public class DateUtils {

    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

    public static String format_yyyyMMdd_HHmmss(int timestamp)
    {
        SimpleDateFormat format = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
        return format.format(new Date(timestamp));

    }


    public static long parse_yyyyMMdd_HHmmss(String formatDate)
    {
        SimpleDateFormat format = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
        try {
            Date date = format.parse(formatDate);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("format is illegal");
    }
}
