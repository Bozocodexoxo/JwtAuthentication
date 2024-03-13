package org.vamshi.myjwt.Service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface jwtservice {
    public String GenerateToken(UserDetails userDetails);
    public String extractUserName(String token);
    public boolean isTokenValid(String token,UserDetails userDetails);
    String generateRefreshtok(Map<String,Object> extraclaims, UserDetails userDetails);
    }
