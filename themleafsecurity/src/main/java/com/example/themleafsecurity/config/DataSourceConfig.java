package com.example.themleafsecurity.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;

/**
 * @author WangKun
 * @create 2018-08-09
 * @desc
 **/
@EnableTransactionManagement
@Import(PropertiesConfig.class)
public class DataSourceConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);

    @Autowired
    private PropertiesConfig propertiesConfig;

    @Bean(name = "druidDataSource")
    public DruidDataSource druidDataSource() {
        DruidDataSource ds = new DruidDataSource();
        ds.setName("CBoard Meta Data");
        ds.setUrl(propertiesConfig.getUrl());
        ds.setUsername(propertiesConfig.getUsername());
        ds.setPassword(propertiesConfig.getPassword());
        ds.setInitialSize(0);
        ds.setMaxActive(20);
        ds.setMinIdle(0);
        ds.setMaxWait(60000);
//        ds.setValidationQuery("");
        ds.setTestOnBorrow(false);
        ds.setTestOnReturn(false);
        ds.setTestWhileIdle(true);
        ds.setTimeBetweenEvictionRunsMillis(60000);
        ds.setMinEvictableIdleTimeMillis(25200000);
        ds.setRemoveAbandoned(true);
        ds.setRemoveAbandonedTimeout(1800);
        ds.setLogAbandoned(true);
        try {
            ds.setFilters("mergeStat");
//            ds.setFilters("mergeStat,log4j");
        } catch (SQLException e) {
            LOGGER.error("dataSourceConfig init error", e);
        }
        LOGGER.info("dataSourceConfig init ok");
        return ds;
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(DruidDataSource druidDataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(druidDataSource);
        return dataSourceTransactionManager;
    }
}
