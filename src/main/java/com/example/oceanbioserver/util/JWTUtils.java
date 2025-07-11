package com.example.oceanbioserver.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class JWTUtils {

    private static final long expire_time = 1000 * 60 * 60 * 24;
    private static final String security_key = "1q2w3e4r5t!";

    public static String createToken(String username) {
        long cru_time = System.currentTimeMillis();
        Date date = new Date(cru_time + expire_time);
        try {
            Algorithm algorithm = Algorithm.HMAC256(security_key);
            return JWT.create()
                    .withClaim("username", username)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            log.error("创建Token失败", e);
            return null;
        }
    }

    public static boolean Verify(String token) throws SignatureVerificationException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(security_key);
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            jwtVerifier.verify(token);
            return true;
        } catch (JWTDecodeException e) {
            log.info("Token解码错误");
            return false;
        } catch (JWTVerificationException e) {
            log.error("Token验证失败", e);
            return false;
        }
    }

    public static boolean Verify(String token, String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(security_key);
            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            jwtVerifier.verify(token);
            return true;
        } catch (JWTDecodeException e) {
            log.info("Token解码错误");
            return false;
        } catch (JWTVerificationException e) {
            log.error("Token验证失败", e);
            return false;
        }
    }

    public static String getTokenUserInfo(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            log.error("获取Token用户信息失败", e);
            return null;
        }
    }

    public static Long getExpireTime(String token) {
        try {
            DecodedJWT decode = JWT.decode(token);
            Claim exp = decode.getClaim("exp");
            return exp.asDate().getTime();
        } catch (JWTDecodeException e) {
            log.error("获取Token过期时间失败", e);
            return null;
        }
    }

    public static boolean isJwtExpired(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            Date expiration = jwt.getExpiresAt();
            return expiration != null && expiration.before(new Date());
        } catch (JWTVerificationException e) {
            log.error("Token验证异常", e);
            return true;
        }
    }
}