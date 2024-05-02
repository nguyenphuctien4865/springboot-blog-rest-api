package com.nptien.blog.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {


    // Called whenever an exception is thrown due to an unathenticated user 
    // trying to access a resource
    @Override
    public void commence(HttpServletRequest request, 
            HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        // TODO Auto-generated method stub
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");//authException.getMessage());
    }
    
}
