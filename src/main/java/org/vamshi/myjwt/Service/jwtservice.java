package org.vamshi.myjwt.Service;

import org.springframework.security.core.userdetails.UserDetails;

public interface jwtservice {
    public String GenerateToken(UserDetails userDetails);
    public String extractUserName(String token);
    public boolean isTokenValid(String token,UserDetails userDetails);

    }
