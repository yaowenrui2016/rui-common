package indi.rui.common.web.security;

import indi.rui.common.base.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;

@Slf4j
public class AuthFilter implements Filter {
    /**
     * 开放的url
     */
    private String[] freeUrls;

    /**
     * token私钥
     */
    private String priKey;

    public AuthFilter(String[] freeUrls, String priKey) {
        this.freeUrls = freeUrls;
        this.priKey = priKey;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        String method = request.getMethod();
        if (shouldAuth(uri) && !"OPTIONS".equals(method)) {
            String token = request.getHeader("X-AUTH-TOKEN");
            try {
                Claims claims = JwtUtil.parse(token, priKey);
                String id = (String) claims.get("id");
                log.info("XXX Request token is valid [{}]", id);
                filterChain.doFilter(request, response);
            } catch (Exception e) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

    protected boolean shouldAuth(String url) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        if (freeUrls != null && freeUrls.length > 0) {
            for (String freeUrl : freeUrls) {
                if (antPathMatcher.match(freeUrl, url)) {
                    return false;
                }
            }
        }
        return true;
    }
}