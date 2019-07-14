package indi.rui.common.base.util;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Encoder;

import java.io.*;
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
    public static final int KEY_SIZE = 2048;

    public static KeyPair genRsaKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(KEY_SIZE);
        return generator.genKeyPair();
    }

    public static PrivateKey buildRsaPriKey(String priKey) throws Exception {
        byte[] bytes = Base64Util.decode(priKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
        return KeyFactory.getInstance("RSA").generatePrivate(keySpec);
    }

    public static PublicKey buildRsaPubKey(String pubKey) throws Exception {
        byte[] bytes = Base64Util.decode(pubKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
        return KeyFactory.getInstance("RSA").generatePublic(keySpec);
    }


    public static void main(String[] args) throws Exception {
        KeyPair keyPair = KeyUtil.genRsaKeyPair();
        PrivateKey priKey = keyPair.getPrivate();
//        PublicKey pubKey = keyPair.getPublic();
        String priKeyStr = Base64Util.encode(priKey.getEncoded());
        log.info("\n{}", priKeyStr);

//        String path = "./tmp/keyfile/";
//        String fileName = "priKey";
//        FileUtil.save(new ByteArrayInputStream(priKeyStr.getBytes()), path, fileName);
//        Thread.sleep(2000);
//
//        FileInputStream in = new FileInputStream(new File(path, fileName));
//        byte[] buf = new byte[in.available()];
//        in.read(buf);
//        String priKeyStr2 = new String(buf);
//        log.info("\n{}\n", priKeyStr2);
//
//        log.info("{}", priKeyStr.equals(priKeyStr2));
    }
}
