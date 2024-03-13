package org.vamshi.myjwt.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.vamshi.myjwt.Service.Userservice;
import org.vamshi.myjwt.Service.jwtservice;


import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
   private jwtservice jwtService;
   private Userservice userservice;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
var Authenticationhead=request.getHeader("Authenticate");
final String jwt;
final String useremail;
if(StringUtils.isEmpty(Authenticationhead)||!StringUtils.startsWith(Authenticationhead,"Bearer")){
return;


    }
    jwt=Authenticationhead.substring(7);
useremail=jwtService.extractUserName(Authenticationhead);
if(StringUtils.isNotEmpty(Authenticationhead) && SecurityContextHolder.getContext().getAuthentication()==null){
    UserDetails userDetails=userservice.userDetailsService().loadUserByUsername(useremail);

if(jwtService.isTokenValid(jwt,userDetails)) {
    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities()
    );
    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    securityContext.setAuthentication(token);
    SecurityContextHolder.setContext(securityContext);

}}
filterChain.doFilter(request,response);
    }}