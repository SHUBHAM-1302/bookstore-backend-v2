package com.rith.id.configuration;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class SimpleCorsFilter implements Filter {


    @Value("#{'${application.web.allowed-cors}'.split('\\s*,\\s*')}")
    private List<String> allowedCorsOrigins;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        for(String allowedCors:allowedCorsOrigins)
        {
            if(request.getHeader("Origin")!=null && request.getHeader("Origin").equals(allowedCors))
            {
                response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
                response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, PATCH");
                response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept");
                break;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
