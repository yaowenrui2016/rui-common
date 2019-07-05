package indi.rui.common.base.util;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @program: study_20180903
 * @description:
 * @author: Yaowr
 * @create: 2018-10-30 16:36
 **/
@Slf4j
public class CronUtil {
    private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String getCron(String timestamp) {
        Date date = parse(timestamp, DATETIME_PATTERN);
        return getCron(date);
    }

    public static String getCron(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int second = calendar.get(Calendar.SECOND);
        int minute = calendar.get(Calendar.MINUTE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        return second + " " + minute + " " + hour + " " + day + " " + month + " ?";
    }

    public static Date parse(String timestamp, String pattern) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            return dateFormat.parse(timestamp);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
