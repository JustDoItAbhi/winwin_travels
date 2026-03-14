package com.auth_api.auth_api.jwt_utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService{

    @Value("${application.jwt.secret}")
    private String secretKey;

    @Value("${application.jwt.expiration}")
    private long jwtExpiration;


    @Override
    public String generateToken(UserDetails userDetails) {

        return generateToken(new HashMap<>(),userDetails);
    }

    @Override
    public String generateToken(Map<String, Object> claims, UserDetails userDetails) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+jwtExpiration))
                .signWith(sigingKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    private Key sigingKey(){
        byte[] keybyts= Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keybyts);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String email=decodeUserName(token);
        return (email.equals(userDetails.getUsername())&& !isTokenExpire(token));
    }

    @Override
    public String decodeUserName(String token) {
        return decodeClaim(token,(Claims->Claims.getSubject()));
    }

    @Override
    public <T> T decodeClaim(String token, Function<Claims, T> claimsTFunction) {
        Claims claims=extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(sigingKey())
                .parseClaimsJws(token)
                .getBody();
    }


    private boolean isTokenExpire(String token){
        return checkExpiry(token).before(new Date());
    }
    private Date checkExpiry(String token){
        return decodeClaim(token,Claims::getExpiration);
    }

}
