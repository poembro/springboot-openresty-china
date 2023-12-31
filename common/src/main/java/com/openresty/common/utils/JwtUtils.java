package com.openresty.common.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

/**
 * @Description: JWT工具类
 * @Author: poembro
 * @Date: 2020-09-02
 */
@Component
public class JwtUtils {
    public static String getHeader2(String name) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (requestAttributes == null) ? null : ((ServletRequestAttributes) requestAttributes).getRequest();
        return Objects.requireNonNull(request).getHeader(name);
    }

    public static String getHeader(String name) {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest r = sra.getRequest();
        return r.getHeader(name);
    }

    private static long expireTime = 259200000;
    private static String secretKey = "abcdefghijklmnopqrstuvwxyz";

    @Value("${token.secretKey}")
    public void setSecretKey(String secretKey) {
        JwtUtils.secretKey = secretKey;
    }

    @Value("${token.expireTime}")
    public void setExpireTime(long expireTime) {
        JwtUtils.expireTime = expireTime;
    }

    /**
     * 判断token是否存在
     *
     * @param token
     * @return
     */
    public static boolean judgeTokenIsExist(String token) {
        return token != null && !"".equals(token) && !"null".equals(token);
    }

    /**
     * 生成token
     *
     * @param subject
     * @return
     */
    public static String generateToken(String subject) {
        String jwt = Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return jwt;
    }

    /**
     * 生成带角色权限的token
     *
     * @param subject
     * @param authorities
     * @return
     */
    public static String generateToken(Integer uid,String subject, Collection<? extends GrantedAuthority> authorities) {
        StringBuilder sb = new StringBuilder();
        for (GrantedAuthority authority : authorities) {
            sb.append(authority.getAuthority()).append(",");
        }
        String jwt = Jwts.builder()
                // header
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS512")
                // payload 载荷
                .setSubject(subject) // 作者
                .claim("authorities", sb)
                .claim("uid", uid)
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return jwt;
    }

    /**
     * 生成自定义过期时间token
     *
     * @param subject
     * @param expireTime
     * @return
     */
    public static String generateToken(String subject, long expireTime) {
        String jwt = Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return jwt;
    }


    /**
     * 获取tokenBody同时校验token是否有效（无效则会抛出异常）
     *
     * @param token
     * @return
     */
    public static Claims getTokenBody(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token.replace("Bearer", "")).getBody();
        return claims;
    }
}
