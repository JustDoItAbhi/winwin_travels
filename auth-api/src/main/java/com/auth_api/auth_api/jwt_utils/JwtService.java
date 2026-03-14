package com.auth_api.auth_api.jwt_utils;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    String generateToken(UserDetails userDetails);
    String generateToken(Map<String ,Object>claims,UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
    String decodeUserName(String token);
    <T> T decodeClaim(String token, Function<Claims,T>claimsTFunction);

}
