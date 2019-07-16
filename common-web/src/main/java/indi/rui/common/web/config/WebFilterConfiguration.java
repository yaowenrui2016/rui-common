package indi.rui.common.web.config;

import indi.rui.common.web.security.PrintFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
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
}
