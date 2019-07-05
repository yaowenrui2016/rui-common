package indi.rui.common.base.util;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class DateUtil {
    private static final int SECOND = 1000;
    private static final int MINUTE = 60 * 1000;
    private static final int HOUR = 60 * 60 * 1000;
    private static final int DAY = 24 * 60 * 60 * 1000;

    public static String now() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    public static String duration(long timeMillis) {
        String str;
        if (timeMillis < SECOND) {
            str = timeMillis + "毫秒";                                      // 一秒以内
        } else if (timeMillis < 30 * SECOND) {
            str = Math.round(timeMillis / 100) / 10f + "秒";                    // 30秒以内
        } else if (timeMillis < MINUTE) {
            str = Math.round(timeMillis / 1000f) + "秒";                                 // 一分钟以内
        } else if (timeMillis < HOUR) {
            str = new SimpleDateFormat("mm分ss秒").format(new Date(timeMillis));       // 一小时以内
        } else if (timeMillis < DAY){
            str = new SimpleDateFormat("HH时mm分ss秒").format(new Date(timeMillis));   // 一天以内
        } else {
            long day = timeMillis / DAY;                                // 大于一天
            String sub = "";
            if (timeMillis % DAY > MINUTE) {
                sub = duration(timeMillis % DAY);
            }
            str = day + "天" + sub;
        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println(duration(2 * DAY + 60 * SECOND));
    }
}
