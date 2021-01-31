package com.nks.quotejava2.configurations;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "sqlite3EntityManagerFactory",
        transactionManagerRef = "sqlite3TransactionManager",
        basePackages = {"com.nks.quotejava2.repositories.sqlite3"})
public class Sqlite3Config {

    @Bean(name = "Sqlite3DataSourceProperties")
    @ConfigurationProperties("spring.datasource-sqlite3")
    public DataSourceProperties sqlite3DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "sqlite3DataSource")
    @ConfigurationProperties("spring.datasource-sqlite3.configuration")
    public DataSource secondaryDataSource(@Qualifier("Sqlite3DataSourceProperties") DataSourceProperties sqlite3DataSourceProperties) {
        return sqlite3DataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean(name = "sqlite3EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(
            EntityManagerFactoryBuilder secondaryEntityManagerFactoryBuilder, @Qualifier("sqlite3DataSource") DataSource sqlite3DataSource) {

        Map<String, String> sqlite3JpaProperties = new HashMap<>();
        sqlite3JpaProperties.put("hibernate.dialect", "com.nks.quotejava2.configurations.SQLiteDialect");
//        sqlite3JpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");

        return secondaryEntityManagerFactoryBuilder
                .dataSource(sqlite3DataSource)
                .packages("com.nks.quotejava2.model.sqlite3")
                .persistenceUnit("secondaryDataSource")
                .properties(sqlite3JpaProperties)
                .build();
    }

    @Bean(name = "sqlite3TransactionManager")
    public PlatformTransactionManager secondaryTransactionManager(
            @Qualifier("sqlite3EntityManagerFactory") EntityManagerFactory sqlite3EntityManagerFactory) {

        return new JpaTransactionManager(sqlite3EntityManagerFactory);
    }
}
