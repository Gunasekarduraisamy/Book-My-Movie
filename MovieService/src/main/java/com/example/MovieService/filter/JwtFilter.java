package com.example.MovieService.filter;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JwtFilter extends GenericFilter {
//    @Override // no override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String authHeader = request.getHeader("Authorization");
        if(request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request,response);
        }
        else if(authHeader == null || !authHeader.startsWith("Bearer "))
        {
            throw new ServletException("Missing or Invalid Token");
        }
        String token = authHeader.substring(7);//Bearer => 6+1 = 7, since token begins with Bearer
        Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
        System.out.println(claims + " line28");
        //String userEmail=Jwts.parser().setSigningKey("krishna").parseClaimsJws(token).getBody().getSubject();
        request.setAttribute("claims",claims);
        System.out.println(claims + " line 29");
       // request.setAttribute("userEmail",userEmail);
        filterChain.doFilter(request,response);
    }
}

