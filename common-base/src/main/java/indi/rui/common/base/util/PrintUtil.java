package indi.rui.common.base.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

@Slf4j
public class PrintUtil {
    public static <T> String print(T[] arrays) {
        if (arrays == null || arrays.length < 1)
            return "";
        StringBuffer sb = new StringBuffer("[");
        for (T t : arrays) {
            sb.append(t.toString() + ",");
        }
        sb.append("]");
        sb.deleteCharAt(sb.lastIndexOf(","));
        return sb.toString();
    }

    public static String printEnv() {
        StringBuffer sb = new StringBuffer("System Environment:\n{\n");
        Map<String, String> env = System.getenv();
        if (env != null && env.size() > 0) {
            Iterator<Map.Entry<String, String>> it = env.entrySet().iterator();
            int num = 0;
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                sb.append("\t" + (num++) + "\t" + entry.getKey() + "=" + entry.getValue() + "\n");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public static <K, V> String print(Map<K, V> map) {
        if (map == null || map.size() < 1)
            return "";
        StringBuffer sb = new StringBuffer("[");
        Iterator<Map.Entry<K, V>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<K, V> entry = iterator.next();
            sb.append("{" + entry.getKey() + " = " + entry.getValue() + "}, ");
        }
        sb.append("]");
        sb.deleteCharAt(sb.lastIndexOf(","));
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] array = new String[]{"a", "b", "c"};
//        String[] array = null;
        String s = Arrays.toString(array);
//        String s = print(array);
        log.info(s);
    }
}
