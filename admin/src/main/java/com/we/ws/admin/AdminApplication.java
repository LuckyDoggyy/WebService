package com.we.ws.admin;

import com.we.ws.admin.interceptor.AuthorizeInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-02-01
 */
@SpringBootApplication
@EnableAutoConfiguration
@MapperScan(basePackages = "com.we.ws.admin.mapper")
public class AdminApplication extends WebMvcConfigurerAdapter {

    @Bean(name = "AdminTransactionManager")
    public DataSourceTransactionManager getBPTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public AuthorizeInterceptor authorizeInterceptor(){
        return new AuthorizeInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizeInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/in","/logout","/checklogin");
    }

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
