package com.webmvc.mywebmvc.config;

import org.h2.jdbc.JdbcConnection;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.naming.Name;
import javax.sql.DataSource;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

@Configuration
public class WebDBConfig {

    /**
     * Konfigurasi datasource untuk database yang akan digunakan
     * @return
     */
    @Bean
    public DataSource datasource() {
        JdbcDataSource dataSource = new JdbcDataSource();

        dataSource.setURL("jdbc:h2:tcp://localhost/~/testdb");
        dataSource.setUser("sa");
        dataSource.setPassword("");

        return dataSource;

        /* Untuk Embedded Database
        return new EmbeddedDatabaseBuilder()
                .setName("testdb;MODE=MySQL;DB_CLOSE_ON_EXIT=false")
                .setType(H2)
                .setScriptEncoding("UTF-8")
                .addScripts("schema.sql","data.sql")
                .ignoreFailedDrops(true)
                .build();
        */
    }
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
