package indi.rui.common.base.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class FileUtil {

    public static final int _1KB = 1024;

    /**
     * 读取指定路径的文件为String，使用utf8编码
     * @param filename
     * @return
     */
    public static String readClasspathFile(String filename) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            throw new RuntimeException("类加载器为空");
        }

        InputStream inputStream = classLoader.getResourceAsStream(filename);
        if (inputStream == null) {
            throw new RuntimeException("资源加载失败");
        }

        StringBuffer sb = new StringBuffer();
        try {
            byte[] buf = new byte[64 * _1KB];
            int len;
            while ((len = inputStream.read(buf)) > 0) {
                sb.append(new String(buf, 0, len, "utf-8"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    /**
     * 将输入流保存到指定路径
     * @param in
     * @param path
     */
    public static void save(InputStream in, String path, String filename) {
        if (in == null) {
            return;
        }
        byte[] buf = new byte[64 * _1KB];;
        OutputStream out = null;
        try {
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            out = new FileOutputStream(dir.getAbsoluteFile() + File.separator + filename);

            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new File("temp").getAbsoluteFile() + File.separator);
    }
}
