package com.example.onlineenergyutilityplatform.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Component
public class TokenGenerator implements Serializable {

    public static final int SECONDS = 1000;

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.validity}")
    private long tokenValidity;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return doGenerateTokenWithExpirationDate(userDetails.getUsername());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String user = getUsernameFromToken(token);
        return user.equals(userDetails.getUsername()) && tokenDidNotExpire(token);
    }

    private Claims getAllClaimsFromToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (MalformedJwtException e) {
            throw new UnableToParseAuthTokenException();
        }
    }

    private Boolean tokenDidNotExpire(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return !expiration.before(new Date());
    }

    private String doGenerateTokenWithExpirationDate(String subject) {
        Date expirationDate = expirationDate(tokenValidity);
        return doGenerateToken(subject, expirationDate);
    }

    private String doGenerateToken(String subject, Date expirationDate) {
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expirationDate)
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKeySpec getSigningKey() {
        return new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());
    }

    private Date expirationDate(long validity) {
        return new Date(System.currentTimeMillis() + validity * SECONDS);
    }

    private static class UnableToParseAuthTokenException extends RuntimeException {
    }
}
