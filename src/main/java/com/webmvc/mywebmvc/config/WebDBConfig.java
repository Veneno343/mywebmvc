package com.webmvc.mywebmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.naming.Name;
import javax.sql.DataSource;

@Configuration
public class WebDBConfig {

    /**
     * Konfigurasi datasource untuk database yang akan digunakan
     * @return
     */

    @Bean
    public DataSource datasource() {
        return new EmbeddedDatabaseBuilder()
                .setName("testDB")
                .setType(EmbeddedDatabaseType.H2)
                .addDefaultScripts()
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .build();
    }


    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate() {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource());

        return namedParameterJdbcTemplate;
    }
}
