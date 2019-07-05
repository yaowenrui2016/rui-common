package indi.rui.common.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class MultiDataSourceConfiguration {
    /*--- DataSource ---*/

    @Bean("plainDataSource")
    @ConditionalOnProperty(prefix = "spring.datasource", name = {"name"})
    public DataSource plainDataSource(DataSourceProperties dataSourceProperties) {
        String name = dataSourceProperties.getName();
        String oldUrl = dataSourceProperties.getUrl();
        String url = oldUrl.replace("/" + name, "");
        dataSourceProperties.setUrl(url);
        log.info("Plain datasource url is [{}]", url);
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean("serviceDataSource")
    public DataSource serviceDataSource(DataSourceProperties dataSourceProperties) {
        log.info("Service datasource url is [{}]", dataSourceProperties.getUrl());
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }


    /*--- DataSourceInitializer ---*/

    /**
     * 建库脚本初始化
     * @param dataSource
     * @return
     */
    @Bean("plainDataSourceInitializer")
    @ConditionalOnBean(value = DataSource.class, name = "plainDataSource")
    public DataSourceInitializer plainDataSourceInitializer(
            @Qualifier("plainDataSource") DataSource dataSource) {
        return buildDataSourceInitializer("schema.sql", dataSource);
    }

    /**
     * 建表脚本初始化
     * @param dataSource
     * @return
     */
    @Bean("serviceDataSourceInitializer")
    @ConditionalOnBean(value = {DataSource.class, DataSourceInitializer.class},
            name = {"serviceDataSource", "plainDataSourceInitializer"})
    public DataSourceInitializer serviceDataSourceInitializer(
            @Qualifier("serviceDataSource") DataSource dataSource) {
        return buildDataSourceInitializer("table.sql", dataSource);
    }

    private DataSourceInitializer buildDataSourceInitializer(String scriptName, DataSource dataSource) {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource("classpath:/sql/" + scriptName);
        if (!resource.exists()) {
            return null;
        }
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator(resource);
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(populator);
        return initializer;
    }
}
