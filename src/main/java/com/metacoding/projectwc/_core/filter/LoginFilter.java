package com.metacoding.projectwc._core.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginFilter implements Filter {
    //로그인 되어있을 시 메인으로 보냄
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        if (httpRequest.getUserPrincipal() != null) {
            httpResponse.sendRedirect("/main");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
