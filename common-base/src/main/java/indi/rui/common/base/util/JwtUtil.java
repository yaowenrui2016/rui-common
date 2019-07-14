package indi.rui.common.base.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
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
     * @param priKey
     * @return
     */
    public static String build(String issuer, String subject, String audience,
                               long expireTime, Map<String, Object> claims,
                               String priKey) throws Exception {
        JwtBuilder jwtBuilder = Jwts.builder();
        if (!StringUtil.isEmpty(priKey)) {
            jwtBuilder.signWith(KeyUtil.buildRsaPriKey(priKey));
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
     * @param priKey
     * @return
     */
    public static Claims parse(String token, String priKey) throws Exception {
        JwtParser jwtParser = Jwts.parser();
        Claims claims;
        if (!jwtParser.isSigned(token)) {
            claims = jwtParser.parseClaimsJwt(token).getBody(); // 未加密的 叫做Jwt
        } else {
            claims = jwtParser.setSigningKey(KeyUtil
                    .buildRsaPriKey(priKey))
                    .parseClaimsJws(token).getBody();           // 加密的（signature）叫做Jws
        }
        return claims;
    }
}
