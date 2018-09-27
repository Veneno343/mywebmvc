package com.webmvc.mywebmvc.config;

import com.webmvc.mywebmvc.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = {"com.webmvc.mywebmvc.repository"})
public class WebJPAConfig {

    @Autowired
    DataSource dataSource;

    /*
     * Mengkonfigurasi Factory JPA dengan vendor Hibernate
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean managerFactory() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setGenerateDdl(false);

        Properties jpaProps = new Properties();
        jpaProps.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(adapter);
        factory.setPackagesToScan("com.webmvc.mywebmvc");
        factory.setDataSource(dataSource);
        factory.setJpaProperties(jpaProps);


        return factory;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return managerFactory().getObject();
    }

    /*
     * Mengaktifkan fungsi Transaction Data pada Database
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactory());

        return manager;
    }
}
