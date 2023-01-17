package com.wxm.dao.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application-druid.yml")
@MapperScan(basePackages = "com.wxm.dao.mapper.ds1",sqlSessionFactoryRef = "sqlSessionFactoryDS1")
public class DruidConfigurationDS1 {

    @Bean("dataSourceDS1")
    @Primary//优先使用
    @ConfigurationProperties(prefix = "spring.datasource.ds1")//application-druid.propeties 中的spring。datasource.ds1开头的所有的属性
    public DataSource dataSourceDS1(){
        return DruidDataSourceBuilder.create().build();//创建druid数据源
    }


    @Bean(name = "transactionManagerDS1")
    @Primary
    public DataSourceTransactionManager transactionManagerDS1() {
        return new DataSourceTransactionManager(dataSourceDS1());
    }

    @Bean(name = "sqlSessionFactoryDS1")
    @Primary
    public SqlSessionFactory sqlSessionFactoryDS1(@Qualifier("dataSourceDS1") DataSource  dataSourceDS1)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSourceDS1);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/xml/ds1/*Mapper.xml"));
        return sessionFactory.getObject();
    }
}
