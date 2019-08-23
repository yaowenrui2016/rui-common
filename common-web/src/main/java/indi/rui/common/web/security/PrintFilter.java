package indi.rui.common.web.security;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;

@Slf4j
public class PrintFilter implements Filter {
    private DecimalFormat format = new DecimalFormat("#.##");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String remoteHost = request.getRemoteHost();
        String queryString = request.getQueryString();
        String queryLinge =  method + " " + uri + (queryString == null ? "": ("?" + queryString));
        log.debug("↓↓↓↓ Request from [{}] >>> {} ", remoteHost, queryLinge);
        long start = System.currentTimeMillis();
        filterChain.doFilter(request, response);
        long end = System.currentTimeMillis();
        log.debug("↑↑↑↑ Finished request with status [{}] use {}s ",response.getStatus(), format.format((end - start) / 1000d));
    }

    @Override
    public void destroy() {

    }
}