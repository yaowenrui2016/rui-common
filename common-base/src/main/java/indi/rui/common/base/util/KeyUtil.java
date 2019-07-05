package indi.rui.common.base.util;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @program: study
 * @description:
 * @author: Yaowr
 * @create: 2019-01-22 15:00
 **/
@Slf4j
public class KeyUtil {
    public static void exportCert() {
        String alias = "mpr";
        String keyStoreFile = System.getProperty("user.home") + File.separator + "mpr.keystore";
        String exportCertFile = System.getProperty("user.home") + File.separator + "mpr.crt";
        String password = "123456";

        KeyStore keyStore = null;
        try {
            keyStore = KeyStore.getInstance("jks");
            keyStore.load(new FileInputStream(keyStoreFile), password.toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        exportCert(keyStore, alias, exportCertFile);
    }

    public static void exportCert(KeyStore keyStore, String alias, String exportFile) {
        try {
            Certificate certificate = keyStore.getCertificate(alias);
            if (certificate == null) {
                throw new RuntimeException("No such keystore item '" + alias + "'");
            }
            exportFile(certificate.getEncoded(), exportFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static KeyPair getKeyPair() throws Exception {
        String alias = "mpr";
        String keyStoreFile = System.getProperty("user.home") + File.separator + "mpr.keystore";
        String password = "123456";

        KeyStore keyStore = null;
        try {
            keyStore = KeyStore.getInstance("jks");
            keyStore.load(new FileInputStream(keyStoreFile), password.toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getKeyPair(keyStore, alias, password.toCharArray());
    }

    public static KeyPair getKeyPair(KeyStore keyStore, String alias, char[] password) throws Exception {
        Key key = keyStore.getKey(alias, password);
        if (key instanceof PrivateKey) {
            Certificate certificate = keyStore.getCertificate(alias);
            PublicKey publicKey = certificate.getPublicKey();
            KeyPair keyPair = new KeyPair(publicKey, (PrivateKey)key);
            return keyPair;
        }
        return null;
    }

    public static void exportFile(byte[] bytes, String exportFile) throws Exception {
        BASE64Encoder encoder = new BASE64Encoder();
        String encodedStr = encoder.encode(bytes);
        FileWriter fw = null;
        try {
            fw = new FileWriter(exportFile);
            fw.write(encodedStr);
        } finally {
            if (fw != null) {
                fw.close();
            }
        }
    }

    public static KeyPair generateKeyPair(int keySise) {
        KeyPairGenerator generator = null;
        try {
            generator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        generator.initialize(keySise);
        return generator.genKeyPair();
    }

    public static PrivateKey getPrivateKey(String priKey) {
        try {
            byte[] bytes = Base64Util.decode(priKey);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            return privateKey;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static PublicKey getPublicKey(String pubKey) throws IOException {
        byte[] bytes = Base64Util.decode(pubKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
        PublicKey publicKey;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e.getMessage());
        }
        return publicKey;
    }

    public static void main(String[] arsg) throws Exception {
        // 输出证书
        exportCert();

        // 获取KeyPair
        KeyPair keyPair = getKeyPair();

        // 输出PriKey
        String exportPriKey = System.getProperty("user.home") + File.separator + "priKey.txt";
        PrivateKey privateKey = keyPair.getPrivate();
        exportFile(privateKey.getEncoded(), exportPriKey);

        // 输出PubKey
        String exportPubKey = System.getProperty("user.home") + File.separator + "pubKey.txt";
        PublicKey publicKey = keyPair.getPublic();
        exportFile(publicKey.getEncoded(), exportPubKey);
    }

}
