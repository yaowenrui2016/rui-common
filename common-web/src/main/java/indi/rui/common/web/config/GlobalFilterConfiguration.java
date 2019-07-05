package indi.rui.common.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ConditionalOnClass(Filter.class)
public class GlobalFilterConfiguration {
    @Bean
    public FilterRegistrationBean globalFilter() {
        GlobalFilter filter = new GlobalFilter();
        List<String> urlPattern = new ArrayList<>();
        urlPattern.add("/*");
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(filter);
        filterRegistrationBean.setUrlPatterns(urlPattern);
        return filterRegistrationBean;
    }

    @Slf4j
    static class GlobalFilter implements Filter {
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
            log.info("↓↓↓↓ Request from [{}] >>> {} ", remoteHost, queryLinge);
            long start = System.currentTimeMillis();
            filterChain.doFilter(request, response);
            long end = System.currentTimeMillis();
            log.info("↑↑↑↑ Finished request with {}s ", format.format((end - start) / 1000d));
        }

        @Override
        public void destroy() {

        }
    }
}
