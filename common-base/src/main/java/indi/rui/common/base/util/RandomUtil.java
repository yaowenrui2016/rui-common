package indi.rui.common.base.util;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.UUID;

@Slf4j
public class RandomUtil {
    private static final Random r = new Random(System.currentTimeMillis());

    private static final String[] CHARS = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"} ;

    /**************************************************
     *            STATIC PUBLIC METHOD                *
     **************************************************/

    public static int nextInt(int bound) {
        return r.nextInt(bound);
    }

    public static int nextInt(int min, int max) {
        return min + nextInt(max - min);
    }

    public static String nextCNChar() {
        int high = 129 + nextInt(125);
        int low;
        while ((low = 64 + nextInt(190)) == 127)
            log.info("Beautiful <<< 127 >>>");
        StringBuffer sb = new StringBuffer();
        sb.append(CHARS[high / 16]);
        sb.append(CHARS[high % 16]);
        sb.append(CHARS[low / 16]);
        sb.append(CHARS[low % 16]);

//        LOGGER.info("bytes: {}", sb.toString());

        byte[] bytes = new byte[]{(byte) high, (byte) low};
        try {
            return new String(bytes, "gbk");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Failed to generate Chinese character");
        }
    }

    public static char nextENChar() {
        return (char) (nextInt(26) + 97);
    }

    public static String nextWord(int minLen, int maxLen,final String lang) {
        int len = nextInt(minLen, maxLen);

        StringBuffer sb = new StringBuffer();
        while(len-- > 0) {
            String c = "";
            if ("CN".equals(lang)) {
                c = nextCNChar();
            } else if ("EN".equals(lang)) {
                c = String.valueOf(nextENChar());
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public static String nextEmail() {
        return nextWord(2, 8, "EN") + "@" + nextWord(2, 8, "EN") + "." + nextWord(2, 3, "EN");
    }

    public static String nextDigital(int length) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            stringBuffer.append(nextInt(10));
        }
        return stringBuffer.toString();
    }

    public static String nextUserId() {
        return System.currentTimeMillis() + nextDigital(4);
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
