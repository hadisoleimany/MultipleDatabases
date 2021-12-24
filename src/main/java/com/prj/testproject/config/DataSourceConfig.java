package com.prj.testproject.config;

import com.prj.testproject.entity.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.prj.testproject.repository",
        transactionManagerRef = "transcationManager",
        entityManagerFactoryRef = "entityManager")
@EnableTransactionManagement
public class DataSourceConfig {

    @Autowired
    private Environment env;
    @Bean
    @Primary
    @Autowired
    public DataSource dataSource() {
        DbRouting routing = new DbRouting();
        routing.initDataSource(env );
        return routing;
    }

    @Bean(name = "entityManager")
    public LocalContainerEntityManagerFactoryBean managerFactoryBean(EntityManagerFactoryBuilder factoryBuilder){
        return factoryBuilder.dataSource(dataSource()).packages(Customer.class).build();

    }
    @Bean(name = "transcationManager")
    public JpaTransactionManager jpaTransactionManager(@Autowired @Qualifier("entityManager") LocalContainerEntityManagerFactoryBean factoryBean){
        return new JpaTransactionManager(factoryBean.getObject());
    }


}
