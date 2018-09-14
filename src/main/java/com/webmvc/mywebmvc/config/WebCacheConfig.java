package com.webmvc.mywebmvc.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
@ComponentScan("com.webmvc.mywebmvc")
public class WebCacheConfig {

    @Bean
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(factoryBean().getObject());
    }

    @Bean
    public EhCacheManagerFactoryBean factoryBean() {
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
        factoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        factoryBean.setShared(true);

        return factoryBean;
    }

}
