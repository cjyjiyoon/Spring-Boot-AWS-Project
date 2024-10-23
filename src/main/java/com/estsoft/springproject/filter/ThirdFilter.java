package com.estsoft.springproject.filter;

import jakarta.servlet.*;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class ThirdFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
        System.out.println("ThirdFilter.init()");
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("ThirdFilter.doFilter() request");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("requestURI: "+request.getRequestURI());

        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("ThirdFilter.doFilter() response");
    }

    @Override
    public void destroy() {
        System.out.println("ThirdFilter.destroy()");

//        Filter.super.destroy();
    }
}
