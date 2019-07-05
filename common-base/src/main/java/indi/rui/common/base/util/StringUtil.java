package indi.rui.common.base.util;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: study_20180903
 * @description: String相关工具类
 * @author: Yaowr
 * @create: 2018-09-03 10:48
 **/
@Slf4j
public class StringUtil {
    /**
     * 邮箱正则表达式
     */
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";

    /**
     * 手机号正则表达式
     */
    public static final String PHONE_NUMBER_REGEX = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";

    public static boolean isEmpty(String str) {
        return str == null || str.length() < 1;
    }

    public static boolean isPhoneNumber(String phoneNum) {
        return matches(phoneNum, PHONE_NUMBER_REGEX);
    }

    public static boolean isEmail(String email) {
        return matches(email, EMAIL_REGEX);
    }

    public static boolean matches(String str, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);

        return m.matches();
    }

    public static void main(String[] args) {
//        String email = "-abcufwefuiiw_f2iuff_fwfw@-154-233rfdsfs.fwefwefwe.com";
//        String email = "314860790@qq.com";
        String email = "asdf@asdf_asdf.com";
        log.info("{}", email.matches(EMAIL_REGEX));
    }

}
