package com.jeral.socialmedia.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Component
public class JwtDecoder {

    public DecodedJWT decode(String token) throws InvalidKeySpecException, NoSuchAlgorithmException {
        String privateKeyBase64 = "CfrLyPb9+apJhTQ/aafLWQ==";
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyBase64);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        //Algorithm algorithm = Algorithm.ECDSA256((java.security.interfaces.ECPublicKey) privateKey, null);
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256("462ef8b9e205775f789b24a7645026008f33aec3439b00f02ed77921728dc989"))
                .build()
                .verify(token);

        // JWT verification successful
        System.out.println("JWT verified: " + jwt);

        // Accessing JWT claims
        String subject = jwt.getSubject();
        System.out.println("Subject: " + subject);

        return jwt;

    }
}
