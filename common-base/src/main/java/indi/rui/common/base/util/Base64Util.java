package indi.rui.common.base.util;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * @program: study_20180903
 * @description: BASE64加密算法工具类
 * @author: Yaowr
 * @create: 2018-09-29 13:54
 **/
@Slf4j
public class Base64Util {
    public static String encode(byte[] bytes) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(bytes);
    }

    public static byte[] decode(String s) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(s);
    }

    /* 测试 */
    private static void test1() throws IOException {
        String str = encode("abc".getBytes("iso8859-1"));
        log.info(str);

        String result = new String(decode(str), "iso8859-1");
        log.info(result);
    }

    public static void main(String[] args) throws IOException {
        test1();
    }
}
