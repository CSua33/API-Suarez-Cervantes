package com.practice.myapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.beans.ConstructorProperties;


@Configuration
public class DataBaseConfig {
    @Bean
    @ConfigurationProperties(prefix = "datasource.my-connection")
    public DataSource crudDataSource (){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate crudJdbcTemplate(DataSource crudDataSource){
        var jdbcTemplate = new JdbcTemplate(crudDataSource);
        return jdbcTemplate;
    }

    @Bean
    public NamedParameterJdbcTemplate crudNamedParameterJdbcTemplate(JdbcTemplate crudJdbcTemplate){
        return new NamedParameterJdbcTemplate(crudJdbcTemplate);
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
