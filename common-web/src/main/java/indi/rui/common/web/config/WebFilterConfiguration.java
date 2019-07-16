package indi.rui.common.web.config;

import indi.rui.common.web.security.AuthFilter;
import indi.rui.common.web.security.PrintFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ConditionalOnClass(Filter.class)
public class WebFilterConfiguration {
    @Bean
    public FilterRegistrationBean globalFilter() {
        PrintFilter filter = new PrintFilter();
        List<String> urlPattern = new ArrayList<>();
        urlPattern.add("/*");
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(filter);
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        filterRegistrationBean.setUrlPatterns(urlPattern);
        return filterRegistrationBean;
    }

    @Bean
    @ConditionalOnProperty(name = "docm.auth.token.priKey")
    public FilterRegistrationBean authFilter(@Value("${docm.auth.freeUrl:}")String[] freeUrl,
                                             @Value("${docm.auth.token.priKey}")String priKey) {
        AuthFilter filter = new AuthFilter(freeUrl, priKey);
        List<String> urlPattern = new ArrayList<>();
        urlPattern.add("/*");
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(filter);
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 10);
        filterRegistrationBean.setUrlPatterns(urlPattern);
        return filterRegistrationBean;
    }
}
