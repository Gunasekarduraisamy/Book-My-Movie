package com.example.userAuthentication.security;

import com.example.userAuthentication.domain.UserData;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTSecurityTokenGeneratorImpl implements SecurityTokenGenerator {
    @Override
    public String createToken(UserData userData) {
        Map<String, Object> claims = new HashMap<>();  // name should be claims
        claims.put("emailId", userData.getUserEmail());
        return generateToken(claims, userData.getUserEmail());
    }

    public String generateToken(Map<String, Object> claims, String subject) {
        String jwtToken = Jwts.builder()
                .setIssuer("MovieApp")
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey")
                .compact();
        return jwtToken;
    }
}
