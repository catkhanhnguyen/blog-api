package com.blog.demo.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.blog.demo.custom.exception.CustomException;
import com.blog.demo.data.response.ErrorResponse;
import com.blog.demo.service.UserInfoService;
import com.blog.demo.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtFilter extends OncePerRequestFilter{

    private final String[] ignoreCsrfAntMatchers = {
        "/login"
    };

    public String[] getIgnoreCsrfAntMatchers(){
        return ignoreCsrfAntMatchers;
    }

    @Autowired
    private UserInfoService userInfoService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws java.io.IOException, ServletException  {
        String token = userInfoService.resolveToken(httpServletRequest);
        try {
            if(isPyPassToken(httpServletRequest)){
                filterChain.doFilter(httpServletRequest, httpServletResponse);
                return;
            }

            if(token == null) throw new CustomException(Constants.MESSAGE_BAD_CREDENTIALS, HttpStatus.UNAUTHORIZED);

            String username = userInfoService.extractUsername(token);
            UserDetails userDetails = userInfoService.loadUserByUsername(username);
            if (userInfoService.validateToken(token, userDetails)) {
                Authentication auth = userInfoService.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        
        catch (CustomException e) {
            SecurityContextHolder.clearContext();
            sendErrorResponse(httpServletResponse, e);
            return;
        }

        catch (Exception e) {
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private boolean isPyPassToken(HttpServletRequest request) {
        return Arrays.binarySearch(getIgnoreCsrfAntMatchers(), request.getServletPath()) >= 0;
    }

    private void sendErrorResponse(HttpServletResponse httpServletResponse, CustomException e) throws java.io.IOException {
        httpServletResponse.setStatus(e.getHttpStatus().value());
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");

        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        String errorResponseJson = new ObjectMapper().writeValueAsString(errorResponse);
        httpServletResponse.getWriter().write(errorResponseJson);
        httpServletResponse.getWriter().flush();
    }
}
