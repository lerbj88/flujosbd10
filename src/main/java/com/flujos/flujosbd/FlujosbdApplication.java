package com.flujos.flujosbd;

import com.flujos.flujosbd.routing.MyRoutingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JndiConnectionFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication

// Disable Auto Config DataSource & DataSourceTransactionManager
@EnableAutoConfiguration(exclude = { JndiConnectionFactoryAutoConfiguration.class,DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,JpaRepositoriesAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class})

// Load to Environment
// (@see resources/datasource-cfg.properties).
@PropertySources({ @PropertySource("classpath:datasource-cfg.properties") })
public class FlujosbdApplication {

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(FlujosbdApplication.class, args);
    }

    // Returns Routing DataSource (MyRoutingDataSource)
    @Autowired
    @Bean(name = "dataSource")
    public DataSource getDataSource(DataSource dataSource1, DataSource dataSource2, DataSource dataSource3) {

        System.out.println("## Create DataSource from dataSource1 , dataSource2 & dataSource3");

        MyRoutingDataSource dataSource = new MyRoutingDataSource();

        dataSource.initDataSources(dataSource1, dataSource2, dataSource3);

        return dataSource;
    }

    @Bean(name = "dataSource1")
    public DataSource getDataSource1() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        // See: datasouce-cfg.properties
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name.1"));
        dataSource.setUrl(env.getProperty("spring.datasource.url.1"));
        dataSource.setUsername(env.getProperty("spring.datasource.username.1"));
        dataSource.setPassword(env.getProperty("spring.datasource.password.1"));


        System.out.println("## DataSource1: " + dataSource);
        return dataSource;
    }

    @Bean(name = "dataSource2")
    public DataSource getDataSource2() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        // See: datasouce-cfg.properties
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name.2"));
        dataSource.setUrl(env.getProperty("spring.datasource.url.2"));
        dataSource.setUsername(env.getProperty("spring.datasource.username.2"));
        dataSource.setPassword(env.getProperty("spring.datasource.password.2"));

        System.out.println("## DataSource2: " + dataSource);

        return dataSource;
    }

    @Bean(name = "dataSource3")
    public DataSource getDataSource3() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        // See: datasouce-cfg.properties
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name.3"));
        dataSource.setUrl(env.getProperty("spring.datasource.url.3"));
        dataSource.setUsername(env.getProperty("spring.datasource.username.3"));
        dataSource.setPassword(env.getProperty("spring.datasource.password.3"));

        System.out.println("## DataSource3: " + dataSource);

        return dataSource;
    }

    @Autowired
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager getTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager txManager = new DataSourceTransactionManager();

        txManager.setDataSource(dataSource);

        return txManager;
    }



}

