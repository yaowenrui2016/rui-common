package indi.rui.common.base.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class MD5Util {
    private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    private static MessageDigest digest;

    private static final Lock lock = new ReentrantLock(false);

    static {
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            log.info("MD5 'MessageDigest' object initializing failed.", e.getMessage());
        }
    }

    public static String encrypt(File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        FileChannel ch = in.getChannel();
        MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,
                file.length());
        lock.lock();
        try {
            digest.update(byteBuffer);
            return bufferToHex(digest.digest());
        } finally {
            lock.unlock();
        }
    }

    public static String encrypt(String str) {
        byte[] bytes;
        try {
            bytes = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
            throw new RuntimeException("MD5 encryption failed.");
        }
        return encrypt(bytes);
    }

    public static String encrypt(byte[] bytes) {
        lock.lock();
        try {
            digest.update(bytes);
            return bufferToHex(digest.digest());
        } finally {
            lock.unlock();
        }
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    private static String saltedMD5(String md5Str, String salt) {
        return encrypt(md5Str + salt);
    }


    private static void test1(String password, String salt) {
        System.out.println("password: " + password);

        String md5edPassword = encrypt(password);
        System.out.println("md5edPassword: " + md5edPassword);

        System.out.println("salt: " + salt);

        String saltedMd5Password = encrypt(md5edPassword + salt);
        System.out.println("saltedMd5Password: " + saltedMd5Password);
    }


    public static void main(String arg[]) {
        test1("a123456", "d3a3e51d92a24839920116dda9e146d2");
        System.out.println(System.getenv().get("JAVA_HOME"));
    }
}
