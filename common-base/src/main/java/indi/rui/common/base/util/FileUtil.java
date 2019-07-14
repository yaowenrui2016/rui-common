package indi.rui.common.base.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class FileUtil {

    public static final int _1KB = 1024;

    /**
     * 将输入流保存到指定路径
     * @param in
     * @param filePath
     */
    public static void save(InputStream in, String filePath, String fileName) {
        if (in == null) {
            return;
        }
        byte[] buf = new byte[64 * _1KB];;
        OutputStream out = null;
        try {
            File path = new File(filePath);
            if (!path.exists()) {
                path.mkdirs();
            }
            File file = new File(path, fileName);
            if (file.exists()) {
                throw new RuntimeException("文件已存在");
            }
            out = new FileOutputStream(file);
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException("写文件异常");
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                    throw new RuntimeException("关闭输出流失败");
                }
            }
        }
    }

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

    public static void remove(String filePath, String fileName) {
        File file = new File(filePath, fileName);
        if (file.exists()) {
            if (!file.delete()) {
                throw new RuntimeException("文件删除失败");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new File("temp").getAbsoluteFile() + File.separator);
    }
}
