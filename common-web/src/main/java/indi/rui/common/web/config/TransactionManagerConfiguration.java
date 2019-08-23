package indi.rui.common.web.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class TransactionManagerConfiguration {
//    @Bean
//    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("serviceDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
}
