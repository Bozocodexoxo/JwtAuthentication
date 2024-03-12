package org.vamshi.myjwt.Service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class jwtserviceimpl {
    public String GenerateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    private  <T> T extractclaims(String token, Function<Claims,T>claimReslover){
        final Claims claims=extractAllClaims(token);
        return claimReslover.apply(claims);

    }
    public String extractUserName(String token){
        return extractclaims(token,Claims::getSubject);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    public Key getSignKey(){
        byte[] key= Decoders.BASE64.decode("");
        return Keys.hmacShaKeyFor(key);
    }
    private boolean isTokenValid(String token,UserDetails userDetails){
        final String username=extractUserName(token);
        return (username.equals(userDetails.getUsername())&& isTokenexpired(token));
    }
    private boolean isTokenexpired(String Token){
return extractclaims(Token,Claims::getExpiration).before(new Date());
    }
}
