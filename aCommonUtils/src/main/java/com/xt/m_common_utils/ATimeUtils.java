package com.xt.m_common_utils;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.TimeUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by xuti on 2017/9/15.
 */

public class ATimeUtils {
    /***************时间格式***********************/
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT1 = "yyyy.MM.dd HH:mm";
    public static final String DATE_FORMAT2 = "yyyy-MM-dd";
    public static final String DATE_FORMAT3 = "yyyy/MM/dd HH:mm:ss";
    public static final String DATE_FORMAT4 = "MM-dd HH:mm";
    /**
     * 获取昨天00:00:00
     */
    public static Date getYestodayStart() {
        return getDayStart(-1);
    }

    /**
     * 获取昨天23:59:59
     */
    public static Date getYestodayEnd() {
        return getDayEnd(-1);
    }

    /**
     * 获取amount天之后00:00:00
     */
    public static Date getDayStart(int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, amount);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取amount天之后23:59:59
     */
    public static Date getDayEnd(int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, amount);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获取Date的00:00:00
     */
    public static Date getDateStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取Date的23:59:59
     */
    public static Date getDateEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获取两个Date的差
     */
    public static long getDateDiffer(Date date0, Date date1) {
        return Math.abs(date0.getTime() - date1.getTime());
    }

    /**
     * Date转Calendar
     */
    public static Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static Date getNowDate() {
        return TimeUtils.getNowDate();
    }

    /**
     * 获取amount天之后date
     */
    public static Date getDateDay(Date date, long timeSpan) {
        return TimeUtils.getDate(date, timeSpan, TimeConstants.DAY);
    }

    public static Date[] getToday() {
        Date endDate = ATimeUtils.getNowDate();
        return new Date[]{
                ATimeUtils.getDayStart(0),
                endDate
        };
    }

    public static String date2String(Date date) {
        return TimeUtils.date2String(date, new SimpleDateFormat(DATE_FORMAT2, Locale.CHINA));
    }

    public static String date2String1(Date date) {
        return TimeUtils.date2String(date, new SimpleDateFormat(DATE_FORMAT, Locale.CHINA));
    }

    public static String formatDateTime(long mss) {
        String DateTimes = null;
        long days = mss / (60 * 60 * 24);
        long hours = (mss % (60 * 60 * 24)) / (60 * 60);
        long minutes = (mss % (60 * 60)) / 60;
        long seconds = mss % 60;
        if (days > 0) {
            DateTimes = days + "天" + hours + "小时" + minutes + "分钟"
                    + seconds + "秒";
        } else if (hours > 0) {
            DateTimes = hours + "小时" + minutes + "分钟"
                    + seconds + "秒";
        } else if (minutes > 0) {
            DateTimes = minutes + "分钟"
                    + seconds + "秒";
        } else {
            DateTimes = seconds + "秒";
        }

        return DateTimes;
    }

    public static String formatDateTime1(long mss) {
        String DateTimes = null;
        long days = mss / (60 * 60 * 24);
        long hours = (mss % (60 * 60 * 24)) / (60 * 60);
        long minutes = (mss % (60 * 60)) / 60;
        long seconds = mss % 60;
        if (days > 0) {
            DateTimes = days + "天" + hours + "小时" + minutes + "分";
        } else if (hours > 0) {
            DateTimes = hours + "小时" + minutes + "分";
        } else if (minutes > 0) {
            DateTimes = minutes + "分";
        } else {
            DateTimes = seconds + "秒";
        }

        return DateTimes;
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
