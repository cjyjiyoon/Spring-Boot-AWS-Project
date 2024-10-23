package com.estsoft.springproject.filter;

import jakarta.servlet.*;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class SecondFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
        System.out.println("SecondFilter.init()");
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("SecondFilter.doFilter() request");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("requestURI: "+request.getRequestURI());

        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("SecondFilter.doFilter() response");
    }



    @Override
    public void destroy() {
//        Filter.super.destroy();
        System.out.println("SecondFilter.destroy()");
    }
}
