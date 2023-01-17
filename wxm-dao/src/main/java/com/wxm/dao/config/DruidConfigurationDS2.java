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
@MapperScan(basePackages = "com.wxm.dao.mapper.ds2",sqlSessionFactoryRef = "sqlSessionFactoryDS2")
public class DruidConfigurationDS2 {

    @Bean("dataSourceDS2")
    @ConfigurationProperties(prefix = "spring.datasource.ds2")//application-druid.propeties 中的spring。datasource.ds2开头的所有的属性
    public DataSource dataSourceDS2(){
        return DruidDataSourceBuilder.create().build();//创建druid数据源
    }


    @Bean(name = "transactionManagerDS2")
    public DataSourceTransactionManager transactionManagerDS2() {
        return new DataSourceTransactionManager(dataSourceDS2());
    }

    @Bean(name = "sqlSessionFactoryDS2")
    public SqlSessionFactory sqlSessionFactoryDS2(@Qualifier("dataSourceDS2") DataSource  dataSourceDS2)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSourceDS2);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/xml/ds2/*Mapper.xml"));
        return sessionFactory.getObject();
    }
}
