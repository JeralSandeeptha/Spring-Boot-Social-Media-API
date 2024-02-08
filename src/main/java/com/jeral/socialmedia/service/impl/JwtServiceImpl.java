package com.jeral.socialmedia.service.impl;

import com.jeral.socialmedia.model.Admin;
import com.jeral.socialmedia.model.User;
import com.jeral.socialmedia.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {
    @Override
    public String generateToken(Object user, String username) {
        Map<String, Object> claims = new HashMap<>();
        if (user instanceof Admin) {
            Admin admin = (Admin) user;
            claims.put("userId", admin.getId());
            claims.put("username", admin.getUsername());
            claims.put("role", admin.getRole());
        } else {
            User normalUser = (User) user;
            claims.put("userId", normalUser.getId());
            claims.put("username", normalUser.getUsername());
            claims.put("role", normalUser.getRole());
        }
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String username) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode("462ef8b9e205775f789b24a7645026008f33aec3439b00f02ed77921728dc989");
        return Keys.hmacShaKeyFor(keyBytes);
    }
}