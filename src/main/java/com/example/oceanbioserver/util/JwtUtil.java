package com.example.oceanbioserver.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.oceanbioserver.config.JwtConfig;
import com.example.oceanbioserver.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JWT工具类（使用com.auth0.java-jwt实现）
 */
@Component
public class JwtUtil {

    @Autowired
    private JwtConfig jwtConfig;

    /**
     * 生成token
     * @param user 用户信息
     * @return token
     */
    public String generateToken(User user) {
        return JWT.create()
                .withSubject(user.getLoginName())
                .withClaim("id", user.getId())
                .withClaim("loginName", user.getLoginName())
                .withClaim("name", user.getName())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtConfig.getExpire() * 1000))
                .sign(Algorithm.HMAC512(jwtConfig.getSecret()));
    }

    /**
     * 验证并解析token
     * @param token token
     * @return 解析后的DecodedJWT对象
     */
    public DecodedJWT verifyToken(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512(jwtConfig.getSecret()))
                .build();
        return verifier.verify(token);
    }

    /**
     * 验证token是否过期
     * @param token token
     * @return 是否过期
     */
    public boolean isTokenExpired(String token) {
        try {
            DecodedJWT decodedJWT = verifyToken(token);
            return decodedJWT.getExpiresAt().before(new Date());
        } catch (JWTVerificationException e) {
            return true;
        }
    }
}