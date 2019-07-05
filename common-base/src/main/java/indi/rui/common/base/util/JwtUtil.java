package indi.rui.common.base.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

import java.security.KeyPair;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: study_20180903
 * @description: Json Web Token 工具类
 * @author: Yaowr
 * @create: 2018-09-27 08:45
 **/
@Slf4j
public class JwtUtil {
    /**
     * 创建jwt或jws，当privateKey不为空则创建jws，否则创建jwt
     * @param issuer
     * @param subject
     * @param audience
     * @param expireTime
     * @param claims
     * @param privateKey
     * @return
     */
    public static String build(String issuer, String subject, String audience,
                               long expireTime, Map<String, Object> claims, String privateKey) {
        JwtBuilder jwtBuilder = Jwts.builder();
        if (!StringUtil.isEmpty(privateKey)) {
            jwtBuilder.signWith(KeyUtil.getPrivateKey(privateKey));
        }
        Date issueDate = new Date();
        Date expDate = null;
        if (expireTime > 0) {
            expDate = new Date(issueDate.getTime() + expireTime);
        }
        return jwtBuilder.setSubject(subject)
                .setIssuer(issuer)
                .setIssuedAt(issueDate)
                .setAudience(audience)
                .setExpiration(expDate)
                .addClaims(claims)
                .compact();
    }

    /**
     * 解析jwt
     * @param token
     * @param privateKey
     * @return
     */
    public static Claims parse(String token, String privateKey) {
        JwtParser jwtParser = Jwts.parser();
        Claims claims;
        if (!jwtParser.isSigned(token)) {
            claims = jwtParser.parseClaimsJwt(token).getBody(); // 未加密的 叫做Jwt
        } else {
            claims = jwtParser.setSigningKey(KeyUtil
                    .getPrivateKey(privateKey))
                    .parseClaimsJws(token).getBody();           // 加密的（signature）叫做Jws
        }
        return claims;
    }



    public static void main(String[] args) {
        KeyPair keyPair = KeyUtil.generateKeyPair(2048);
        String priKey = Base64Util.encode(keyPair.getPrivate().getEncoded());
        Map<String, Object> claims = new HashMap();
        claims.put("accountId", "20190001");
        String token = build("yaowr", "登录令牌", "jk", -1, claims, priKey);
//        String token = build("yaowr", "登录令牌", "jk", claims, null);
        log.info("jwt：\n" + token);
        try {
            Thread.sleep(1100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        String token = "eyJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJBMEYzMkEzNzJGRDU0MUQ0QTYwN0NGQzk5OThFOEU3OSIsInN1YiI6IuaOpeWPo-mqjOivgSIsImF1ZGllbmNlIjoiQTBGMzJBMzcyRkQ1NDFENEE2MDdDRkM5OTk4RThFNzkiLCJpc3MiOiJhNmZlZDg2MTFjNzA0ZWFhOTEyNjZkODVmM2RjOTg1NyIsInBhcmFtc01ENSI6IkI0ODRFQTAxRDRBNTRDODRFQThCNTU0QzVGNTQ2QTk0IiwiaWF0IjoxNTMzNjk4MTA0LCJpc3N1ZXIiOiJhNmZlZDg2MTFjNzA0ZWFhOTEyNjZkODVmM2RjOTg1NyIsImp0aSI6IjNlMWI4ZjViYTlhYjQ4MDVhNDJiNDFlN2YyZjY1YWM5In0.aZj-ZnVYMGxZcSFvVgCDBv2N1uDbH760tITSp9Rw4741qCMjbcUFpYYkWm9cnG53g7mA37vq_mapbhTio8s4Lo9aiZZfpM7VeObmqsniEm_Gu66E8w09NNK_Y2p_N4lwcM41OvkGfiNjhVeaMRtHzfuCl2MjDl-QZFQEKb5oI5M";

        log.info("-----------------");
        Claims claimsP = parse(token, priKey);
//        Claims claimsP = parse(token, null);
        for (Map.Entry<String, Object> entry : claimsP.entrySet()) {
            log.info(entry.getKey() + " = " + entry.getValue());
        }

    }
}
